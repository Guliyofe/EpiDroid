package com.raclettecorp.epidroid;


import org.json.JSONObject;

public class Module
{
    private final Integer _scholarYear;
    private final String _idUserHistory;
    private final String _codeModule;
    private final String _codeInstance;
    private final String _title;
    private final String _idInstance;
    private final String _dateIns;
    private final String _cycle;
    private final String _grade;
    private final Integer _credits;
    private final String _instanceId;
    private final Integer _semester;

    Module(JSONObject module)
    {
        _scholarYear = module.optInt("scolaryear");
        _idUserHistory = module.optString("id_user_history");
        _codeModule = module.optString("codemodule");
        _codeInstance = module.optString("codeinstance");
        _title = module.optString("title");
        _idInstance = module.optString("id_instance");
        _dateIns = module.optString("date_ins");
        _cycle = module.optString("cycle");
        _grade = module.optString("grade");
        _credits = module.optInt("credits");
        _instanceId = module.optString("instance_id");
        _semester = module.optInt("semester");
    }

    public Integer getScholarYear()
    {
        return _scholarYear;
    }

    public String getIdUserHistory()
    {
        return _idUserHistory;
    }

    public String getCodeModule()
    {
        return _codeModule;
    }

    public String getCodeInstance()
    {
        return _codeInstance;
    }

    public String getTitle()
    {
        return _title;
    }

    public String getIdInstance()
    {
        return _idInstance;
    }

    public String getDateIns()
    {
        return _dateIns;
    }

    public String getCycle()
    {
        return _cycle;
    }

    public String getGrade()
    {
        return _grade;
    }

    public Integer getCredits()
    {
        return _credits;
    }

    public String getInstanceId()
    {
        return _instanceId;
    }

    public Integer getSemester()
    {
        return _semester;
    }
}
