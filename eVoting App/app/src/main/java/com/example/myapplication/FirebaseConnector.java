package com.example.myapplication;

import android.content.Intent;
import android.widget.*;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseConnector {
    Candidate cand;
    DatabaseReference database;
    ArrayList<Candidate> al;
    private RecyclerView recyclerView;
  //  private ArtistsAdapter adapter;

    public void FetchInfo(int constId)
    {


        Query query = FirebaseDatabase.getInstance().getReference("Candidates")
                .orderByChild("constId")
                .equalTo(constId);
        cand=new Candidate();
        al=new ArrayList<>();

        query.addListenerForSingleValueEvent(valueEventListener);


    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            al.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cand = snapshot.getValue(Candidate.class);
                    al.add(cand);
                    System.out.println(al.get(al.size()-1).getName());
                }
                // adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}
