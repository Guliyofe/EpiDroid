package com.raclettecorp.epidroid;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiIntraInfos
{
    private final String _ip;
    private Infos _infos;
    private Current _current;
    private History[] _history;

    ApiIntraInfos(JSONObject answer)
    {
        _ip = answer.optString("ip");
        try
        {
            _infos = new Infos(new JSONObject(answer.optString("infos")));
        }
        catch (JSONException e)
        {
            _infos = null;
        }
        try
        {
            _current = new Current(new JSONObject(answer.optString("current")));
        }
        catch (JSONException e)
        {
            _current = null;
        }
        doHistoryThingy(answer.optJSONArray("history"));
    }

    public void doHistoryThingy(JSONArray history)
    {
        _history = new History[history.length()];
        for (int i = 0; i < history.length(); i++)
        {
            _history[i] = new History(history.optJSONObject(i));
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

    public History[] getHistory()
    {
        return _history;
    }
}
