package com.example.semesterprojectarir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 *
 * @author Arir Allana
 * @version 1.0
 * The activity that displays player scores after getting from database
 * This is seen by user after VIEW SCORES button is pressed
 * */

public class Scores extends AppCompatActivity {
    TextView textView;
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textView = (TextView)findViewById(R.id.textView4);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        myRef.child("Scores")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    /**
                     * @param dataSnapshot
                     * On data change listener for 'Score' child node in firebase
                     * passes score object to get values of player, disks and turns from firebase query
                     */
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Score score = snapshot.getValue(Score.class);
                            textView.append(score.player+"\n"+score.disks+"\n"+score.turns+"\n\n");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }


}