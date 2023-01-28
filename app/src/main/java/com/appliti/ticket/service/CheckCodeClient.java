package com.appliti.ticket.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.appliti.ticket.activity.ReleaseActivity;
import com.google.gson.Gson;
import com.kodart.httpzoid.HttpResponse;
import com.kodart.httpzoid.NetworkError;
import com.kodart.httpzoid.ResponseHandler;

import java.util.HashMap;
import java.util.Map;

public class CheckCodeClient extends RBiMHttpRequest {
    private static final String HASH = "hash";
    private static final String ACTION = "action";
    public static String CHECK_CODE_DATA = "CHECK_CODE_DATA";

    private final Map<String, String> params;

    public CheckCodeClient(final Activity activity, final String code) {
        super(activity);
        params = new HashMap<>();
        params.put(HASH, code);
        params.put(ACTION, "userVerify");
    }


    public CheckCodeClient(final Activity activity, final Map<String, String> params) {
        super(activity);
        this.params = params;
        params.put(ACTION, "systemVerify");
    }

    public void verify() {
        http.post(getShopUrl() + "/weryfikacja.php")
                .header("Authorization", RBiMHttpRequest.authorization)
                .data(params)
                .handler(new ResponseHandler() {
                    @Override
                    public void success(final Object data, final HttpResponse response) {
                        if (response.getCode() == 200 && data != null) {
                            final Gson gson = new Gson();
                            final String json = gson.toJson(data);

                            final Context context = getActivity().getBaseContext();
                            final Intent intent = new Intent(context, ReleaseActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra(CHECK_CODE_DATA, json);
                            intent.putExtra(MarkItemsReleasedClient.ORDER_ACTION_DATA, params.get(ACTION));
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
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }).send();
    }
}
