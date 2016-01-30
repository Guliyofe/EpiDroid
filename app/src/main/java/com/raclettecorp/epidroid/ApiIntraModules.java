package com.raclettecorp.epidroid;


import org.json.JSONArray;
import org.json.JSONObject;

public class ApiIntraModules
{

    private final Module[] _modules;

    ApiIntraModules(JSONObject answer)
    {
        JSONArray modules = answer.optJSONArray("modules");
        _modules = new Module[modules.length()];
        for (int i = 0; i < modules.length(); i++)
        {
            _modules[i] = new Module(modules.optJSONObject(i));
        }
    }

    public Module[] getModules()
    {
        return _modules;
    }
}
