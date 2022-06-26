package com.example.semesterprojectarir;

/**
 *
 * @author Arir Allana
 * @version 1.0
 * The class for Score
 * This is used by Scores.java class to create score objects to pass in firebase queries
 * */

public class Score {

    String player;
    int disks;
    int turns;

    /**
     * initialize without any parameters
     */
    public Score() {

    }

    /**
     * initialize with player name as String, int values for disks and turns
     * @param player
     * @param disks
     * @param turns
     */
    public Score(String player, int disks, int turns) {
        this.player = player;
        this.disks = disks;
        this.turns = turns;
    }

    /**
     *
     * @return returns player name
     */
    public String getPlayer() {
        return player;
    }

    /**
     *
     * @return returns number of disks
     */
    public int getDisks() {
        return disks;
    }

    /**
     *
     * @return  returns number of turns
     */
    public int getTurns() {
        return turns;
    }
}
