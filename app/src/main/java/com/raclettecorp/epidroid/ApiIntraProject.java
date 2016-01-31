package com.raclettecorp.epidroid;


import org.json.JSONObject;

import java.io.Serializable;

public class ApiIntraProject implements Serializable
{
    private static final long serialVersionUID = 8350095671342143535L;

    private final String _scolarYear;
    private final String _codeModule;
    private final String _codeInstance;
    private final String _codeActi;
    private final String _instanceLocation;
    private final String _moduletitle;
    private final Boolean _registered;
    private final String _begin;
    private final String _endRegistered;
    private final String _end;
    private final String _title;
    private final String _description;

    ApiIntraProject(JSONObject project)
    {
        _scolarYear = project.optString("scolaryear");
        _codeModule = project.optString("codemodule");
        _codeInstance = project.optString("codeinstance");
        _codeActi = project.optString("codeacti");
        _instanceLocation = project.optString("instance_location");
        _moduletitle = project.optString("module_title");
        _registered = project.optBoolean("registered");
        _begin = project.optString("begin");
        _end = project.optString("end");
        _endRegistered = project.optString("end_registered");
        _title = project.optString("title");
        _description= project.optString("description");
    }


    public String getScolarYear()
    {
        return _scolarYear;
    }

    public String getCodeModule()
    {
        return _codeModule;
    }

    public String getCodeInstance()
    {
        return _codeInstance;
    }

    public String getCodeActi()
    {
        return _codeActi;
    }

    public String getInstanceLocation()
    {
        return _instanceLocation;
    }

    public String getModuleTitle()
    {
        return _moduletitle;
    }

    public Boolean getRegistered()
    {
        return _registered;
    }

    public String getBegin()
    {
        return _begin;
    }

    public String getEnd()
    {
        return _end;
    }

    public String getEndRegistered()
    {
        return _endRegistered;
    }

    public String getTitle()
    {
        return _title;
    }

    public String getDescription()
    {
        return _description;
    }
}
