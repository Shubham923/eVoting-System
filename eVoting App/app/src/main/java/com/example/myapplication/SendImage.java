package com.example.myapplication;
/*ACTIVITY FOR SENDING IMAGE TO SERVER
* 'Result'(NAME OF VOTER AFTER PERFORMING FACE RECOGNITION) IS OBTAINED
*  CHANGES REQUIRED.
* */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendImage extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    private Button send;
    private TextView tv;
    private String result="";
    Session session;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_image);
        send=(Button)findViewById(R.id.bSend);
        tv=(TextView)findViewById(R.id.bresult);
        final String imageTitle=getIntent().getExtras().getString("img_title");
        Toast.makeText(this, imageTitle,Toast.LENGTH_SHORT);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    ImageSender is=new ImageSender();
                    is.execute(imageTitle);
                    MessageSender ms=new MessageSender();
                    result=ms.execute().get();
                    /*RESULT IS THE NAME OF VOTER RETURNED AFTER PERFORMING IMAGE RECOGNITION*/
                    tv.setText(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });
        Toast.makeText(this, "Image Sent Successfully", Toast.LENGTH_SHORT).show();


    }


}
