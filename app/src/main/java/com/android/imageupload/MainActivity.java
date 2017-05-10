package com.android.imageupload;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String UPLOAD_URL = "http://localhost:8080/GbvJewellers/upload.php";
    public static final String UPLOAD_KEY = "image";
    public static final String TAG = "MainActivity";

    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose, buttonUpload, buttonView;
    private ImageView imageView;
    private Bitmap bitmap;

    private Uri filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        buttonView = (Button) findViewById(R.id.buttonViewImage);

        imageView = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==buttonChoose) {
            showFileChooser();
        }
        if(v==buttonUpload) {
            uploadImage();
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null) {
            filePath = data.getData();
            Log.i(TAG, "onActivityResult: filePath: "+filePath);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Log.i(TAG, "onActivityResult: bitmap: "+bitmap);
                imageView.setImageBitmap(bitmap);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    //We convert the image to base64 String

    private void uploadImage() {

    }
}
