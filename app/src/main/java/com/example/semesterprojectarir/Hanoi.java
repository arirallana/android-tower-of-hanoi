package com.example.semesterprojectarir;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedHashMap;
import java.util.Map;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Arir Allana
 * @version 1.0
 *
 * The activity that runs the game
 * This is seen by user after PLAY button is pressed
 *
 * */
public class Hanoi extends AppCompatActivity {
    /**
     * integers for number of disks, turns, source stack, destination stack, previous source stack, previous destinationn stack
     * booleans for source stack being clicked, game won,
     * Linked stack of size 3
     * String for player name
     */
    private int disks = 0;
    private int turns = 0;
    private int source = 0;
    private int destination = 0;
    private int source_0 = 0;
    private int destination_0 = 0;
    private boolean source_click = false;
    private boolean win = false;
    private LL[] linked_stack = new LL[3];
    private String player = "NoName";

    /**
     * creation of firebase database object
     * creation of reference to the child node 'Scores'
     */

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Scores");

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanoi);

        /**
         * creates intent to get strings from MainActivity.java
         */
        Intent intent = getIntent();
        disks = Integer.parseInt(Objects.requireNonNull(intent.getExtras()).get(Intent.EXTRA_TEXT) + "");
        player = Objects.requireNonNull(intent.getExtras()).get(Intent.EXTRA_SUBJECT) + "";

        /**
         * creates new linked stack oject for given range
         */
        for (int node = 0; node < 3; node++) {
            linked_stack[node] = new LL();
        }
        /**
         * adds nodes to first node of linked stack from given range
         */
        for (int disk_num = 1; disk_num < disks + 1; disk_num++) {
            linked_stack[0].add(disk_num);
        }
        /**
         * runs show() method
         */
        show();
    }

    /**
     * creates a score object of the Score class with parameters: player name, number of disks and turns
     * accesses 'Score' firebase reference and pushes score object as new node with unique ID
     */
    public void WriteInfotoFirebase (){
        Score score = new Score(player,  disks, turns);
        myRef.push().setValue(score);
    }

    /**
     *
     * @param view
     * sets source_click to true if rod 1 has been clicked
     * otherwise sets destination to rod 1 and runs begin() method
     */
    public void rod1(View view){
        if (source_click == false){
            source = 1;
            source_click = true;
        }
        else{
            destination = 1;
            begin();
        }
    }

    /**
     *
     * @param view
     * sets source_click to true if rod 2 has been clicked
     * otherwise sets destination to rod 2 and runs begin() method
     */
    public void rod2(View view){
        if (source_click == false){
            source = 2;
            source_click = true;
        }
        else{
            destination = 2;
            begin();
        }
    }

    /**
     *
     * @param view
     * sets source_click to true if rod 3 has been clicked
     * otherwise sets destination to rod 3 and runs begin() method
     */
    public void rod3(View view){
        if (source_click == false){
            source = 3;
            source_click = true;
        }
        else{
            destination = 3;
            begin();
        }
    }

    /**
     * creates new array list for disk images in frame layout
     * creates new linked hashmap of disk images in drawable
     */
    public void show() {
        Map<Integer, Integer> myMap = new LinkedHashMap<>();
        List<List<ImageView>> myList = new ArrayList<>();

        /**
         * adds new arraylist objects in given range
         */
        for (int node = 0; node < 3; node++) {
            myList.add(new ArrayList<ImageView>());
        }

        /**
         * adds new imageviews to arraylist for each rod: 0, 1, 2
         * all possible combinations denoted as : r+rod_index+d_disk_index
         */

        myList.get(2).add((ImageView)findViewById(R.id.r3d8));
        myList.get(2).add((ImageView)findViewById(R.id.r3d7));
        myList.get(2).add((ImageView)findViewById(R.id.r3d6));
        myList.get(2).add((ImageView)findViewById(R.id.r3d5));
        myList.get(2).add((ImageView)findViewById(R.id.r3d4));
        myList.get(2).add((ImageView)findViewById(R.id.r3d3));
        myList.get(2).add((ImageView)findViewById(R.id.r3d2));
        myList.get(2).add((ImageView)findViewById(R.id.r3d1));
        myList.get(1).add((ImageView)findViewById(R.id.r2d8));
        myList.get(1).add((ImageView)findViewById(R.id.r2d7));
        myList.get(1).add((ImageView)findViewById(R.id.r2d6));
        myList.get(1).add((ImageView)findViewById(R.id.r2d5));
        myList.get(1).add((ImageView)findViewById(R.id.r2d4));
        myList.get(1).add((ImageView)findViewById(R.id.r2d3));
        myList.get(1).add((ImageView)findViewById(R.id.r2d2));
        myList.get(1).add((ImageView)findViewById(R.id.r2d1));
        myList.get(0).add((ImageView)findViewById(R.id.r1d8));
        myList.get(0).add((ImageView)findViewById(R.id.r1d7));
        myList.get(0).add((ImageView)findViewById(R.id.r1d6));
        myList.get(0).add((ImageView)findViewById(R.id.r1d5));
        myList.get(0).add((ImageView)findViewById(R.id.r1d4));
        myList.get(0).add((ImageView)findViewById(R.id.r1d3));
        myList.get(0).add((ImageView)findViewById(R.id.r1d2));
        myList.get(0).add((ImageView)findViewById(R.id.r1d1));

        /**
         * maps disk images from drawable
         */
        myMap.put(0, null);
        myMap.put(1, R.drawable.disk8);
        myMap.put(2, R.drawable.disk7);
        myMap.put(3, R.drawable.disk6);
        myMap.put(4, R.drawable.disk5);
        myMap.put(5, R.drawable.disk4);
        myMap.put(6, R.drawable.disk3);
        myMap.put(7, R.drawable.disk2);
        myMap.put(8, R.drawable.disk1);

        /**
         * for each rod checks if linked stack is not empty
         * gets disk image indices from hash map and sets them to rod indexed by arraylist for given node values of linked list
         */
        if (!linked_stack[0].isEmpty()) {
            for (int stack_len = linked_stack[0].len() - 1; stack_len >= 0; stack_len--) {
                myList.get(0).get(stack_len).setImageResource(myMap.get(linked_stack[0].get(stack_len)));
            }
        } else {
            for (int array_index = 0; array_index < myList.get(0).size(); array_index++) {
                myList.get(0).get(array_index).setImageResource(0);
            }
        }
        if (!linked_stack[1].isEmpty()) {
            for (int stack_len = linked_stack[1].len() - 1; stack_len >= 0; stack_len--) {
                myList.get(1).get(stack_len).setImageResource(myMap.get(linked_stack[1].get(stack_len)));
            }
        } else {
            for (int array_index = 0; array_index < myList.get(1).size(); array_index++) {
                myList.get(1).get(array_index).setImageResource(0);
            }
        }
        if (!linked_stack[2].isEmpty()) {
            for (int stack_len = linked_stack[2].len() - 1; stack_len >= 0; stack_len--) {
                myList.get(2).get(stack_len).setImageResource(myMap.get(linked_stack[2].get(stack_len)));
            }
        } else {
            for (int array_index = 0; array_index < myList.get(2).size(); array_index++) {
                myList.get(2).get(array_index).setImageResource(0);
            }
        }
    }

    /**
     * checks if source node in linked stack is not empty;
     *      then checks if destination node is empty, if so runs the turn() method passing source and destination index;
     *      otherwise checks if source node value is greater than destination node value, if so runs the turn() method passing source and destination index;
     *      if not runs the wrong() method to notify wrong move or win.
     * if source node is empty runs the wrong() method to notify wrong move or win
     */
    public void begin() {
        source_click = false;
        if (!linked_stack[source - 1].isEmpty()) {
            if (linked_stack[destination - 1].isEmpty()) {
                turn(linked_stack, source, destination);
            } else if (linked_stack[source - 1].view() > linked_stack[destination - 1].view()) {
                turn(linked_stack, source, destination);
            } else {
                wrong();
            }
        } else {
            wrong();
        }

        /**
         * displays disks using node values from linked stack
         */
        show();

        /**
         * makes current source as previous source
         * makes current destination as previous destination
         */
        source_0 = source;
        source = 0;
        destination_0 = destination;
        destination = 0;

        /**
         * if number of disks becomes equal to value of last node in linked stack then unhides game complete button and sets win as true
         */
        if (disks == linked_stack[linked_stack.length - 1].len()) {
            Button rp = findViewById(R.id.replay);
            rp.setVisibility(View.VISIBLE);
            WriteInfotoFirebase ();
            win = true;
            rp.setText("You completed in " + turns + " turns\n Click here to replay.");
        }
    }

    /**
     *
     * @param ll
     * @param source_node
     * @param destination_node
     *
     * takes linked stack, source node index and destination node index
     * pops value from source node and add it to destination node
     *
     * if win is true then shows notifiaction via wrong() method
     */
    public void turn(LL[] ll, int source_node, int destination_node) {
        if (win == false) {
            ll[destination_node - 1].add(ll[source_node - 1].remove());
            turns++;
        }
        else{
            wrong();

        }
    }

    /**
     *
     * @param view
     * reverts to MainActivity.java (HOME) screen
     */
    public void start_again(View view) {
        finish();
    }

    /**
     *
     * @param view
     * creates new linked stack
     * adds all values to first node
     */
    public void clear_moves(View view){
        source_click = false;
        for (int node = 0; node < 3; node++) {
            linked_stack[node] = new LL();
        }
        for (int disk_num = 1; disk_num < disks + 1; disk_num++) {
            linked_stack[0].add(disk_num);
        }

        /**
         * displays disks using node values from linked stack
         */
        show();
        win = false;
        turns = 0;

        /**
         * creates and hides game complete button
         */
        Button rp = findViewById(R.id.replay);
        rp.setVisibility(View.INVISIBLE);

        /**
         * makes current source as previous source
         * makes current destination as previous destination
         */
        source_0 = source;
        source = 0;
        destination_0 = destination;
        destination = 0;
    }

    /**
     *
     * @param view
     * reverts to MainActivity.java (HOME) screen
     */
    public void rods_change(View view){
        finish();
    }

    /**
     *
     * @param view
     *
     *
     * pops value from destination node and tries to add it to source node
     * if win is true: runs wrong() method, makes current source as previous source, makes current destination as previous destination
     * @exception Exception
     *                 runs wrong() method, makes current source as previous source, makes current destination as previous destination
     */
    public void reverse_move(View view) {
        source_click = false;
        if (win == false) {
            try {
                linked_stack[source_0 - 1].add(linked_stack[destination_0 - 1].remove());
                turns--;
                show();
                source_0 = source;
                source = 0;
                destination_0 = destination;
                destination = 0;
            } catch (Exception e) {
                wrong();
                source_0 = source;
                source = 0;
                destination_0 = destination;
                destination = 0;
            }
        } else {
            wrong();
            source_0 = source;
            source = 0;
            destination_0 = destination;
            destination = 0;
        }
    }

    /**
     * sets source rod clicked as false
     * if win in false shows wrong move messaage
     * else shows congratulations message
     * makes current source as previous source, makes current destination as previous destination
     */
    public void wrong() {
        source_click = false;
        if (win == false) {
            Toast toast = Toast.makeText(this, "Wrong Move", Toast.LENGTH_SHORT);
            toast.show();
            source_0 = source;
            source = 0;
            destination_0 = destination;
            destination = 0;
        }
        else{
            Toast toast = Toast.makeText(this, "Congratulations, you have won! Hit REPLAY to start another game.", Toast.LENGTH_SHORT);
            toast.show();
            source_0 = source;
            source = 0;
            destination_0 = destination;
            destination = 0;
        }
    }
}
