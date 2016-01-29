package com.raclettecorp.epidroid;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Class for managing requests to intra
 */
public class ApiIntra implements Serializable
{
    private static final long serialVersionUID = 1350092881346723535L;
    String mToken;
    String mLogin;
    String mPassword;

    ApiIntraPlanning apiIntraPlanning;

    public ApiIntra()
    {
        apiIntraPlanning = new ApiIntraPlanning();
        mToken = null;
    }

    public String getMToken() {
        return mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
    }

    public ApiIntraPlanning getApiIntraPlanning() {
        return apiIntraPlanning;
    }

    public String connectIntra(Context context, String login, String password)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_login));

            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod(context.getString(R.string.request_post));
            con.setRequestProperty(context.getString(R.string.string_login), mLogin);
            con.setRequestProperty(context.getString(R.string.string_password), mPassword);
            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(context.getString(R.string.string_login)+ context.getString(R.string.string_equal) + login +
                    context.getString(R.string.string_esper) + context.getString(R.string.string_password) + context.getString(R.string.string_equal) + password);
            output.close();

            DataInputStream input = new DataInputStream( con.getInputStream() );

            String outputString = context.getString(R.string.string_empty);
            for( int c = input.read(); c != -1; c = input.read() )
                outputString += (char) c;
            Log.d(context.getString(R.string.app_name), context.getString(R.string.debug_output_login) + outputString);
            input.close();
            if (con.getResponseCode() == 200)
            {
                JSONObject  jsonRootObject = new JSONObject(outputString);
                String name = jsonRootObject.optString("token").toString();
                String data = name;
                mToken = data;
                return data;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
    }

    public JSONObject requestInfos(Context context)
    {
        String answer;
        try
        {
            URL obj = new URL(context.getString(R.string.http_infos));

            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod(context.getString(R.string.request_post));
            con.setRequestProperty(context.getString(R.string.string_token), mToken);
            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken);
            output.close();

            DataInputStream input = new DataInputStream( con.getInputStream() );

            String outputString = context.getString(R.string.string_empty);
            for( int c = input.read(); c != -1; c = input.read() )
                outputString += (char) c;
            Log.d(context.getString(R.string.app_name), context.getString(R.string.debug_output_login) + outputString);
            input.close();
            if (con.getResponseCode() == 200) {
                JSONObject jsonObjectInfos = new JSONObject("Response");
                String name = jsonObjectInfos.optString("token").toString();
                return jsonObjectInfos;
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
        return null;
    }



}
