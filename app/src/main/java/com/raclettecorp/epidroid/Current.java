package com.raclettecorp.epidroid;


import org.json.JSONObject;

public class Current
{
    private final String _activeLog;
    private final String _creditMin;
    private final String _creditNorm;
    private final String _creditObj;
    private final String _logMin;
    private final String _logNorm;
    private final String _semesterCode;

    Current(JSONObject current)
    {
        _activeLog = current.optString("active_log");
        _creditMin = current.optString("credits_min");
        _creditNorm = current.optString("credits_norm");
        _creditObj = current.optString("credits_obj");
        _logMin = current.optString("nslog_min");
        _logNorm = current.optString("nslog_norm");
        _semesterCode = current.optString("semester_code");
    }

    public String getActiveLog()
    {
        return _activeLog;
    }

    public String getCreditMin()
    {
        return _creditMin;
    }

    public String getCreditNorm()
    {
        return _creditNorm;
    }

    public String getCreditObj()
    {
        return _creditObj;
    }

    public String getLogMin()
    {
        return _logMin;
    }

    public String getLogNorm()
    {
        return _logNorm;
    }

    public String getSemesterCode()
    {
        return _semesterCode;
    }
}
