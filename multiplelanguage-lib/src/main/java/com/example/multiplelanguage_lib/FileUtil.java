package com.example.multiplelanguage_lib;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by PhuTLQ on 3/2/2017.
 */

public class FileUtil {
    private static final String NAME_FOLDER_LOCALE = "locales";

    public FileUtil() {
    }

    private static boolean isFileExistInAsset(Context context, String fileName) {
        boolean isExist = false;
        AssetManager assetManager = context.getAssets();
        if(context != null && fileName != null && !"".equals(fileName)) {
            try {
                String[] e = assetManager.list("locales");

                for(int i = 0; i < e.length; ++i) {
                    if(fileName.equals(e[i])) {
                        isExist = true;
                        break;
                    }
                }
            } catch (IOException var6) {
                var6.printStackTrace();
            }

            return isExist;
        } else {
            return false;
        }
    }

    private static boolean isFileExistInInternal(Context context, String fileName) {
        if(context != null && fileName != null && !"".equals(fileName)) {
            File file = new File(context.getFilesDir(), fileName);
            return file.exists();
        } else {
            return false;
        }
    }

    public static boolean isFileExistInPhone(Context context, String fileName) {
        return isFileExistInAsset(context, fileName)?true:isFileExistInInternal(context, fileName);
    }

    public static void deleteFileInInternal(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if(file.exists()) {
            file.delete();
        }

    }

    public static JSONObject readFileLocale(Context context, String fileName) {
        JSONObject jSONObject = null;
        Object is = null;
        if(isFileExistInAsset(context, fileName)) {
            AssetManager e = context.getAssets();
            StringBuffer line = new StringBuffer("locales");
            line.append("/");
            line.append(fileName);

            try {
                is = e.open(line.toString());
            } catch (IOException var8) {
                var8.printStackTrace();
            }
        } else {
            if(!isFileExistInInternal(context, fileName)) {
                return null;
            }

            try {
                is = context.openFileInput(fileName);
            } catch (FileNotFoundException var7) {
                var7.printStackTrace();
            }
        }

        try {
            BufferedReader e1 = new BufferedReader(new InputStreamReader((InputStream)is));
            StringBuffer response = new StringBuffer();

            String line1;
            while((line1 = e1.readLine()) != null) {
                response.append(line1);
            }

            e1.close();
            jSONObject = new JSONObject(response.toString());
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return jSONObject;
    }
}
