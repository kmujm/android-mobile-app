package com.example.myapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class additem extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button BtnChooseImg, BtnUpload;
    private EditText itemName;
    private ImageView imgView;
    private ProgressBar progressBar;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        BtnChooseImg = findViewById(R.id.choose_image);
        BtnUpload = findViewById(R.id.upload_button);
        itemName = findViewById(R.id.item_name);
        imgView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.upload_progressbar);

        BtnChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        imageUri = intent.getData();
                        Glide.with(additem.this)
                                .load(imageUri)
                                .into(imgView);

                    }
                }
            });
}
