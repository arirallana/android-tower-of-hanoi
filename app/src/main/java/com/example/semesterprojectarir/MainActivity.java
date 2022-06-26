package com.example.semesterprojectarir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Arir Allana
 * @version 1.0
 * The main activity for the application
 *
 * This is the first screen the user sees
 *
 * */

public class MainActivity extends AppCompatActivity {

    /**
     * EditText to enter number of disks and player name
     * Buttons for PLAY, VIEW SCORES AND EXIT
     * TextView for game label
     */
    EditText disks;
    TextView textView;
    TextView player_name;
    Button button;
    Button button2;
    Button button3;

    /**
     *
     * @param savedInstanceState
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView3);
        disks = (EditText)findViewById(R.id.disks);
        player_name = (TextView)findViewById(R.id.player_name);
        button = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             * On click listener for PLAY button
             */
            @Override
            public void onClick(View view) {
                Play();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             * On click listener for VIEW SCORES button
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Scores.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param view
             * On click listener for EXIT button
             */
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });


    }

    /**
     * gets user inut for player name and disks and stores as string
     * creates intent to open Hanoi.java activity
     * displays toast message if disk input is invalid
     */
    public void Play(){
        String myString = "";
        String player = "";
        String[] range_vals = {"3", "4", "5", "6", "7", "8"};
        ArrayList <String> range = new ArrayList<String>();
        range.addAll(Arrays.asList(range_vals));

        if (range.contains(disks.getText().toString())) {
            myString = disks.getText().toString();
            player = player_name.getText().toString();
            Intent intent = new Intent(MainActivity.this, Hanoi.class);
            intent.setType("");
            intent.putExtra(Intent.EXTRA_TEXT, myString);
            intent.putExtra(Intent.EXTRA_SUBJECT, player);
            startActivity(intent);
            disks.setText("");

        } else {
            Toast toast = Toast.makeText(this, "Invalid input.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
