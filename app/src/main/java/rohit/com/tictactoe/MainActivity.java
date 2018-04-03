package rohit.com.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

import rohit.com.tictactoe.adapters.GameAdapter;

public class MainActivity extends AppCompatActivity {
    GridView mainBoard;
    ArrayList<String> values;
    GameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBoard = (GridView) findViewById(R.id.main_grid);
        values = new ArrayList<>(Arrays.asList("", "", "", "", "", "", "", "", ""));
        adapter = new GameAdapter(this, R.layout.game_grid, values);

        mainBoard.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
