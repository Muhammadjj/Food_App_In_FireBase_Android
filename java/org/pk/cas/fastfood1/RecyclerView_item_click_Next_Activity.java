package org.pk.cas.fastfood1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Objects;

public class RecyclerView_item_click_Next_Activity extends AppCompatActivity {
    TextView textFoodName, textFoodPrice, text_count_plus_minus;
    ImageView Big_imageView, shopping_Click_Next, minus_count, plus_count;
    MaterialButton AddToCard;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth ;
    FragmentRecyclerViewModelClass modelClass ;

    int count = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_item_click_next);


//       All layout member initlized
        textFoodName = findViewById(R.id.FoodName_Click_Next);
        textFoodPrice = findViewById(R.id.Price_Click_Next);
        text_count_plus_minus = findViewById(R.id.textview_minus_plus);
        minus_count = findViewById(R.id.minus_count);
        plus_count = findViewById(R.id.plus_count);
        Big_imageView = findViewById(R.id.Image_Click_Next);
        shopping_Click_Next = findViewById(R.id.Shopping_Click_Next);
        AddToCard = findViewById(R.id.AddToCard);
        modelClass = new FragmentRecyclerViewModelClass();
        firebaseFirestore  = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        /*Model Class hm na as lya bnye q ka hmy Firebase sa data chaya tha or all data hmy
        * ModelClass sa a raha ha hm na aur hm FragmentRecyclerViewAdapter sa data bhj raha ha
        * aur as Activity ma hm data hasil kr raha ha aur hm data intent ka zrya hasl krty ha
        * as jaga pr hm na model Class ka use as laya kya q ka hmy all data Firebase ka model
        * class ma a raha ha. As waja sa hm ny model class ka use kya q ky hmy fireBase ka hi
        * data chaya tha aur jo data hm model Class ma get kr raha tha asy hm na as activity ka
        * texts ma da dya. */
        modelClass.setName(getIntent().getStringExtra("name"));
        modelClass.setPKR(getIntent().getStringExtra("price"));
        modelClass.setImage(getIntent().getStringExtra("images"));

        textFoodName.setText(modelClass.getName());
        textFoodPrice.setText(modelClass.getPKR());

        Toast.makeText(this, "" + modelClass.getImage(), Toast.LENGTH_SHORT).show();
        /*Glide ki library hm as laya use kr raha ha ky hm jo as FragmentRecyclerViewAdapter ma sa
         * images bhj raha ha wo Glide Library ka zrya bhj raha ha.
         * As laya hm as activity ma glide library ka use kr raha ha q ka js library ka thro hm
         * images bhjy gy ose ka zrya hi hm Next activity ma as image ko pick kr sky ga. */
        Glide.with(getApplication())
                .load(modelClass.getImage())
                .into(Big_imageView);


//      Click AddToCard button and working this Next Activity.
        AddToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("names", modelClass.getName());
                hashMap.put("prices", modelClass.getPKR());
                hashMap.put("imagee", modelClass.getImage());

                firebaseFirestore.collection("AddtoCart")
                        .document((auth.getCurrentUser()).getUid())
                        .collection("CurrentUser").add(hashMap)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(RecyclerView_item_click_Next_Activity.this, task+"Added to Cart", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        /*Shopping Icon click  for Next Activity */
        shopping_Click_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecyclerView_item_click_Next_Activity.this, ToolbarBasketActivity.class));

            }
        });


        plus_count.setOnClickListener(v -> {
            count++;
            text_count_plus_minus.setText(String.valueOf(count));

        });

        minus_count.setOnClickListener(v -> {
            if (count > 1) {
                count--;

                text_count_plus_minus.setText(String.valueOf(count));

            }
        });

    }
}