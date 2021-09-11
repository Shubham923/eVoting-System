package com.example.myapplication;

import android.os.AsyncTask;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.lang.*;
import java.nio.*;

public class MessageSender extends AsyncTask<Void, Void, String> {

    public MessageSender() throws IOException {
    }
    Socket s;

    public String received="";



    @Override
    protected String doInBackground(Void... voids) {
        try {
            s=new Socket("192.168.43.40",7801);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            dos.writeUTF("MH10511");
            /*Code Segment for Processing Image*/





            /*receiving part resumes here*/

            received = dis.readUTF();
            System.out.println(received);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return received;
    }
}
