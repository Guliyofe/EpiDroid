package com.raclettecorp.epidroid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiIntraUser
{
    private User _user;
    private NetsoulStats _ns;
    private GpaThingy[] _gpa = null;

    ApiIntraUser(JSONObject answer)
    {
        try
        {
            _user = new User(answer);
        }
        catch (Exception e)
        {
            _user = null;
        }
        try
        {
            _ns = new NetsoulStats(new JSONObject(answer.optString("nsstat").toString()));
        }
        catch (Exception e) {
            _ns = null;
        }
        doGpaStuff(answer.optJSONArray("gpa"));
    }

    private void doGpaStuff(JSONArray gpa)
    {
        if (gpa == null)
            return;
        _gpa = new GpaThingy[gpa.length()];
        for (int i = 0; i < gpa.length(); i++)
        {
            _gpa[i] = new GpaThingy(gpa.optJSONObject(i));
        }
    }

    public User getUser()
    {
        return _user;
    }

    public NetsoulStats getNs()
    {
        return _ns;
    }

    public GpaThingy getGpa(int index)
    {
        if (index < _gpa.length)
            return _gpa[index];
        return null;
    }

    public class GpaThingy
    {
        private final String _gpa;
        private final String _cycle;

        GpaThingy(JSONObject gpa)
        {
            _gpa = gpa.optString("gpa");
            _cycle = gpa.optString("cycle");
        }

        public String getGpa()
        {
            return _gpa;
        }

        public String getCycle()
        {
            return _cycle;
        }
    }
}


