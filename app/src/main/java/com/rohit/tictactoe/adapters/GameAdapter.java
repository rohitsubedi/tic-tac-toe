package com.rohit.tictactoe.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rohit.tictactoe.AIActivity;
import com.rohit.tictactoe.R;

import java.util.ArrayList;

/**
 * Created by developer on 03/04/18.
 */

public class GameAdapter extends ArrayAdapter<String> {
    protected Context context;
    protected int resource;
    protected ArrayList<String> values;

    public GameAdapter(Context context, int resource, ArrayList<String> values) {
        super(context, resource, values);

        this.context = context;
        this.resource = resource;
        this.values = values;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(this.resource, parent, false);

        RelativeLayout box = (RelativeLayout) rowView.findViewById(R.id.each_grid);
        final String value = this.values.get(position);
        TextView text = (TextView) rowView.findViewById(R.id.grid_value);

        text.setText(String.valueOf(value));
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((AIActivity) context).isHumanTurn() && values.get(position).isEmpty()) {
                    ((AIActivity) context).updateBoard(position);

                    ((AIActivity) context).initiateComputerTurn();
                }
            }
        });

        return rowView;
    }
}
