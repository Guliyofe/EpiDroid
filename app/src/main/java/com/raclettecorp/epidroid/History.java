package com.raclettecorp.epidroid;

import org.json.JSONObject;

public class History
{
    private final String _title;
    private final UserHistory _user;
    private final String _content;
    private final String _date;
    private final String _id;
    private final String _visible;
    private final String _idActivity;
    private final String _class;

    History(JSONObject history)
    {
        _title = history.optString("title");
        _user = new UserHistory(history.optJSONObject("user"));
        _content = history.optString("content");
        _date = history.optString("date");
        _id = history.optString("id");
        _visible = history.optString("visible");
        _idActivity = history.optString("id_activite");
        _class = history.optString("class");
    }

    public String getTitle()
    {
        return _title;
    }

    public UserHistory getUser()
    {
        return _user;
    }

    public String getContent()
    {
        return _content;
    }

    public String getDate()
    {
        return _date;
    }

    public String getId()
    {
        return _id;
    }

    public String getVisible()
    {
        return _visible;
    }

    public String getIdActivity()
    {
        return _idActivity;
    }

    public String getClassHistory()
    {
        return _class;
    }

    public class UserHistory
    {
        private final String _picture;
        private final String _title;
        private final String _url;

        UserHistory(JSONObject user)
        {
            _picture = user.optString("picture");
            _title = user.optString("title");
            _url = user.optString("url");
        }

        public String getPicture()
        {
            return _picture;
        }

        public String getTitle()
        {
            return _title;
        }

        public String getUrl()
        {
            return _url;
        }
    }
}
