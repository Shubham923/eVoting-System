package com.example.myapplication;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ImageSender  extends AsyncTask<String, Void, Void> {

    public ImageSender() throws IOException {
    }
    Socket s;
    public final static int SOCKET_PORT = 7800;  // you may change this
    public String received="";
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    Socket sock = null;


    @Override
    protected Void doInBackground(String... voids) {
        try {

            String FILE_TO_SEND = voids[0];
            System.out.println("Waiting...");
            try {

                sock = new Socket("192.168.43.40", SOCKET_PORT);
                System.out.println("Accepted connection : " + sock);


                DataInputStream dis = new DataInputStream(sock.getInputStream());
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

                dos.writeUTF("MH10511");


                // send file
                File myFile = new File(FILE_TO_SEND);
                byte [] mybytearray  = new byte [(int)myFile.length()];
                fis = new FileInputStream(myFile);
                bis = new BufferedInputStream(fis);
                bis.read(mybytearray,0,mybytearray.length);
                os = sock.getOutputStream();
                System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
                os.write(mybytearray,0,mybytearray.length);
                os.flush();
                System.out.println("Done.");
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally {
                try {
                    if (bis != null) bis.close();
                    if (os != null) os.close();
                    if (sock != null) sock.close();
                }catch(Exception e)
                {
                    System.out.println(e);
                }
            }

        }
        finally {
            try {
                if (sock != null) sock.close();
            }catch(Exception e)
            {

            }
        }



        return null;
    }
}
