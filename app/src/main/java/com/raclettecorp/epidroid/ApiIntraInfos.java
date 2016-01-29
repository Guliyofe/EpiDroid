package com.raclettecorp.epidroid;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiIntraInfos
{
    private final String _ip;
    private Infos _infos;
    private Current _current;

    ApiIntraInfos(JSONObject answer)
    {
        _ip = answer.optString("ip").toString();
        try
        {
            _infos = new Infos(new JSONObject(answer.optString("infos").toString()));
        }
        catch (JSONException e)
        {
            _infos = null;
        }
        try
        {
            _current = new Current(new JSONObject(answer.optString("current").toString()));
        }
        catch (JSONException e)
        {
            _current = null;
        }
    }

    public String getIp()
    {
        return _ip;
    }

    public Infos getInfos()
    {
        return _infos;
    }

    public Current getCurrent()
    {
        return _current;
    }
}
