package com.example.final_projects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private CircleImageView Photo;
    Button sendPhoto;
    PickResult r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_detiles);

        Photo = findViewById(R.id.profile_photo_2);
        sendPhoto = findViewById(R.id.Upload_Photo);
        firebaseStorage = FirebaseStorage.getInstance();
        reference = firebaseStorage.getReferenceFromUrl("gs://final-projects-2-73b2b.appspot.com/");

        // code  is to make the Activity full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressDialog = new ProgressDialog(login_detiles.this);
        progressDialog.setMessage("Uploading..");
        progressDialog.setCancelable(false);

        Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()).show(login_detiles.this);
            }
        });

        //Upload Image to fireStorage
        sendPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userImage != null) {
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
                                    progressDialog.dismiss();
                                    Log.e("MAS_IMAGE_PATH", uri + "");
                                    Toast.makeText(login_detiles.this, "Success", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(login_detiles.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(login_detiles.this, "Add image first", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void onPickResult(PickResult r) {
        Photo.setImageBitmap(r.getBitmap());
        userImage = r.getUri();
    }

    }
