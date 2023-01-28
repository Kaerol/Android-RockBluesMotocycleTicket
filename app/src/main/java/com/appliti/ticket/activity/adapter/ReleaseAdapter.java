package com.appliti.ticket.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appliti.ticket.R;
import com.appliti.ticket.model.Position;

import java.util.List;

public class ReleaseAdapter extends ArrayAdapter<Position> {

    public ReleaseAdapter(final Context context, final List<Position> positions) {
        super(context, 0, positions);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_release, parent, false);
        }

        if (position % 2 == 1) {
            listItemView.setBackgroundResource(R.color.colorLightGrey);
        } else {
            listItemView.setBackgroundResource(R.color.colorLight);
        }

        final Position release = getItem(position);

        final TextView itemName = (TextView) listItemView.findViewById(R.id.tv_item_name);
        itemName.setText(release.getItemName());

        final TextView itemCount = (TextView) listItemView.findViewById(R.id.tv_count);
        itemCount.setText(String.valueOf(release.getCount()));

        return listItemView;
    }
}