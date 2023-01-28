package com.appliti.ticket.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appliti.ticket.R;
import com.appliti.ticket.service.response.Comment;

import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {

    public CommentAdapter(final Context context, final List<Comment> comments) {
        super(context, 0, comments);
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_comment, parent, false);
        }

        if (position % 2 == 1) {
            listItemView.setBackgroundResource(R.color.colorLightGrey);
        } else {
            listItemView.setBackgroundResource(R.color.colorLight);
        }

        final Comment comment = getItem(position);

        final TextView author = (TextView) listItemView.findViewById(R.id.comment_author);
        author.setText(comment.getAuthor());

        final TextView text = (TextView) listItemView.findViewById(R.id.comment_text);
        text.setText(comment.getContent());

        final TextView date = (TextView) listItemView.findViewById(R.id.comment_date);
        date.setText(comment.getDate());

        return listItemView;
    }
}