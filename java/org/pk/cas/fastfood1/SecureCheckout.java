package org.pk.cas.fastfood1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SecureCheckout extends AppCompatActivity {

    ImageView location_Icon;
    MaterialButton secure_checkout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_checkout);

        location_Icon = findViewById(R.id.location_Icon);
        secure_checkout_button = findViewById(R.id.secure_checkout_material_button);

//      click Order Now Button and show this toast.
        secure_checkout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecureCheckout.this, "Order Now", Toast.LENGTH_SHORT).show();
            }
        });
        /*location_Icon hm na Permission ka laya bnya ha jb bhi
         * hm location_Icon pr click kry ga tb hi hmy permission dialog
         * view ho ga.*/
        location_Icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(SecureCheckout.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(SecureCheckout.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                        ActivityCompat.requestPermissions(SecureCheckout.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    } else {
                        ActivityCompat.requestPermissions(SecureCheckout.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    }
                }
            }
        });
    }


    /* ya method hm na onCreate ka bhr bnya ha aur hm as method ma location ka laya
     * onRequestPermissionsResult bna raha ha  */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(SecureCheckout.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}