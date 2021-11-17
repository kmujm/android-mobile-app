package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name, imgUrl;
    Button btnAdd, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText) findViewById(R.id.upload_name);
        imgUrl = (EditText) findViewById(R.id.upload_image);

        btnAdd = (Button) findViewById(R.id.button_upload);
        btnBack = (Button) findViewById(R.id.button_back);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
                clearAll();
                startActivity(new Intent(getApplicationContext(), ThirdActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearAll();
                startActivity(new Intent(getApplicationContext(), ThirdActivity.class));
            }
        });
    }

    private void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.getText().toString());
        map.put("image", imgUrl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("products").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "업로드 성공!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "업로드 실패", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void clearAll() {
        name.setText("");
        imgUrl.setText("");
    }
}
