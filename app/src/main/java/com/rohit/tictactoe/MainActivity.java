package com.rohit.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

import com.rohit.tictactoe.adapters.GameAdapter;

public class MainActivity extends AppCompatActivity {
    GridView mainBoard;
    ArrayList<String> values;
    GameAdapter adapter;
    int turn = 1; // 1 for human and 0 for computer

    /**
     * Initiate the Board for Tic Tac Toe
     *
     * @param turn
     */
    private void initiateBoard(int turn) {
        values = new ArrayList<>(Arrays.asList("", "", "", "", "", "", "", "", ""));
        adapter = new GameAdapter(this, R.layout.game_grid, values);
        this.turn = turn;

        mainBoard.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * Update Board on each player selection
     *
     * @param position
     */
    public void updateBoard(int position) {
        if (values.get(position).isEmpty()) {
            values.set(position, this.turn == 1 ? "x" : "o");
            adapter.notifyDataSetChanged();

            //this.turn = (this.turn + 1) % 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBoard = (GridView) findViewById(R.id.main_grid);

        this.initiateBoard(turn);
    }

    /**
     * Check if the turn is human or not
     *
     * @return Boolean
     */
    public boolean isHumanTurn() {
        return this.turn == 1;
    }
}
