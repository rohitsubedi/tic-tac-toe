package com.rohit.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

import com.rohit.tictactoe.adapters.GameAdapter;

public class MainActivity extends AppCompatActivity {
    GridView mainBoard;
    ArrayList<String> values;
    GameAdapter adapter;
    TicTacToe tictactoe;
    int turn = 1; // 1 for human and 0 for computer
    String human = "x";
    String computer = "o";
    int boardSize = 3;

    /**
     * Initiate the Board for Tic Tac Toe
     *
     * @param turn
     */
    private void initiateBoard(int turn) {
        values = new ArrayList<>();

        for (int i = 0; i < Math.pow(this.boardSize, 2); i++) {
            values.add("");
        }

        adapter = new GameAdapter(this, R.layout.game_grid, values);
        tictactoe = new TicTacToe(this.computer, this.human, this.boardSize);
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
        if (!tictactoe.gameFinished(values)) {
            if (values.get(position).isEmpty()) {
                values.set(position, this.turn == 1 ? this.human : this.computer);
                adapter.notifyDataSetChanged();

                this.turn = (this.turn + 1) % 2;
            }
        }
    }

    /**
     * Computer turn to play
     */
    public void initiateComputerTurn() {
        if (tictactoe.movesLeft(values)) {
            int bestPosition = tictactoe.findBestMove(values);

            this.updateBoard(bestPosition);
        }
    }

    /**
     * Check if the turn is human or not
     *
     * @return Boolean
     */
    public boolean isHumanTurn() {
        return this.turn == 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBoard = (GridView) findViewById(R.id.main_grid);

        mainBoard.setNumColumns(this.boardSize);
        this.initiateBoard(turn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.refresh:
                this.initiateBoard(this.turn);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
