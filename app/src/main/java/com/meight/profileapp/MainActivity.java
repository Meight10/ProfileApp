package com.meight.profileapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shareButton = (Button)findViewById(R.id.shareButton);

        //buttonAction(shareButton);

        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Date now  = new Date();
                android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

                String mPath = Environment.getExternalStorageDirectory().toString() + "/Pictures/Screenshots/Screen.jpg";
                File cachePath = new File(mPath);
                try {


                    View v1 = getWindow().getDecorView().getRootView();
                    v1.setDrawingCacheEnabled(true);
                    Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());//Bitmap bitmap = v1.getDrawingCache();
                    v1.setDrawingCacheEnabled(false);

                    File imageFile = new File(mPath);

                    FileOutputStream out = new FileOutputStream(imageFile);
                    int quality = 100;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
                    out.flush();
                    out.close();


                } catch (Exception e) {
                    e.printStackTrace();
                }

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(cachePath));
                startActivity(Intent.createChooser(share, "Share via"));

            }
        });
    }

    private void openScreenshot(File imageFile){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(imageFile);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);

    }
    /*shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                View v1 = findViewById(R.id.logo);
                v1.setDrawingCacheEnabled(true);
                Bitmap bitmap = v1.getDrawingCache();
                File mPath = Environment.getExternalStorageDirectory();
                File imageFile = new File(mPath.getAbsolutePath() + "/DCIM/Camera/image.jpg");
                try {
                    imageFile.createNewFile();
                    FileOutputStream out = new FileOutputStream(imageFile);
                    int quality = 100;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
                    out.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });*/
    /*private void generateScreenShoot(){
        View v1 = getWindow().getDecorView().getRootView();
        v1.setDrawingCacheEnabled(true);
        Bitmap bitmap = v1.getDrawingCache();
        File mPath = Environment.getExternalStorageDirectory();
        File imageFile = new File(mPath.getAbsolutePath() + "/DCIM/Camera/image.jpg");
        try {
            mPath.createNewFile();
            FileOutputStream out = new FileOutputStream(mPath);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }*/

    /*private void buttonAction(Button button){

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("-1");
                generateScreenShoot();
            }
        });

        System.out.println("-2");
    }*/

    /*private void actionPerformedShareButton(){

    }*/

    private Button shareButton;
}
