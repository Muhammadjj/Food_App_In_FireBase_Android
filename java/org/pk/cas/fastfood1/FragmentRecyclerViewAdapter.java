package org.pk.cas.fastfood1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FragmentRecyclerViewAdapter extends RecyclerView.Adapter<FragmentRecyclerViewAdapter.ViewHolder> {

    ArrayList<FragmentRecyclerViewModelClass> data;
    Context context;

    public FragmentRecyclerViewAdapter(ArrayList<FragmentRecyclerViewModelClass> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_view_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FragmentRecyclerViewModelClass model = data.get(position);
        holder.name.setText(model.getName());
        holder.price.setText(model.getPKR());
//      glide library uses in access for images
        Glide.with(holder.image.getContext())
                .load(model.getImage())
                .into(holder.image);



//      Select all layout box and next Activity.
        holder.item_selected_Card_View.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecyclerView_item_click_Next_Activity.class);
            intent.putExtra("name", model.getName());
            intent.putExtra("price", model.getPKR());
            intent.putExtra("images", model.getImage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image, image_icon;
        TextView name, price;
        CardView item_selected_Card_View;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.Images);
            image_icon = itemView.findViewById(R.id.Icon_Image);
            name = itemView.findViewById(R.id.FoodName);
            price = itemView.findViewById(R.id.PKR);
            item_selected_Card_View = itemView.findViewById(R.id.item_selected_Card_View);
        }
    }
}
