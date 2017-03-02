package com.example.multiplelanguage_lib;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressBar;

import org.json.JSONObject;

/**
 * Created by PhuTLQ on 3/2/2017.
 */

public class LanguageReader extends AsyncTask<String, Void, JSONObject> {
    private Context mContext;
    private String mFileName;
    private OnMultiLanguageListener mListener;
    private ProgressBar progressBar;

    public LanguageReader(Context context, String fileName, OnMultiLanguageListener l, ProgressBar progressBar) {
        this.mContext = context;
        this.mFileName = fileName;
        this.mListener = l;
        this.progressBar = progressBar;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if(this.progressBar != null) {
            this.progressBar.setVisibility(0);
        }

    }

    protected JSONObject doInBackground(String... params) {
        JSONObject jSONObject = FileUtil.readFileLocale(this.mContext, this.mFileName);
        return jSONObject;
    }

    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        LanguageDictionary dic = null;
        if(result != null) {
            dic = new LanguageDictionary(result);
        }

        if(this.progressBar != null) {
            this.progressBar.setVisibility(8);
        }

        this.mListener.onReadFileLocaleFinish(dic, this.mFileName);
    }
}

