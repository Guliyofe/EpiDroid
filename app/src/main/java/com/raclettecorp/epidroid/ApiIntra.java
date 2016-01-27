package com.raclettecorp.epidroid;

import android.content.Context;
import android.util.Log;

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Class for managing requests to intra
 */
public class ApiIntra
{
    String token;
    String mLogin;
    String mPassword;

    public ApiIntra()
    {}

    public boolean connectIntra(Context context)
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
            return true;
        }
        catch (Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return false;
        }
    }

}
