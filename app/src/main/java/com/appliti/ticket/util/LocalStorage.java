package com.appliti.ticket.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Map;

public class LocalStorage {
    public static final Map<String, String> storage = new HashMap<>();

    public static final String SHOP_URL = "SHOP_URL";
    public static final String RELEASE_TIME = "RELEASE_TIME";

    public static void storeString(final String key, final String value, final Activity activity) {
        final SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        final SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putString(key, value);
        myEditor.commit();

        storage.put(key, value);
    }

    public static String receive(final String key, final String defaultValue, final Activity activity) {
        final SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        final String value = myPreferences.getString(key, defaultValue);
        storage.put(key, value);

        return value;
    }
}
