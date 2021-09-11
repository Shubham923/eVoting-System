package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Loading extends AppCompatActivity {

    ProgressBar progressBar;
    Session session;
    String result;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        progressBar=findViewById(R.id.progress_bar);
        Drawable draw=getResources().getDrawable(R.drawable.custom_progresssbar);
// set the drawable as progress drawable
        progressBar.setProgressDrawable(draw);
        session=new Session(getApplicationContext());
        result="";
        progressBar.setVisibility(View.VISIBLE);


        /*final String mCurrentPhotoPath=getIntent().getExtras().getString("img_title");
        try {
            ImageSender is = new ImageSender();
            is.execute(mCurrentPhotoPath);
            MessageSender ms = new MessageSender();
            result = ms.execute().get();
        }catch (Exception e)
        {}

        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
        if(result.equals(session.getVoterId()))
        {
            intent=new Intent(getApplicationContext(), testActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Face Recognition Failed",Toast.LENGTH_SHORT).show();
            intent=new Intent(getApplicationContext(), Activity_Login.class);

            startActivity(intent);
        }*/
        Thread mythread = new Thread(runnable);
        mythread.start();

       // progressBar.setVisibility(View.INVISIBLE);


    }

    Runnable runnable = new Runnable() {
        public void run() {


            final String mCurrentPhotoPath=getIntent().getExtras().getString("img_title");
            try {
                ImageSender is = new ImageSender();
                is.execute(mCurrentPhotoPath);
                MessageSender ms = new MessageSender();
                result = ms.execute().get();
            }catch (Exception e)
            {}

          ////  Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
            if(result.equals(session.getVoterId()))
            {
                System.out.println("exe1");
                intent=new Intent(getApplicationContext(), testActivity.class);
                startActivity(intent);
            }
            else
            {
                //Toast.makeText(getApplicationContext(),"Face Recognition Failed",Toast.LENGTH_SHORT).show();
                System.out.println("exe");
                intent=new Intent(getApplicationContext(), Activity_Login.class);

                startActivity(intent);
            }



        }
    };
}
