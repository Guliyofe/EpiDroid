package com.raclettecorp.epidroid;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
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
        mToken = null;
    }

    public String getMToken() {
        return mToken;
    }

    public void setToken(String token) {
        this.mToken = token;
        apiIntraPlanning = new ApiIntraPlanning(mToken);
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
                JSONObject jsonRootObject = new JSONObject(outputString);
                mToken = jsonRootObject.optString("token");
                return mToken;
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

    public ApiIntraInfos requestInfos(Context context)
    {
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

            String answer = context.getString(R.string.string_empty);
            String lol;
            while ((lol = input.readLine()) != null)
                answer += lol;
            input.close();
            if (con.getResponseCode() == 200) {
                ApiIntraInfos infos = new ApiIntraInfos(new JSONObject((answer)));
                Log.d(context.getString(R.string.app_name), context.getString(R.string.debug_api_infos_done));
                return infos;
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
        return null;
    }

    public ApiIntraUser requestUser(Context context, String login)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_user) + "?" + context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_user)
                    + context.getString(R.string.string_equal) + login);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setDoInput(true);

            DataInputStream input = new DataInputStream( con.getInputStream() );
            String answer = context.getString(R.string.string_empty);
            String lol;
            while ((lol = input.readLine()) != null)
                answer += lol;
            input.close();

            if (con.getResponseCode() == 200) {
                return new ApiIntraUser(new JSONObject((answer)));
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
        return null;
    }

    public ApiIntraModules requestModules(Context context)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_modules) + "?" + context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setDoInput(true);

            DataInputStream input = new DataInputStream( con.getInputStream() );
            String answer = context.getString(R.string.string_empty);
            String lol;
            while ((lol = input.readLine()) != null)
                answer += lol;
            input.close();
            if (con.getResponseCode() == 200)
            {
               return new ApiIntraModules(new JSONObject((answer)));
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
        return null;
    }

    public ApiIntraProjects requestProjects(Context context)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_projects) + "?" + context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setDoInput(true);

            DataInputStream input = new DataInputStream( con.getInputStream() );
            String answer = context.getString(R.string.string_empty);
            String lol;
            while ((lol = input.readLine()) != null)
                answer += lol;
            input.close();
            if (con.getResponseCode() == 200)
            {
                return new ApiIntraProjects(new JSONArray((answer)));
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
        return null;
    }

    public ApiIntraProject requestProject(Context context, String scolarYear, String codeModule, String codeInstance, String codeActi)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_project) + "?" + context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_scolar_year) + context.getString(R.string.string_equal) + scolarYear
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_module) + context.getString(R.string.string_equal) + codeModule
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_instance) + context.getString(R.string.string_equal) + codeInstance
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_acti) + context.getString(R.string.string_equal) + codeActi);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setDoInput(true);

            DataInputStream input = new DataInputStream( con.getInputStream() );
            String answer = context.getString(R.string.string_empty);
            String lol;
            while ((lol = input.readLine()) != null)
                answer += lol;
            input.close();
            if (con.getResponseCode() == 200)
            {
                return new ApiIntraProject(new JSONObject((answer)));
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
        return null;
    }

    public Boolean unsubProject(Context context, String scolarYear, String codeModule, String codeInstance, String codeActi)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_project));
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod(context.getString(R.string.request_delete));
            con.setRequestProperty(context.getString(R.string.string_token), mToken);
            con.setRequestProperty(context.getString(R.string.string_scolar_year), scolarYear);
            con.setRequestProperty(context.getString(R.string.string_code_module), codeModule);
            con.setRequestProperty(context.getString(R.string.string_code_instance), codeInstance);
            con.setRequestProperty(context.getString(R.string.string_code_acti), codeActi);
            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_scolar_year) + context.getString(R.string.string_equal) + scolarYear
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_module) + context.getString(R.string.string_equal) + codeModule
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_instance) + context.getString(R.string.string_equal) + codeInstance
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_acti) + context.getString(R.string.string_equal) + codeActi);
            output.close();

            DataInputStream input = new DataInputStream( con.getInputStream() );

            String answer = context.getString(R.string.string_empty);
            for( int c = input.read(); c != -1; c = input.read() )
                answer += (char) c;
            input.close();
            if (con.getResponseCode() == 200) {
                return true;
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return false;
        }
        return false;
    }

    public Boolean subProject(Context context, String scolarYear, String codeModule, String codeInstance, String codeActi)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_project));
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod(context.getString(R.string.request_post));
            con.setRequestProperty(context.getString(R.string.string_token), mToken);
            con.setRequestProperty(context.getString(R.string.string_scolar_year), scolarYear);
            con.setRequestProperty(context.getString(R.string.string_code_module), codeModule);
            con.setRequestProperty(context.getString(R.string.string_code_instance), codeInstance);
            con.setRequestProperty(context.getString(R.string.string_code_acti), codeActi);
            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_scolar_year) + context.getString(R.string.string_equal) + scolarYear
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_module) + context.getString(R.string.string_equal) + codeModule
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_instance) + context.getString(R.string.string_equal) + codeInstance
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_acti) + context.getString(R.string.string_equal) + codeActi);
            output.close();

            DataInputStream input = new DataInputStream( con.getInputStream() );

            String answer = context.getString(R.string.string_empty);
            for( int c = input.read(); c != -1; c = input.read() )
                answer += (char) c;
            input.close();
            if (con.getResponseCode() == 200) {
                return true;
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return false;
        }
        return false;
    }

    public ApiIntraModule requestModule(Context context, String scolarYear, String codeModule, String codeInstance, String grade)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_module) + "?" + context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken
            + context.getString(R.string.string_esper) + context.getString(R.string.string_scolar_year) + context.getString(R.string.string_equal) + scolarYear
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_module) + context.getString(R.string.string_equal) + codeModule
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_instance) + context.getString(R.string.string_equal) + codeInstance);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setDoInput(true);

            DataInputStream input = new DataInputStream( con.getInputStream() );
            String answer = context.getString(R.string.string_empty);
            String lol;
            while ((lol = input.readLine()) != null)
                answer += lol;
            input.close();
            if (con.getResponseCode() == 200)
            {
                ApiIntraModule module = new ApiIntraModule(new JSONObject((answer)), grade);
                Log.d(context.getString(R.string.app_name), "Module : " + module.getDescription());
                return module;
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return null;
        }
        return null;
    }

    public Boolean unsubModule(Context context, String scolarYear, String codeModule, String codeInstance)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_module));
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod(context.getString(R.string.request_delete));
            con.setRequestProperty(context.getString(R.string.string_token), mToken);
            con.setRequestProperty(context.getString(R.string.string_scolar_year), scolarYear);
            con.setRequestProperty(context.getString(R.string.string_code_module), codeModule);
            con.setRequestProperty(context.getString(R.string.string_code_instance), codeInstance);
            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_scolar_year) + context.getString(R.string.string_equal) + scolarYear
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_module) + context.getString(R.string.string_equal) + codeModule
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_instance) + context.getString(R.string.string_equal) + codeInstance);
            output.close();

            DataInputStream input = new DataInputStream( con.getInputStream() );

            String answer = context.getString(R.string.string_empty);
            for( int c = input.read(); c != -1; c = input.read() )
                answer += (char) c;
            input.close();
            if (con.getResponseCode() == 200) {
                return true;
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return false;
        }
        return false;
    }

    public Boolean subModule(Context context, String scolarYear, String codeModule, String codeInstance)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_module));
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod(context.getString(R.string.request_post));
            con.setRequestProperty(context.getString(R.string.string_token), mToken);
            con.setRequestProperty(context.getString(R.string.string_scolar_year), scolarYear);
            con.setRequestProperty(context.getString(R.string.string_code_module), codeModule);
            con.setRequestProperty(context.getString(R.string.string_code_instance), codeInstance);
            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_scolar_year) + context.getString(R.string.string_equal) + scolarYear
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_module) + context.getString(R.string.string_equal) + codeModule
                    + context.getString(R.string.string_esper) + context.getString(R.string.string_code_instance) + context.getString(R.string.string_equal) + codeInstance);
            output.close();

            DataInputStream input = new DataInputStream( con.getInputStream() );

            String answer = context.getString(R.string.string_empty);
            for( int c = input.read(); c != -1; c = input.read() )
                answer += (char) c;
            input.close();
            if (con.getResponseCode() == 200) {
                return true;
            }
        }
        catch( Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
            return false;
        }
        return false;
    }

    public ApiIntraSusie requestSusie(Context context, int id, int calendar_id)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_infos));

            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod(context.getString(R.string.request_post));
            con.setRequestProperty(context.getString(R.string.string_token), mToken);
            con.setRequestProperty(context.getString(R.string.string_id), String.valueOf(id));
            con.setRequestProperty(context.getString(R.string.string_id_calendar), String.valueOf(calendar_id));
            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(context.getString(R.string.string_token) + context.getString(R.string.string_equal) + mToken);
            output.writeBytes(context.getString(R.string.string_id) + context.getString(R.string.string_equal) + String.valueOf(id));
            output.writeBytes(context.getString(R.string.string_id_calendar) + context.getString(R.string.string_equal) + String.valueOf(calendar_id));

            output.close();

            DataInputStream input = new DataInputStream( con.getInputStream() );

            String answer = context.getString(R.string.string_empty);
            for( int c = input.read(); c != -1; c = input.read() )
                answer += (char) c;
            input.close();
            if (con.getResponseCode() == 200) {
                ApiIntraSusie susie = new ApiIntraSusie(new JSONObject((answer)));
                Log.d(context.getString(R.string.app_name), context.getString(R.string.debug_api_infos_done));
                return susie;
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
