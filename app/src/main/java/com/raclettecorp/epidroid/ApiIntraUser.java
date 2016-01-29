package com.raclettecorp.epidroid;

import org.json.JSONObject;

public class ApiIntraUser
{
    private User _user;

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
    }

    public User getUser()
    {
        return _user;
    }
}
