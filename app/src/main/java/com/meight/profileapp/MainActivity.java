package com.meight.profileapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shareButton = (Button)findViewById(R.id.shareButton);

        buttonAction(shareButton);
    }


    private void buttonAction(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                actionPerformedShareButton();
            }
        });
    }

    private void actionPerformedShareButton(){
        File cachePath = takeScreenShoot();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(cachePath));
        startActivity(Intent.createChooser(share, "Share via"));
    }

    private File takeScreenShoot(){
        //generando fecha con formato
        Date now  = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        //creando directorio
        String mPath = Environment.getExternalStorageDirectory().toString() + "/Pictures/Screenshots/Screen.jpg";
        File cachePath = new File(mPath);

        try {
            //consiguiendo ventana del view y pasandola a bitmap
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());//Bitmap bitmap = v1.getDrawingCache();
            v1.setDrawingCacheEnabled(false);

            //creando archivo contenedor y colocando bitmap
            File imageFile = new File(mPath);

            FileOutputStream out = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cachePath;

    }





    private Button shareButton;
}
