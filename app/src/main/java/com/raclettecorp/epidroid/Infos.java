package com.raclettecorp.epidroid;

import org.json.JSONObject;

public class Infos
{
    private final String _id;
    private final String _title;
    private final String _login;
    private final String _lastName;
    private final String _firstName;
    private final String _promo;
    private final String _semester;
    private final String _location;
    private final String _studentYear;

    Infos(JSONObject infos)
    {
        _id = infos.optString("id");
        _title = infos.optString("title");
        _login = infos.optString("login");
        _lastName = infos.optString("lastname");
        _firstName = infos.optString("firstname");
        _promo = infos.optString("promo");
        _semester = infos.optString("semester");
        _location = infos.optString("location");
        _studentYear = infos.optString("studentyear");
    }

    public String getId()
    {
        return _id;
    }

    public String getTitle()
    {
        return _title;
    }

    public String getLogin()
    {
        return _login;
    }

    public String getLastName()
    {
        return _lastName;
    }

    public String getFirstName()
    {
        return _firstName;
    }

    public String getPromo()
    {
        return _promo;
    }

    public String getSemester()
    {
        return _semester;
    }

    public String getLocation()
    {
        return _location;
    }

    public String getStudentYear()
    {
        return _studentYear;
    }
}
