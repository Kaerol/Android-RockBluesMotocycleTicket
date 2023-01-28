package com.appliti.ticket.activity.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appliti.ticket.R;

import java.util.List;

public class PersonDataAdapter extends ArrayAdapter<String> {

    public PersonDataAdapter(final Activity context, final List<String> data) {
        super(context, 0, data);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_person, parent, false);
        }

        final String data = getItem(position);

        final TextView description = (TextView) listItemView.findViewById(R.id.tv_text);
        description.setText(data);

        return listItemView;
    }
}