package com.example.myapplication;
/*
* THIS  CLASS WILL QUERY A FIREBASE DATABASE
* USE THIS CLASS TO FETCH USER INFORMATION ONLY.
*
* */

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class FetchUserInfo {

    public DatabaseReference dbArtists;
    public User user;
    ArrayList<User> userList=new ArrayList<User>();
    Activity_Login al=new Activity_Login();
    public User getUserInfo(final Session session, String tvInput, final Context cntx)
    {
        Query query = FirebaseDatabase.getInstance().getReference("User")
                .orderByChild("voterId")
                .equalTo(tvInput);
        user=new User();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {

                        user=data.getValue(User.class);
                        if(user!=null)
                        {
                            Toast.makeText(cntx, "Voter ID Identified",Toast.LENGTH_SHORT).show();
                            session.setUser(user);
                            Intent intent=new Intent(cntx, TakePic.class);
                            cntx.startActivity(intent);
                        }
                        break;
                    }
                }

                //IF IT CAN'T FIND THE VOTER ID THEN RETURN NULL

                else{
                    user=null;
                    session.setUser(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        for(int i=0;i<100000;i++);
        return user;
    }


}
