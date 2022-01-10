package com.example.warhammer;

// Class to handle individual rounds and scoring
public class Round {
    static int PRIMARY_VALUE = 5; // Constant for primary value
    int[] primaries = {0,0}; // Array for scoring primaries
    int[][] secondaries = {{0,0,0}, {0,0,0}}; // 2D array for scoring secondaries

    // Constructor
    Round() {

    }

    // Used to update primary value for round
    void updatePrimary(int player, int amount) {
        int temp = amount * PRIMARY_VALUE;
        primaries[player] = temp;
    }

    // Used to update secondary values for round
    void updateSecondary(int player, int index, int amount) {
        secondaries[player][index] = amount;
    }

    // Returns the array of player primaries
    int[] getPrimaries(){
        return primaries; }

    // Returns the array of player secondaries
    int[][] getSecondaries(){
        return secondaries;
    }

    // Adds secondary values together
    int[] getSecondaryTotal() {
        int[] temp = new int[secondaries.length];

        for (int i = 0; i < secondaries.length; i++)
            for (int j = 0; j < 3; j++) {
                temp[i] += secondaries[i][j];
            }

        return temp;
    }
}
