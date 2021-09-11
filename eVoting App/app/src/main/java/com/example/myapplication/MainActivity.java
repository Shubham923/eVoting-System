package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    public String result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {

                    final TextView helloTextView = (TextView) findViewById(R.id.textView);
                    String text=send(v);
                    helloTextView.setText( text);
                } catch (IOException | ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public String send(View v) throws IOException, ExecutionException, InterruptedException {
        MessageSender ms=new MessageSender();
        result= ms.execute().get();
        return result;
    }

}
