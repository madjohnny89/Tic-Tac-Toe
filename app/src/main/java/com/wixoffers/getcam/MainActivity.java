package com.wixoffers.getcam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public  class MainActivity extends AppCompatActivity {
    ImageView player1;
    ImageView player2;
    Bitmap bm1;
    Bitmap bm2;

    int x =0;
    static final int REQUEST_IMAGE_CAPTURE = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1 = findViewById(R.id.img1);
        player2 = findViewById(R.id.img2);




    }
    public void uploadImg(View view){
        if (view.getId()== R.id.img1){
            x=0;
        }
        else x=1;

dispatchTakePictureIntent();

    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if (x==0) {
                player1.setImageBitmap(imageBitmap);
                bm1 = imageBitmap;


                x=1;
            }
            else {
                player2.setImageBitmap(imageBitmap);
                bm2 = imageBitmap;

                x=0;

            }

        }
    }
    public void letsGo(View view){
        Intent intent = new Intent(MainActivity.this,inGame.class);
        intent.putExtra("bm1",bm1);
        intent.putExtra("bm2",bm2);

    startActivity(intent);
    finish();
    }


}
