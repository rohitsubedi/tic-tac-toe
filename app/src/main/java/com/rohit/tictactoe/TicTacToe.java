package com.rohit.tictactoe;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by developer on 03/04/18.
 */

class TicTacToe {
    private String player;
    private String oponent;
    private int boardSize;

    TicTacToe(String player, String oponent, int boardSize) {
        this.player = player;
        this.oponent = oponent;
        this.boardSize = boardSize;
    }

    /**
     * Check if there are any moves left
     *
     * @param values
     *
     * @return boolean
     */
    boolean movesLeft(ArrayList<String> values) {
        return values.contains("");
    }

    /**
     * Check if game is finished or not
     *
     * @param values
     * @return
     */
    boolean gameFinished(ArrayList<String> values) {
        int winScore = this.winScore(values);

        return winScore == 10 || winScore == -10;
    }

    /**
     * Get win score depending on player or openent
     *
     * @param values
     * @return
     */
    private int winScore(ArrayList<String> values) {
        // Check Row for win Condition for x or o
        for (int i = 0; i < this.boardSize; i++) {
            boolean allSame = true;

            for (int j = 0; j < this.boardSize; j++) {
                if (!values.get(i * this.boardSize).equals(values.get((i * this.boardSize) + j))) {
                    allSame = false;
                }
            }

            if (allSame) {
                if (values.get(i * this.boardSize).equals(this.player)) {
                    return 10;
                } else if (values.get(i * this.boardSize).equals(this.oponent)) {
                    return -10;
                }
            }
        }

        // Check Column for win Condition for x or o
        for (int i = 0; i < this.boardSize; i++) {
            boolean allSame = true;

            for (int j = 0; j < this.boardSize; j++) {
                if (!values.get(i).equals(values.get((j * this.boardSize) + i))) {
                    allSame = false;
                }
            }

            if (allSame) {
                if (values.get(i).equals(this.player)) {
                    return 10;
                } else if (values.get(i).equals(this.oponent)) {
                    return -10;
                }
            }
        }

        // Check Diagonal for win Condition for x or o
        if (values.get(0).equals(values.get(4)) && values.get(4).equals(values.get(8))) {
            if (values.get(0).equals(this.player)) {
                return 10;
            } else if (values.get(0).equals(this.oponent)) {
                return -10;
            }
        }

        if (values.get(2).equals(values.get(4)) && values.get(4).equals(values.get(6))) {
            if (values.get(2).equals(this.player)) {
                return 10;
            } else if (values.get(2).equals(this.oponent)) {
                return -10;
            }
        }

        return 0;
    }

    /**
     * Minimax Algorithm to determine the best position
     *
     * @param values
     * @param isMax
     * @return int
     */
    private int miniMaxAlgorithm(ArrayList<String> values, boolean isMax) {
        int winScore = this.winScore(values);

        if (winScore == 10) {
            return winScore;
        }

        if (winScore == -10) {
            return winScore;
        }

        if (!this.movesLeft(values)) {
            return 0;
        }

        if (isMax) {
            int best = -1000;

            for (int i = 0; i < Math.pow(this.boardSize, 2); i++) {
                if (values.get(i).isEmpty()) {
                    values.set(i, this.player);

                    best = Math.max(best, this.miniMaxAlgorithm(values, false));

                    values.set(i, "");
                }
            }

            return best;
        } else {
            int best = 1000;

            for (int i = 0; i < Math.pow(this.boardSize, 2); i++) {
                if (values.get(i).isEmpty()) {
                    values.set(i, this.oponent);

                    best = Math.min(best, this.miniMaxAlgorithm(values, true));

                    values.set(i, "");
                }
            }

            return best;
        }
    }

    /**
     * Find best position relative to each position
     *
     * @param values
     * @return int
     */
    int findBestMove(ArrayList<String> values)
    {
        int bestPosition = -1;
        int bestValue = -1000;

        for (int i = 0; i < Math.pow(this.boardSize, 2); i++) {
            if (values.get(i).isEmpty()) {
                values.set(i, this.player);

                int moveValue = this.miniMaxAlgorithm(values, false);

                values.set(i, "");

                if (moveValue > bestValue) {
                    bestValue = moveValue;
                    bestPosition = i;
                }

            }
        }

        return bestPosition;
    }
}
