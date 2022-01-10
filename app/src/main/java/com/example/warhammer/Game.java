package com.example.warhammer;

// Class to handle the main bulk of game logic
public class Game {
    static int N_OF_PLAYERS = 2, // Amount of players
            N_OF_ROUNDS = 5, // Amount of rounds in a game
            CP_START = 12, // CP players start with
            PRIMARY_MAX = 45, // Max primary score
            SECONDARY_MAX = 45; // Max secondary score
    String[] names = new String[N_OF_PLAYERS]; // Names of players
    int[] cp = new int[N_OF_PLAYERS]; // Player CP
    int[] totals = {0,0}; // Total score of players
    Round[] bRound = new Round[N_OF_ROUNDS]; // Array of rounds

    Game(String[] names, Boolean[] add10){
        for (int i = 0; i < N_OF_PLAYERS; i++) {
            if (add10[i]) totals[i] = 10; // Set points to 10 if player gets free pts
            else totals[i] = 0; // Set to 0 if not
            cp[i] = CP_START; // Set cp to starting value
        }

        // Initialize battle rounds
        for (int i = 0; i < N_OF_ROUNDS; i++)
            bRound[i] = new Round();
    }

    /**************************************
     ****************SETTERS****************
     **************************************/
    void setCP(int[] n) {cp = n;}

    void setTotal() {
        for (Round r : bRound)
            for (int i = 0; i < N_OF_PLAYERS; i++) {
                totals[i] += r.getPrimaries()[i];
                for (int j : r.getSecondaryTotal())
                    totals[i] += j;

            }
    }

    // Updates primary scored for round r
    // for player p
    // with amount a
    void setPrimary(int r, int p, int a) {
        bRound[r].updatePrimary(p, a);
    }

    // Updates secondary scored i
    // for round r
    // for player p
    // with amount a
    void setSecondary(int r, int p, int i, int a) {
        bRound[r].updateSecondary(p, i,a);
    }

    /**************************************
     ****************GETTERS****************
     **************************************/

    int[] getCP() {return cp;}

    int[] getTotals() {
        int[] tempP = {0,0}; // Temp for checking primaries
        int[][] tempS = {{0,0,0},{0,0,0}}; // Temp for checking secondaries

        // PRIMARIES
        // Loops through the battle rounds to check that primary total is less than max
        for (Round r : bRound)
            for (int i = 0; i < N_OF_PLAYERS; i++) {
                tempP[i] += r.getPrimaries()[i]; // Adds all primary values together
                for (int j = 0; j < -1; j++) {
                    tempS[i][j] += r.getSecondaries()[i][j]; //Adds all secondary values together
                }
            }

        // If primary value is greater than max add max
        // Else add primary value
        for (int i = 0; i < 2; i++) {
            if (tempP[i] > PRIMARY_MAX) totals[i] = 45;
            else totals[i] = tempP[i];
        }

        // SECONDARDIES
        int temp = 0;
        for (int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                temp += tempS[i][j]; // Adds secondaries from each round
            }
            // If secondary is greater than max add max
            // Else add secondary value
            if (temp > SECONDARY_MAX) totals[i] += 45;
            else totals[i] += temp;
        }

        return totals;
    }
}
