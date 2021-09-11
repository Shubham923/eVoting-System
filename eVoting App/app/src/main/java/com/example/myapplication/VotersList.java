package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class VotersList extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
//    String mTitle[] = { "Whatsapp", "Twitter", "Instagram", "Youtube"};
//    String mDescription[] = { "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description"};
//    int images[] = { R.drawable.whatsapp, R.drawable.twitter, R.drawable.instagram, R.drawable.youtube};
    String mTitle[] = { "Arvind Kejriwal", "Narendra Modi", "Rahul Gandhi"};
    String mDescription[] = { "AAP", "BJP","INC"};
    int images[] = { R.drawable.c21103, R.drawable.c21101, R.drawable.c21102};
    RadioGroup mRgAllButtons;
    String TAG = "VotersList";
    TextView tv_candidate;
    TextView tv_party;
    TextView tv_voted;
    ImageView iv_candidate_pic;
    Button submitbtn;

    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voters_list);

        listView = findViewById(R.id.listView);

        // now create an adapter class

      //  addRadioButtons(4);
        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        tv_candidate=findViewById(R.id.tv_candidate);
        tv_party=findViewById(R.id.tv_party);
        tv_voted=findViewById(R.id.tv_voted);
        iv_candidate_pic=findViewById(R.id.iv_candidate_pic);
        tv_voted.setVisibility(View.INVISIBLE);

        tv_party.setVisibility(View.INVISIBLE);

        tv_candidate.setVisibility(View.INVISIBLE);

        iv_candidate_pic.setVisibility(View.INVISIBLE);

        submitbtn=findViewById(R.id.submit_button);
        submitbtn.setEnabled(false);
        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                listView.setItemChecked(position,true); //Don't make the same mistake I did by calling this function before setting the listview adapter.
                view.setSelected(true);
                listView.setSelection(position);
//                if (position ==  0) {
//                    Toast.makeText(VotersList.this, "Facebook Description", Toast.LENGTH_SHORT).show();
//                }
//                if (position ==  1) {
//                    Toast.makeText(VotersList.this, "Whatsapp Description", Toast.LENGTH_SHORT).show();
//                }
//                if (position ==  2) {
//                    Toast.makeText(VotersList.this, "Twitter Description", Toast.LENGTH_SHORT).show();
//                }
//                if (position ==  3) {
//                    Toast.makeText(VotersList.this, "Instagram Description", Toast.LENGTH_SHORT).show();
//                }
//                if (position ==  4) {
//                    Toast.makeText(VotersList.this, "Youtube Description", Toast.LENGTH_SHORT).show();
//                }

                tv_candidate.setVisibility(View.VISIBLE);
                tv_candidate.setText(mTitle[position]);
                tv_party.setVisibility(View.VISIBLE);
                tv_party.setText(mDescription[position]);
                tv_voted.setVisibility(View.VISIBLE);
                iv_candidate_pic.setVisibility(View.VISIBLE);
               // iv_candidate_pic.setImageAlpha(images[position]);
                iv_candidate_pic.setImageResource(images[position]);
                submitbtn.setEnabled(true);

            }
        });
        // so item click is done now check list view


        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Response Saved", Toast.LENGTH_SHORT).show();
            }
        });



    }


 /*   public void addRadioButtons() {
        mRgAllButtons.setOrientation(LinearLayout.HORIZONTAL);
        //
//        for (int i = 1; i <= number; i++) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(View.generateViewId());
            rdbtn.setText("Radio " + rdbtn.getId());
            rdbtn.setOnClickListener(this);
            mRgAllButtons.addView(rdbtn);
//        }
    }
*/
    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            System.out.println(position);
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);
            return row;
        }
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, " Name " + ((RadioButton)v).getText() +" Id is "+v.getId());
    }
}
