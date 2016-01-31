package com.raclettecorp.epidroid;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class ApiIntraPlanning  implements Serializable {
    private static final long serialVersionUID = 8350092881346723535L;
    private String mToken;

    ApiIntraPlanning(String token)
    {
        mToken = token;
    }

    public HttpsURLConnection getHttpsURLConnection(Context context, String urlString)
    {
        try
        {
            URL obj = new URL(urlString);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            return con;
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
    }

    public String getResponseRequest(Context context, HttpsURLConnection con)
    {
        try
        {
            DataInputStream input = new DataInputStream(con.getInputStream());
/*            String answer = context.getString(R.string.string_empty);
            for( int c = input.read(); c != -1; c = input.read())
                answer += (char) c;*/
            String answer = context.getString(R.string.string_empty);
            String output;
            while ((output = input.readLine()) != null)
                answer += output;
            input.close();
            return answer;
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
    }

    public List<Planning> getIntraPlanning(Context context, String startDate, String endDate)
    {
        try
        {
            HttpsURLConnection con = this.getHttpsURLConnection(context, context.getString(R.string.http_planning) + "?"
                    + context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken
                    + context.getString(R.string.string_esper) + context.getString(R.string.start_date_format)
                    + context.getString(R.string.string_equal) + startDate + context.getString(R.string.string_esper)
                    + context.getString(R.string.end_date_format)
                    + context.getString(R.string.string_equal) + endDate);

            String answer = this.getResponseRequest(context, con);

            if (con.getResponseCode() == 200)
            {
                JSONArray jsonArray = new JSONArray(answer);
                List<Planning> planningList = new ArrayList<>();
                for(int i=0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    planningList.add(new Planning(jsonObject, mToken));
                }
                return planningList;
            }
            else
                return null;
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
    }
}
