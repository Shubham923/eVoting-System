package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class testActivity extends AppCompatActivity {

    Button btn;
    EditText et;
    FirebaseConnector fc;
    ArrayList<String> al;
    private RecyclerView recyclerView;
    private CandidateAdapter adapter;
    private List<Candidate> candidateList;
    TextView tv_candidate,tv_party,votedfor,tv_temporary;
    ImageView iv_candidate_pic;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tv_candidate=findViewById(R.id.tv_candidate);
        tv_party=findViewById(R.id.tv_party);
        iv_candidate_pic=findViewById(R.id.iv_candidate_pic);
        votedfor=findViewById(R.id.tv_voted);
        tv_candidate.setVisibility(View.INVISIBLE);
        tv_party.setVisibility(View.INVISIBLE);
        votedfor.setVisibility(View.INVISIBLE);
        iv_candidate_pic.setVisibility(View.INVISIBLE);
        session=new Session(getApplicationContext());


        Toast.makeText(getApplicationContext(),"Welcome "+session.getUsername(),Toast.LENGTH_SHORT).show();
//
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        candidateList = new ArrayList<>();
   //     System.out.println(candidateList.size());
        adapter = new CandidateAdapter(this, candidateList,this);
        recyclerView.setAdapter(adapter);


//

        Query query = FirebaseDatabase.getInstance().getReference("Candidates")
                .orderByChild("constId")
                .equalTo(211);

        query.addListenerForSingleValueEvent(valueEventListener);

        Toast.makeText(getApplicationContext(),"Welcome "+session.getUsername(),Toast.LENGTH_LONG).show();





    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            candidateList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Candidate candidate = snapshot.getValue(Candidate.class);
                  //  System.out.println(candidate.getName());
                    candidateList.add(candidate);
                }
               adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
