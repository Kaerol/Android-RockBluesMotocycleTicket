package com.appliti.ticket.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.appliti.ticket.activity.ScanerActivity;
import com.kodart.httpzoid.HttpResponse;
import com.kodart.httpzoid.NetworkError;
import com.kodart.httpzoid.ResponseHandler;

public class MarkItemsReleasedClient extends RBiMHttpRequest {
    public static final String ORDER_ACTION_DATA = "ORDER_ACTION_DATA";

    public MarkItemsReleasedClient(final Activity activity) {
        super(activity);
    }

    public void released(final int iOrder) {
        http.put(getShopUrl() + "/wydano.php")
                .header("Authorization", RBiMHttpRequest.authorization)
                .data(new CheckCodeData(iOrder))
                .handler(new ResponseHandler() {
                    @Override
                    public void success(final Object data, final HttpResponse response) {
                        if (response.getCode() == 200) {
                            final Context context = getActivity().getBaseContext();
                            final Intent intent = new Intent(context, ScanerActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "error ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void error(final String message, final HttpResponse response) {
                        Toast.makeText(getActivity(), "error ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(final NetworkError error) {
                        Toast.makeText(getActivity(), "failure ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void complete() {
                        Toast.makeText(getActivity(), "complete ", Toast.LENGTH_LONG).show();
                    }
                }).send();
    }

    private static class CheckCodeData {
        private final int order_id;

        private CheckCodeData(int order_id) {
            this.order_id = order_id;
        }
    }
}
