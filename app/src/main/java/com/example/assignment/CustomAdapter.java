package com.example.assignment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class CustomAdapter extends FirebaseRecyclerAdapter<ProductModel, CustomAdapter.myviewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CustomAdapter(@NonNull FirebaseRecyclerOptions<ProductModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, final int position, @NonNull ProductModel model) {
        holder.name.setText(model.getName());
        Glide.with(holder.img.getContext()).
                load(model.getImage()).
                into(holder.img);

        holder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("정말 지우시겠습니까?");
                builder.setMessage("삭제 후 되돌릴 수 없습니다.");

                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("products")
                                .child(getRef(holder.getBindingAdapterPosition()).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(), "취소됨", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog delDlg = builder.create();
                delDlg.show();
            }
        });
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.third_item, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        Button btnDel;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.product_image);
            name = (TextView) itemView.findViewById(R.id.product_name);
            btnDel = (Button) itemView.findViewById(R.id.button_delete);
        }
    }
}
