package com.appliti.ticket.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appliti.ticket.R;
import com.appliti.ticket.activity.ReportActivity;
import com.appliti.ticket.activity.adapter.ReleaseAdapter;
import com.appliti.ticket.model.Position;
import com.appliti.ticket.service.response.Product;

import java.util.ArrayList;
import java.util.List;


public class FragmentReportReleased extends Fragment {

    private View view;
    private ReportActivity activity;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_released_report, container, false);

        activity = (ReportActivity) getActivity();
        fillReport(activity.getReleasedData());

        return view;
    }

    private void fillReport(final List<Product> released) {
        final ArrayList<Position> releasedItems = new ArrayList<Position>();

        if (!released.isEmpty()) {
            for (final Product product : released) {
                releasedItems.add(new Position(product.getName(), product.getCount()));
            }
        }

        final ListView waitingItemsView = view.findViewById(R.id.released_data);
        final ReleaseAdapter waitingItemsAdapter = new ReleaseAdapter(view.getContext(), releasedItems);
        waitingItemsView.setAdapter(waitingItemsAdapter);
    }
}