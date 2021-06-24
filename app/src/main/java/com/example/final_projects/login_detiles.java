package com.example.final_projects;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import de.hdodenhof.circleimageview.CircleImageView;

public class login_detiles extends AppCompatActivity implements IPickResult {

    FirebaseStorage firebaseStorage;
    StorageReference reference;
    Uri userImage = null;
    ProgressDialog progressDialog;
    ProgressDialog progressDialog_photo;
    private CircleImageView Photo;
    Button sendPhoto;
    EditText email, pass;
    Button SingUp;
    FirebaseAuth mAtu;


    //=======================================Casting=====================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_detiles);

        Photo = findViewById(R.id.profile_photo_2);
        sendPhoto = findViewById(R.id.Upload_Photo);
        firebaseStorage = FirebaseStorage.getInstance();
        reference = firebaseStorage.getReferenceFromUrl("gs://final-projects-2-73b2b.appspot.com/");
        mAtu = FirebaseAuth.getInstance();
        SingUp = findViewById(R.id.Submit_Button_2);
        email = findViewById(R.id.edit_email_3);
        pass = findViewById(R.id.edit_pass_3);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Wait A Moment...");
        progressDialog.setCancelable(false);
        //============================================================================================
        // code  is to make the Activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //=======================================progressDialog for uploading profile photo=====================================================
        progressDialog_photo = new ProgressDialog(login_detiles.this);
        progressDialog_photo.setMessage("Uploading..");
        progressDialog_photo.setCancelable(false);
        //======================================When click Image View Use PickerImageDialog library to access to Gallery and pick image ======================================================
        Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()).show(login_detiles.this);
            }
        });
        //====================================Upload Image to fireStorage========================================================
        sendPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userImage != null) {
                    progressDialog.show();
                    StorageReference childRef = reference.child(System.currentTimeMillis() + "_mas.png");
                    UploadTask uploadTask = childRef.putFile(userImage);
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            StorageReference storageReference = firebaseStorage.getReference(childRef.getPath());
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    progressDialog_photo.dismiss();
                                    Log.e("MAS_IMAGE_PATH", uri + "");
                                    Toast.makeText(login_detiles.this, "Success", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog_photo.dismiss();
                            Toast.makeText(login_detiles.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(login_detiles.this, "Add image first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //=============================================Register button=================================
        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get email and password and send it to firebase by the log in button
                String email_1 = email.getText() + "";
                String password = pass.getText() + "";
                // عمل تحقق للايميل والباسورد اذا كانو نفس عدد الكاركتير المحدد والصحة كتابة الايمل
                if (isValidEmail(email_1) && password.length() >= 6) {
                    newuser(email_1, password);
                } else {
                    if (!isValidEmail(email_1))
                        email.setError("The e-mail does not match the e-mail format");
                    else
                        pass.setError("Enter a password of 6 numbers or above");
                }
            }
        });
    }
    //==============================register method==========================================================
    private void newuser(String email, String password) {
        progressDialog.show();
        mAtu.createUserWithEmailAndPassword(email, password)
                //call back method
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.dismiss();
                        Intent intent = new Intent(login_detiles.this, login_detiles.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(login_detiles.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //======================================to Show Image That Choose On Image View======================================================
    public void onPickResult(PickResult r) {
        Photo.setImageBitmap(r.getBitmap());
        userImage = r.getUri();
    }

    //=======================================TO ENSURE THAT THE EMAIL IS A corrected EMAIL OR NOT===================================================
    //TO ENSURE THAT THE EMAIL IS A corrected EMAIL OR NOT
    public static boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    //=================================================Show the progress bar onPause Call Method=================================================
    @Override
    protected void onPause() {
        super.onPause();
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
