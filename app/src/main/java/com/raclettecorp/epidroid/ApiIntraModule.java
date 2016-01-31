package com.raclettecorp.epidroid;


import org.json.JSONObject;

import java.io.Serializable;

public class ApiIntraModule implements Serializable
{
    private static final long serialVersionUID = 8350095671346723535L;

    private final String _scolarYear;
    private final String _codeModule;
    private final String _codeInstance;
    private final Integer _semester;
    private final String _title;
    private final String _begin;
    private final String _endRegister;
    private final String _end;
    private final Integer _credits;
    private final String _description;
    private final String _competence;
    private final String _grade;


    ApiIntraModule(JSONObject answer, String grade)
    {
        _scolarYear = answer.optString("scolaryear");
        _codeModule = answer.optString("codemodule");
        _codeInstance = answer.optString("codeinstance");
        _semester = answer.optInt("semester");
        _title = answer.optString("title");
        _begin = answer.optString("begin");
        _endRegister = answer.optString("end_register");
        _end = answer.optString("end");
        _credits = answer.optInt("credits");
        _description = answer.optString("description");
        _competence = answer.optString("competence");
        _grade = grade;
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

    public Integer getSemester()
    {
        return _semester;
    }

    public String getTitle()
    {
        return _title;
    }

    public String getBegin()
    {
        return _begin;
    }

    public String getEndRegister()
    {
        return _endRegister;
    }

    public String getEnd()
    {
        return _end;
    }

    public Integer getCredits()
    {
        return _credits;
    }

    public String getDescription()
    {
        return _description;
    }

    public String getCompetence()
    {
        return _competence;
    }

    public String getGrade()
    {
        return _grade;
    }
}
