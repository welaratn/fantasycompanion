package com.example.welar.fantasyhelper;

import android.util.Log;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by welar on 08/06/2017.
 */

public class FantasyRestClientUsage {
    public JSONObject getPublicTimeline(EditText text) throws JSONException {
        FantasyRestClient.get("element-summary", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                try {
                    JSONObject firstEvent = timeline.getJSONObject(0);
                    String tweetText = firstEvent.getString("text");
                    // Do something with the response
                    System.out.println(tweetText);
                } catch (JSONException e) {
                }
            }
        });
    }

    public void onSuccess(String playerId) throws JSONException {
        String apiCall = "element-summary/" + playerId;
        FantasyRestClient.get(apiCall, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("RESTAPI", "JSONObject function");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                Log.d("RESTAPI", "JSONObject function");
                try {
                    //return timeline;
                    JSONObject firstEvent = timeline.getJSONObject(0);
                    String tweetText = firstEvent.getString("text");
                    // Do something with the response
                    System.out.println(tweetText);
                } catch (JSONException e) {
                }
            }
        });
    }
}
