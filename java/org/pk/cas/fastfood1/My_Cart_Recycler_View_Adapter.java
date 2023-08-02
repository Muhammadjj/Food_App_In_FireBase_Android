package org.pk.cas.fastfood1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;

public class My_Cart_Recycler_View_Adapter extends RecyclerView.Adapter<My_Cart_Recycler_View_Adapter.ViewHolder> {

    Context context;
    ArrayList<MyCartRecyclerViewModelclass> data;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    public My_Cart_Recycler_View_Adapter(Context context, ArrayList<MyCartRecyclerViewModelclass> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_recycler_view_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MyCartRecyclerViewModelclass modelClass1 = data.get(position);
//        holder.my_cart_food_price.setText(modelClass1.getMycartfoodprice());
        holder.my_cart_food_name.setText(modelClass1.getMycartfoodname());
        holder.my_cart_quanity.setText(modelClass1.getMycartfoodQuantity());

        Glide.with(holder.my_cart_image.getContext())
                .load(modelClass1.getBigimage())
                .into(holder.my_cart_image);


        /*my_cart_delete_icon touch on Delete this card */
        holder.my_cart_delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firestore.collection("AddtoCart").document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                        .collection("CurrentUser").document(data.get(position).DocumentId)
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    data.remove(data.get(position));
                                    notifyDataSetChanged();
                                }
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView my_cart_image,
                my_cart_delete_icon;
        TextView my_cart_food_name,
                my_cart_food_price,
                my_cart_quanity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            my_cart_image = itemView.findViewById(R.id.FoodImages_my_cart_item);
            my_cart_delete_icon = itemView.findViewById(R.id.my_cart_delete_icon);
            my_cart_food_name = itemView.findViewById(R.id.my_cart_food_name);
//            my_cart_food_price =itemView.findViewById(R.id.my_cart_food_price);
            my_cart_quanity = itemView.findViewById(R.id.my_cart_food_Quantity);
        }
    }
}
