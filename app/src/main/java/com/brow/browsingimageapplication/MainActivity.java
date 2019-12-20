package com.brow.browsingimageapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
        ImageView imageView, imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.img);
        imgProfile = findViewById(R.id.imgProfile);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

    }
    private void BrowseImage(){
        Intent intent =new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,0);
    }

    @SuppressLint("MissingSuperCall")
    protected void  onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==RESULT_OK){
            if (data == null){
                Toast.makeText(this, "please select images", Toast.LENGTH_LONG).show();
            }
        }

        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
//        String imagePath = getRealPathFromUri(uri);
//        PreviewImage(imagePath);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null,
                null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;

    }

//    private void PreviewImage(String imagePath){
//        File imgFile = new File(imagePath)
//                if(imgFile.exists()){
//                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                    imgProfile.setImageBitmap(myBitmap);
//                }
//    }
}




