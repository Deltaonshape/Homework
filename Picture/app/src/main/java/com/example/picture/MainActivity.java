package com.example.picture;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    final int PICK_CONTACT_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        {
            Log.d("mytag","TAG:"+view.getId());
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            startActivityForResult(i, PICK_CONTACT_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;
        ImageView imageView = (ImageView) findViewById(R.id.pic);
        // проверяем, что код вызова соответствует тому, что мы отправляли в методе onClick()
        if (requestCode == PICK_CONTACT_CODE) {

            /*
            // получаем Uri контакта
            Uri uri = data.getData();
            //imageView.setImageURI(uri);
            Log.d("mytag","request: "+uri);


            // запрашиваем данные контакта по его Uri
            final String[] columns = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.WIDTH,
                    MediaStore.Images.Media.HEIGHT
            };
            Cursor cursor = getContentResolver()
                    .query(uri, columns, null, null, null);

            Log.d("mytag","cursor size: "+cursor.getCount());
            cursor.moveToFirst();
            int width = cursor.getInt(1); // поле - ширина
            int height = cursor.getInt(2); // поле - ширина

            Log.d("mytag","image w: "+width + " image h: " +height);
            */


            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e){
                e.printStackTrace();
            }

            imageView.setImageBitmap(bitmap);

        }
    }
    //загрузка изображения по uri
    /*private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        Log.d("mytag","загрузка изображения");
        return image;
    }*/
    //отображение полученного bitmap
   /* public void setImageBitmap (Bitmap bm){

    }*/
}
