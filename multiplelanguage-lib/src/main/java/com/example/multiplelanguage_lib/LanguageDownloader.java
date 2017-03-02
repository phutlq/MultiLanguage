package com.example.multiplelanguage_lib;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by PhuTLQ on 3/2/2017.
 */

public class LanguageDownloader extends AsyncTask<String, Void, Boolean> {
    private Context mContext;
    private String mUrl;
    private String mFileName;
    private ProgressDialog mProgressDialog;
    private OnMultiLanguageListener mListener;

    public LanguageDownloader(Context context, String url, String fileName, OnMultiLanguageListener l) {
        this.mContext = context;
        this.mUrl = url;
        this.mFileName = fileName;
        this.mListener = l;
        this.mProgressDialog = new ProgressDialog(context);
        this.mProgressDialog.setCanceledOnTouchOutside(false);
        this.setProgressMessage("Downloading...");
    }

    public void setProgressMessage(String message) {
        this.mProgressDialog.setMessage(message);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.mProgressDialog.show();
    }

    protected Boolean doInBackground(String... params) {
        try {
            URL e = new URL(this.mUrl);
            URLConnection connection = e.openConnection();
            connection.connect();
            BufferedInputStream is = new BufferedInputStream(e.openStream());
            File file = new File(this.mContext.getFilesDir(), this.mFileName);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] data = new byte[1024];

            int count;
            while((count = is.read(data)) != -1 && !this.isCancelled()) {
                fos.write(data, 0, count);
            }

            fos.flush();
            fos.close();
            is.close();
            return Boolean.valueOf(true);
        } catch (Exception var9) {
            var9.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    protected void onCancelled() {
        super.onCancelled();
        FileUtil.deleteFileInInternal(this.mContext, this.mFileName);
    }

    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        this.mProgressDialog.dismiss();
        this.mListener.onDownloadFileLocaleFinish(result.booleanValue(), this.mFileName);
    }
}
