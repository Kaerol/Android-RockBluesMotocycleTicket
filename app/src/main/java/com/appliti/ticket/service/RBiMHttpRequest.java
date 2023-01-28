package com.appliti.ticket.service;

import android.app.Activity;

import com.appliti.ticket.util.LocalStorage;
import com.kodart.httpzoid.Http;
import com.kodart.httpzoid.HttpFactory;


public abstract class RBiMHttpRequest {

    protected final Http http;
    public static String authorization;

    protected Activity getActivity() {
        return activity;
    }

    private final Activity activity;

    public RBiMHttpRequest(final Activity activity) {
        this.activity = activity;
        http = HttpFactory.create(activity);
    }

    public String getShopUrl() {
        return LocalStorage.storage.get(LocalStorage.SHOP_URL);
    }
}
