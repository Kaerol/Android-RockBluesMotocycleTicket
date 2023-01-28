package com.appliti.ticket.activity.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appliti.ticket.R;
import com.appliti.ticket.activity.ReleaseActivity;
import com.appliti.ticket.activity.adapter.CommentAdapter;
import com.appliti.ticket.service.response.Comment;

import java.util.List;


public class FragmentReleaseComments extends Fragment {

    private View view;
    private ReleaseActivity activity;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_comments_release, container, false);

        activity = (ReleaseActivity) getActivity();
        fillReport(activity.getComments());

        return view;
    }

    private void fillReport(final List<Comment> comments) {
        if (!comments.isEmpty()) {
            final ListView commentItemsView = view.findViewById(R.id.coments_items);
            final CommentAdapter commentItemsAdapter = new CommentAdapter(activity, comments);
            commentItemsView.setAdapter(commentItemsAdapter);
        }
    }
}