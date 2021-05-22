package com.example.final_projects.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.final_projects.MainActivity;
import com.example.final_projects.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

public class Profile_Fragment extends Fragment implements IPickResult {

    FirebaseStorage firebaseStorage;
    StorageReference reference;
    public Uri userImage = null;
    ProgressDialog progressDialog;
    public ImageView Photo;
    Button sendPhoto, Submit;
    EditText edit_name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        Photo = view.findViewById(R.id.profile_photo);
        sendPhoto = view.findViewById(R.id.Upload_Photo_btn);
        edit_name = view.findViewById(R.id.edit_name);
        Submit = view.findViewById(R.id.Submit_Button);
        firebaseStorage = FirebaseStorage.getInstance();
        reference = firebaseStorage.getReferenceFromUrl("gs://final-projects-2-73b2b.appspot.com/");

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Uploading..");
        progressDialog.setCancelable(false);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String theText = edit_name.getText().toString();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtra("Next Screen",theText);
                startActivity(intent);
            }
        });

        Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup()).show(getActivity());
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
                                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), e.getMessage() + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(getActivity(), "Add image first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onPickResult(PickResult r) {
        Photo.setImageBitmap(r.getBitmap());
        userImage = r.getUri();
    }
}
