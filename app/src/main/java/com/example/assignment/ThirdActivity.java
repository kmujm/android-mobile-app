package com.example.assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ThirdActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    FloatingActionButton btnAdd, btnInfo;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();


        FirebaseRecyclerOptions<ProductModel> options =
                new FirebaseRecyclerOptions.Builder<ProductModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("products"), ProductModel.class)
                        .build();

        adapter = new CustomAdapter(options);
        recyclerView.setAdapter(adapter);

        btnAdd = (FloatingActionButton) findViewById(R.id.addButton);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));

            }
        });

        btnInfo = (FloatingActionButton) findViewById(R.id.infoButton);
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user != null) {
                    startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ThirdActivity.this);
                    builder.setTitle("로그아웃 상태입니다");
                    builder.setMessage("로그인이나 회원가입을 해주세요");
                    builder.setPositiveButton("로그인하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("회원가입하기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), SecondActivity.class));
                        }
                    });
                    AlertDialog stateDlg = builder.create();
                    stateDlg.show();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
