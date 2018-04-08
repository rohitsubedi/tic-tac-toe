package com.rohit.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.rohit.tictactoe.adapters.GameAdapter;
import com.rohit.tictactoe.library.TicTacToe;

import java.util.ArrayList;

public class AIActivity extends AppCompatActivity {
    GridView mainBoard;
    ArrayList<String> values;
    GameAdapter adapter;
    TextView gameResult;
    TicTacToe tictactoe;
    int turn = 1; // 1 for human and 0 for computer
    String human = "x";
    String computer = "o";

    /**
     * Initiate the Board for Tic Tac Toe
     */
    private void initiateBoard() {
        values = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            values.add("");
        }

        adapter = new GameAdapter(this, R.layout.game_grid, values);
        tictactoe = new TicTacToe(this.computer, this.human);

        mainBoard.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    /**
     * Reset Board
     */
    private void resetBoard() {
        for (int i = 0; i < 9; i++) {
            values.set(i, "");
        }

        this.turn = 1;

        gameResult.setVisibility(TextView.GONE);
        adapter.notifyDataSetChanged();
    }

    /**
     * Update Board on each player selection
     *
     * @param position
     */
    public void updateBoard(int position) {
        int gameValue = tictactoe.gameFinished(values);
        if (gameValue == 0) {
            if (values.get(position).isEmpty()) {
                values.set(position, this.turn == 1 ? this.human : this.computer);
                adapter.notifyDataSetChanged();

                this.turn = (this.turn + 1) % 2;
            }
        }

        gameValue = tictactoe.gameFinished(values);

        if (gameValue == 10) {
            gameResult.setText("Computer won");
            gameResult.setVisibility(TextView.VISIBLE);
        } else if (gameValue == -10) {
            gameResult.setText("You won");
            gameResult.setVisibility(TextView.VISIBLE);
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
        setContentView(R.layout.activity_ai);

        mainBoard = (GridView) findViewById(R.id.main_grid);
        gameResult = (TextView) findViewById(R.id.game_result);

        this.initiateBoard();
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
                this.resetBoard();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
