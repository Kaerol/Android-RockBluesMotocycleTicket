package com.appliti.ticket.activity.fragment;

import android.os.Bundle;
import android.app.Fragment;
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


public class FragmentReportWaiting extends Fragment {

    private View view;
    private ReportActivity activity;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_waiting_report, container, false);

        activity = (ReportActivity) getActivity();
        fillReport(activity.getWaitingData());

        return view;
    }

    private void fillReport(final List<Product> waiting) {
        final ArrayList<Position> waitingItems = new ArrayList<Position>();

        if (!waiting.isEmpty()) {
            for (final Product product : waiting) {
                waitingItems.add(new Position(product.getName(), product.getCount()));
            }
        }
        final ListView waitingItemsView = view.findViewById(R.id.waiting_data);
        final ReleaseAdapter waitingItemsAdapter = new ReleaseAdapter(view.getContext(), waitingItems);
        waitingItemsView.setAdapter(waitingItemsAdapter);
    }
}