package org.pk.cas.fastfood1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class ToolbarBasketActivity extends AppCompatActivity {
    TextView toolbar_price;
    MaterialButton mycart_Process_checkout;
    RecyclerView recyclerView;
    My_Cart_Recycler_View_Adapter adapter;
    ImageView cross_sign_my_cart;
    ArrayList<MyCartRecyclerViewModelclass> dataList;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_basket);

        toolbar_price = findViewById(R.id.toolbar_price);
        cross_sign_my_cart = findViewById(R.id.cross_sign_my_cart);
        mycart_Process_checkout = findViewById(R.id.my_cart_process_to_checkout);
        recyclerView = findViewById(R.id.my_cart_recycler);
        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        adapter = new My_Cart_Recycler_View_Adapter(this, dataList);
        recyclerView.setAdapter(adapter);


        firebaseFirestore.collection("AddtoCart").document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .collection("CurrentUser").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                String docmentId = documentSnapshot.getId();
                                MyCartRecyclerViewModelclass modelClass = new MyCartRecyclerViewModelclass();
                                modelClass.setBigimage((String) documentSnapshot.get("imagee"));

                                modelClass.setMycartfoodprice(((String) Objects.requireNonNull(documentSnapshot.get("prices"))));

                                modelClass.setMycartfoodname((String) documentSnapshot.get("names"));
                                modelClass.setDocumentId(docmentId);
                                dataList.add(modelClass);
                                dataList.indexOf(modelClass);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });


//        cross_sign_my_cart Activity use in onBackPress() method because back to one activity.
        cross_sign_my_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        mycart_Process_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ToolbarBasketActivity.this, SecureCheckout.class));
            }
        });
    }

}