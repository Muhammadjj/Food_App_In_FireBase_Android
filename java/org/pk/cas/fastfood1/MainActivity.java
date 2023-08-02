package org.pk.cas.fastfood1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextInputLayout et_e_mail, et_password;
    TextView nextActivitysignup;
    Button Login;
    ImageView login_page_image;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    /*SharedPreferences ko hm na 3 part ma Read krna ha.
     *  1)
     *SharedPreferences ka mqsd ya ha ky ya minimum data apny ps store kr skta ha SharedPreferences
     * asy hm Auto login page ka laya use krty ha i mean ka agr hmre koi bhi email firebase ya kase
     * aur plateForm pr mojod ha to login wale activity pr sa open nhi ho ge direct hi hmy
     * SharedPreferences apne main activity pr la jaye ga js pr hm na intent sa btya ho ga ky hmy kn
     * si activity ma jana ha */
    SharedPreferences sharedPreferences;
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* 3)
        *Sb sa phly to hm na asy SharedPreferences ky variable ko Define kya ha as ka bd hm na asy
        * SharedPreferences ka variable lga ka ya confirm kya ha ka jo hmra share(); method ha as
        *ma email mojod ha ka nhi agr hmry share method ka andr email (if)isEmpty() ho ge to ya agle
        * activity ma nhi jaye ga ya hm sa email manga ga aur agr as ma
        * Aur agr as ma email (else) store hoye to ya hmy next activity ma la jaye ga  */
        sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
        final String type = sharedPreferences.getString("email", "");
        if (type.isEmpty()) {
            Toast.makeText(this, "please login ", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(MainActivity.this, NavigationDrawerMainActivity.class));
            Toast.makeText(this, "Account login", Toast.LENGTH_SHORT).show();
            finish();
        }


        et_e_mail = findViewById(R.id.email);
        et_password = findViewById(R.id.password);
        nextActivitysignup = findViewById(R.id.NextSignUp);
        Login = findViewById(R.id.login);
        login_page_image =findViewById(R.id.login_page_image);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        /*Blink Animation for the Fast Food Image */
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.blink_animation);
        login_page_image.startAnimation(animation);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_e_mail.getEditText().getText().toString();
                String password = et_password.getEditText().getText().toString();

                if (TextUtils.isEmpty(email)) {
                    et_e_mail.setError("Please Enter the E_mail");
                } else if (TextUtils.isEmpty(password)) {
                    et_password.setError("Please Enter the Password");
                } else if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, password + " : " + "Maximum 6 Character", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.setTitle("Login Page.");
                    progressDialog.setMessage("Please Wait A Few Second....");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();

                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(MainActivity.this, "Login Successfully..", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, NavigationDrawerMainActivity.class));
                            progressDialog.dismiss();
                            share();// Method checkOut the SharedPreferences
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Snackbar snackbar = Snackbar.make(v, e.getMessage(), Snackbar.LENGTH_LONG);
                            snackbar.setDuration(2000);
                            snackbar.show();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        });


//       Click Signup text open the RegisterActivity
        nextActivitysignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                Toast.makeText(MainActivity.this, "Register Activity", Toast.LENGTH_SHORT).show();

            }
        });

    }


   /* 2)
   * Share() method as method ka andr hm na asy btya ha ky jo hm na login page ma email aur password
   * dyna ha asy SharedPreferences.Editor as ma store kr la {SharedPreferences bhi database ki trha
   * ha but ya minimum data ko store krta ha } aur store krna ka bd ya hmy ays activity ma la
   * jaye js ka hm na intent ki help sa btya howa ha. */

    private void share() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", Objects.requireNonNull(et_e_mail.getEditText()).getText().toString());
        editor.putString("password", Objects.requireNonNull(et_password.getEditText()).getText().toString());
        editor.apply();
        Toast.makeText(MainActivity.this, "Account login successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, NavigationDrawerMainActivity.class));
    }
}