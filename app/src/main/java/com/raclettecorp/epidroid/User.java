package com.raclettecorp.epidroid;

import org.json.JSONObject;

public class User
{
    private final String _login;
    private final String _title;
    private final String _email;
    private final String _picture;
    private final Integer _promo;
    private final String _idPromo;
    private final String _idHistory;
    private final String _courseDoc;
    private final Boolean _close;
    private final Integer _studentYear;
    private final Boolean _admin;
    private final Boolean _editable;
    private final Integer _credits;
    private final Integer _uid;
    private final Integer _gid;
    private final Integer _semester;
    private final String _location;

    User(JSONObject user)
    {
        _login = user.optString("login");
        _title = user.optString("title");
        _email = user.optString("internal_email");
        _picture = user.optString("picture");
        _promo = user.optInt("promo");
        _idPromo = user.optString("id_promo");
        _idHistory = user.optString("id_history");
        _courseDoc = user.optString("course_doc");
        _close = user.optBoolean("close");
        _studentYear = user.optInt("studentyear");
        _admin = user.optBoolean("admin");
        _editable = user.optBoolean("editable");
        _credits = user.optInt("credits");
        _uid = user.optInt("uid");
        _gid = user.optInt("gid");
        _semester = user.optInt("semester");
        _location = user.optString("location");
    }

    public String getLogin()
    {
        return _login;
    }

    public String getTitle()
    {
        return _title;
    }

    public String getEmail()
    {
        return _email;
    }

    public String getPicture()
    {
        return _picture;
    }

    public Integer getPromo()
    {
        return _promo;
    }

    public String getIdPromo()
    {
        return _idPromo;
    }

    public String getIdHistory()
    {
        return _idHistory;
    }

    public String getCourseDoc()
    {
        return _courseDoc;
    }

    public Boolean getClose()
    {
        return _close;
    }

    public Integer getStudentYear()
    {
        return _studentYear;
    }

    public Boolean getAdmin()
    {
        return _admin;
    }

    public Boolean getEditable()
    {
        return _editable;
    }

    public Integer getCredits()
    {
        return _credits;
    }

    public Integer getUid()
    {
        return _uid;
    }

    public Integer getGid()
    {
        return _gid;
    }

    public Integer getSemester()
    {
        return _semester;
    }

    public String getLocation()
    {
        return _location;
    }
}
