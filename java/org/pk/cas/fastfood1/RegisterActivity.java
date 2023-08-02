package org.pk.cas.fastfood1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout et_e_mail,et_password, et_name;
    ShapeableImageView circle_image;
    Button register;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        et_e_mail=findViewById(R.id.email);
        et_password =findViewById(R.id.password);
        et_name= findViewById(R.id.Name);
        register = findViewById(R.id.register);
        circle_image = findViewById(R.id.circle_image);
        firebaseAuth =FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForm();
            }
        });


        circle_image.setOnClickListener(v -> {
            Dialog dialog =new Dialog(RegisterActivity.this);
            dialog.setContentView(R.layout.circle_image_dialog);

            MaterialButton gallery=dialog.findViewById(R.id.gallery);
            MaterialButton camera=dialog.findViewById(R.id.camera);
            dialog.show();

         /* gallery wala button hm na as laya use kya ha ky jb hm gallery waly button pr click
         * kry to hmry gallery open ho jaye aur hm gallery sa image select kr sky  */
//                gallery.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent igallery=new Intent(Intent.ACTION_PICK);
//                        igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        startActivityForResult(igallery,pickGalleryImages);
//                        dialog.dismiss();
//                    }
//                });
        });
    }

    /* (onActivityResult) ya hm na as laya use kya ha q na ky hmy apne gallery ka andr enter
    * hona ky laya onActivityResult ki need hote ha . */




    private void registerForm() {
        String  name = Objects.requireNonNull(et_name.getEditText()).getText().toString();
        String  email = Objects.requireNonNull(et_e_mail.getEditText()).getText().toString();
        String  password = Objects.requireNonNull(et_password.getEditText()).getText().toString();

        if (TextUtils.isEmpty(name)){
            et_name.setError("Please Enter The Name");
        }else if (TextUtils.isEmpty(email)){
            et_e_mail.setError("Please Enter The E_mail");
        }else if (TextUtils.isEmpty(password)){
            et_password.setError("Please Enter The Password");
        }else if (password.length()<6){
            Toast.makeText(RegisterActivity.this, password+" : "+"Maximum 6 Character", Toast.LENGTH_SHORT).show();
        } else{
            progressDialog.setTitle("Register Page.");
            progressDialog.setMessage("Please Wait A Few Second....");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setIcon(R.drawable.recyclecircle);
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                        progressDialog.dismiss();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Don't Register", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Snackbar snackbar = Snackbar.make(register, Objects.requireNonNull(e.getMessage()),Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            });
        }
    }
}