package com.example.multiplelanguage_lib;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PhuTLQ on 3/2/2017.
 */

public class LanguageDictionary {

    private JSONObject mJSONObject;

    public LanguageDictionary(JSONObject jSONObject) {
        this.mJSONObject = jSONObject;
    }

    public JSONObject getmJSONObject() {
        return mJSONObject;
    }

    public void setmJSONObject(JSONObject mJSONObject) {
        this.mJSONObject = mJSONObject;
    }

    public String getValue(String key) {
        if(this.mJSONObject == null) {
            return "";
        } else {
            if(!this.mJSONObject.isNull(key)) {
                try {
                    return this.mJSONObject.getString(key);
                } catch (JSONException var3) {
                    var3.printStackTrace();
                }
            }

            return "";
        }
    }
}
