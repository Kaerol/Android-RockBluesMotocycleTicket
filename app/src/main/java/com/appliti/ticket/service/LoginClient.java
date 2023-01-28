package com.appliti.ticket.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.widget.Toast;

import com.appliti.ticket.activity.ForbiddenActivity;
import com.appliti.ticket.activity.ScanerActivity;
import com.appliti.ticket.service.response.Authorization;
import com.kodart.httpzoid.HttpResponse;
import com.kodart.httpzoid.NetworkError;
import com.kodart.httpzoid.ResponseHandler;

import java.nio.charset.StandardCharsets;

public class LoginClient extends RBiMHttpRequest {
    public static String CHECK_CODE_DATA = "CHECK_CODE_DATA";

    private final String email;
    private final String password;

    public LoginClient(final Activity activity, final String email, final String password) {
        super(activity);
        this.email = email;
        this.password = password;
    }

    public void verify() {
        http.post(getShopUrl() + "/login.php")
                .header("Authorization", getAuthorization(email, password))
                .header("Content-Type", "application/json")
                .handler(new ResponseHandler<Authorization>() {
                    @Override
                    public void success(final Authorization data, final HttpResponse response) {
                        if (response.getCode() == 200) {
                            RBiMHttpRequest.authorization = data.getCode();

                            final Context context = getActivity().getBaseContext();
                            final Intent intent = new Intent(context, ScanerActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "You are forbidden! ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void error(final String message, final HttpResponse response) {
                        final Context context = getActivity().getBaseContext();
                        final Intent intent = new Intent(context, ForbiddenActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(final NetworkError error) {
                        Toast.makeText(getActivity(), "failure ", Toast.LENGTH_LONG).show();
                    }
                }).send();
    }

    private String getAuthorization(final String email, final String password) {
        final String text = email + ":" + password;
        final byte[] data = text.getBytes(StandardCharsets.UTF_8);
        return Base64.encodeToString(data, Base64.DEFAULT);
    }
}
