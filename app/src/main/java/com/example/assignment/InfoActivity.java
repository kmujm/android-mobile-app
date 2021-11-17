package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class InfoActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore fStore;

    TextView email, name, phone, address;
    Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        fStore = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        email = (TextView) findViewById(R.id.info_email);
        name = (TextView) findViewById(R.id.info_name);
        phone = (TextView) findViewById(R.id.info_phone);
        address = (TextView) findViewById(R.id.info_address);

        fStore.collection("users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot != null) {
                    String dbEmail = documentSnapshot.getString("Email");
                    String dbName = documentSnapshot.getString("Name");
                    String dbPhone = documentSnapshot.getString("Phone");
                    String dbAddress = documentSnapshot.getString("Address");

                    email.setText(dbEmail);
                    name.setText(dbName);
                    phone.setText(dbPhone);
                    address.setText(dbAddress);
                }
            }
        });



        btnLogout = (Button) findViewById(R.id.logout_button);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}
