package com.example.multiplelanguage_lib;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by PhuTLQ on 3/2/2017.
 */

public class SaverPreferences {
    private static final String DEFAULT_FILE_LOCALE_NAME = "locale.en";
    private static final String PREFERENCES = "multiple_language";
    private static final String LOCALE = "locale";

    public SaverPreferences() {
    }

    public static String getCurrentFileLocaleName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("multiple_language", 0);
        return prefs.getString("locale", "locale.en");
    }

    public static void saveCurrentFileLocaleName(Context context, String fileName) {
        SharedPreferences prefs = context.getSharedPreferences("multiple_language", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("locale", fileName);
        editor.commit();
    }
}
