package com.appliti.ticket.activity.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.appliti.ticket.R;
import com.appliti.ticket.activity.ReleaseActivity;
import com.appliti.ticket.activity.adapter.PersonDataAdapter;
import com.appliti.ticket.activity.adapter.ReleaseAdapter;
import com.appliti.ticket.model.Position;
import com.appliti.ticket.service.MarkItemsReleasedClient;
import com.appliti.ticket.util.LocalStorage;
import com.dhruv.timerbutton.ButtonAnimationListener;
import com.dhruv.timerbutton.TimerButton;

import java.util.List;


public class FragmentReleaseData extends Fragment {

    private View view;
    private ReleaseActivity activity;
    private TimerButton timerButton;
    private Button btnRelease;
    private Button checkButton;
    private LinearLayout buttonsLayout;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data_release, container, false);

        activity = (ReleaseActivity) getActivity();
        fillReport(activity.getProducts(), activity.getPersonData());

        timerButton = view.findViewById(R.id.timer_button);
        btnRelease = (Button) view.findViewById(R.id.btnRelease);
        checkButton = (Button) view.findViewById(R.id.checkButton);
        buttonsLayout = (LinearLayout) view.findViewById(R.id.buttonsLayout);
        final LinearLayout.LayoutParams lay = (LinearLayout.LayoutParams) buttonsLayout.getLayoutParams();

        if (activity.isReleasedMode()) {
            lay.weight = 3f;

            timerButton.setVisibility(View.VISIBLE);
            btnRelease.setVisibility(View.GONE);
            checkButton.setVisibility(View.GONE);
            timerButton.setDuration(Long.parseLong(LocalStorage.receive(LocalStorage.RELEASE_TIME, "6000", activity)));

            timerButton.startAnimation();
            timerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    final MarkItemsReleasedClient itemsReleased = new MarkItemsReleasedClient(activity);
                    itemsReleased.released(activity.getOrderId());
                }
            });
            timerButton.setButtonAnimationListener(new ButtonAnimationListener() {
                @Override
                public void onAnimationEnd() {
                    final MarkItemsReleasedClient itemsReleased = new MarkItemsReleasedClient(activity);
                    itemsReleased.released(activity.getOrderId());
                }

                @Override
                public void onAnimationReset() {

                }

                @Override
                public void onAnimationStart() {

                }
            });
        } else {
            lay.weight = 1f;

            timerButton.setVisibility(View.GONE);
            btnRelease.setVisibility(View.VISIBLE);
            checkButton.setVisibility(View.VISIBLE);

            btnRelease.setText(R.string.btn_release_done);
            btnRelease.setBackgroundResource(R.color.colorAccent);
            btnRelease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    Toast.makeText(activity, "PRZEDMIOTY WYDANO - sprawdź dokument.", Toast.LENGTH_LONG).show();
                }
            });
        }
        return view;

    }

    private void fillReport(final List<Position> positions, final List<String> personData) {
        if (!positions.isEmpty()) {
            final ListView basketItemsView = view.findViewById(R.id.basket_items);
            final ReleaseAdapter basketItemsAdapter = new ReleaseAdapter(activity, positions);
            basketItemsView.setAdapter(basketItemsAdapter);
        }
        if (!personData.isEmpty()) {
            final ListView personDataView = view.findViewById(R.id.person_data);
            final PersonDataAdapter personDataAdapter = new PersonDataAdapter(activity, personData);
            personDataView.setAdapter(personDataAdapter);
        }
    }
}