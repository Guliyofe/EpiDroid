package com.raclettecorp.epidroid;

import org.json.JSONObject;

public class NetsoulStats
{
    private final Double _active;
    private final Double _idle;
    private final Double _outActive;
    private final Double _outIdle;
    private final Integer _nsNorm;

    NetsoulStats(JSONObject ns)
    {
        _active = ns.optDouble("active");
        _idle = ns.optDouble("idle");
        _outActive = ns.optDouble("out_active");
        _outIdle = ns.optDouble("out_idle");
        _nsNorm = ns.optInt("nslog_norm");
    }

    public Double getActive()
    {
        return _active;
    }

    public Double getIdle()
    {
        return _idle;
    }

    public Double getOutActive()
    {
        return _outActive;
    }

    public Double getOutIdle()
    {
        return _outIdle;
    }

    public Integer getNsNorm()
    {
        return _nsNorm;
    }
}
