package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView<CustomAdapter.myviewholder> {

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Product model) {
        holder.name.setText(model.getName());
        Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);
    }

    public CustomAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.product_image);
            name = (TextView) itemView.findViewById(R.id.product_name);
        }
    }

//    private ArrayList<Product> productList;
//    private Context context;
//
//    public CustomAdapter(ArrayList<Product> productList, Context context) {
//        this.productList = productList;
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
//        CustomViewHolder holder = new CustomViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
//        Glide.with(holder.itemView)
//                .load(productList.get(position).getImage())
//                .into(holder.product_image);
//        holder.product_name.setText(productList.get(position).getName());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return(productList != null ? productList.size() : 0);
//    }
//
//    public class CustomViewHolder extends RecyclerView.ViewHolder {
//        ImageView product_image;
//        TextView product_name;
//
//        public CustomViewHolder(@NonNull View itemView) {
//            super(itemView);
//            this.product_image = itemView.findViewById(R.id.product_image);
//            this.product_name = itemView.findViewById(R.id.product_name);
//        }
//    }
}
