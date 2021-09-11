package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.List;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder> {

    private Context mCtx;
    private List<Candidate> candidateList;
    private Activity activity;
    public DatabaseReference votesdatabase;
    static String tempName;


    public CandidateAdapter(Context mCtx, List<Candidate> candidateList, Activity activity) {
        this.mCtx = mCtx;
        this.candidateList = candidateList;
        this.activity=activity;
        votesdatabase= FirebaseDatabase.getInstance().getReference("Votes");

    }

    @NonNull
    @Override
    public CandidateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_candidates, parent, false);
        return new CandidateViewHolder(view,activity);
    }

    @Override
    public void onBindViewHolder(@NonNull final CandidateViewHolder holder, int position) {
        final Candidate candidate = candidateList.get(position);

        final String concat= candidate.getCandidateId();


        Class res = R.drawable.class;
        Field field = null;
        int drawableId=0;
        try {
            field = res.getField(concat);
            drawableId = field.getInt(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.textViewName.setText(candidate.getName());
        holder.textViewParty.setText("Party: " + candidate.getParty());
        holder.imageView.setImageResource(drawableId);
        tempName=candidate.getName();
//        holder.submit_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(activity, holder.textViewName.getText(),Toast.LENGTH_SHORT).show();
//                String en=Encrypt.encrypt(concat);
//               // String id=votesdatabase.push().getKey();
//             //   votesdatabase.child(id).setValue(en);
//
//              //  Toast.makeText(activity, Encrypt.decrypt(),Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return candidateList.size();
    }

    class CandidateViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewParty, tv_party, tv_candidate,votedfor,tv_temporary;
        ImageView imageView,iv_candidate_pic;
        Button submit_button;
        String concat;
        Intent intent;
        public CandidateViewHolder(@NonNull View itemView, final Activity activity) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_candidate_pic1);
            textViewName = itemView.findViewById(R.id.tv_candidate_name);
            textViewParty = itemView.findViewById(R.id.tv_candidate_party);
            tv_candidate=activity.findViewById(R.id.tv_candidate);
            tv_party=activity.findViewById(R.id.tv_party);
            iv_candidate_pic=activity.findViewById(R.id.iv_candidate_pic);
            votedfor=activity.findViewById(R.id.tv_voted);

            submit_button=activity.findViewById(R.id.submit_button);

            tv_temporary=activity.findViewById(R.id.tv_temporary);

            tv_candidate.setVisibility(View.INVISIBLE);
            tv_party.setVisibility(View.INVISIBLE);
            votedfor.setVisibility(View.INVISIBLE);
            iv_candidate_pic.setVisibility(View.INVISIBLE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    tv_candidate.setVisibility(View.VISIBLE);
                    tv_party.setVisibility(View.VISIBLE);
                    votedfor.setVisibility(View.VISIBLE);
                    iv_candidate_pic.setVisibility(View.VISIBLE);
                    tv_candidate.setText(candidateList.get(pos).getName());
                    tv_party.setText(candidateList.get(pos).getParty());
                    concat= candidateList.get(pos).getCandidateId();
                    tv_temporary.setText(concat);
                    Class res = R.drawable.class;
                    Field field = null;
                    int drawableId=0;
                    try {
                        field = res.getField(concat);
                        drawableId = field.getInt(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    iv_candidate_pic.setImageResource(drawableId);  //as of now HardCoded
                  //  iv_candidate_pic.setIma

                   // Toast.makeText(mCtx, candidateList.get(pos).getName(), Toast.LENGTH_SHORT).show();
                }
            });


            submit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(activity, tv_temporary.getText(),Toast.LENGTH_SHORT).show();
                    String en=Encrypt.encrypt(tv_temporary.getText().toString());
                    String id=votesdatabase.push().getKey();
                    votesdatabase.child(id).setValue(en);
                    intent=new Intent(activity, Confirmation.class);
                    activity.startActivity(intent);



                }
            });
        }
    }
}