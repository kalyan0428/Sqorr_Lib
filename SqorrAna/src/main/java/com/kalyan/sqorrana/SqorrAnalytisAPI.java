package com.kalyan.sqorrana;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

public class SqorrAnalytisAPI {

    public static void setInstance(Context context, String token) {
        token = "K8I550EORBPVVUQN6THB";
        JSONObject jbInfo = new JSONObject();
        try {
            jbInfo.put("al_lib", "Android");
            jbInfo.put("brand", "");
            jbInfo.put("model", android.os.Build.MODEL);
            jbInfo.put("os", "");
            jbInfo.put("os_version", "");
            jbInfo.put("device", "");
            jbInfo.put("", "");
            jbInfo.put("", "");
            jbInfo.put("", "");
            jbInfo.put("", "");
            jbInfo.put("", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        AndroidNetworking.post("https://api-playsqorr.azurewebsites.net/analytics/api/initialize?token=" + token)
                .addJSONObjectBody(jbInfo) // posting json
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {


                    }

                    @Override
                    public void onError(ANError anError) {

                        if (anError.getErrorCode() != 0) {
                            Log.e("", "onError errorCode : " + anError.getErrorCode());
                            Log.e("", "onError errorBody : " + anError.getErrorBody());
                            Log.e("", "onError errorDetail : " + anError.getErrorDetail());

                        } else {
                            Log.e("", "onError errorDetail  0: " + anError.getErrorDetail());
                        }

                    }
                });

    }

    public static void trackEvent(String eventName, JSONObject properties) {


        JSONObject jbInfoTrack = new JSONObject();
        try {
            jbInfoTrack.put("event_name", eventName);
            jbInfoTrack.put("event_data", properties);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AndroidNetworking.post("https://api-playsqorr.azurewebsites.net/analytics/api/track?_id=5fc49e552078cac3b6433e91")
                .addJSONObjectBody(jbInfoTrack) // posting json
                .addHeaders("Content-Type", "application/json")
                .setPriority(Priority.HIGH)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }
}
