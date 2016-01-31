package com.raclettecorp.epidroid;


import org.json.JSONArray;
import org.json.JSONObject;

public class ApiIntraProjects
{
    private Project[] _projects;

    ApiIntraProjects(JSONArray answer)
    {
        _projects = new Project[answer.length()];
        for (int i = 0; i < answer.length(); i++)
            _projects[i] = new Project(answer.optJSONObject(i));
    }

    public Project[] getProjects()
    {
        return _projects;
    }

    public static class Project
    {
        private final String _codeModule;
        private final String _project;
        private final String _endActi;
        private final String _actiTitle;
        private final String _titleModule;
        private final String _beginActi;
        private final String _codeActi;
        private final Integer _registered;
        private final String _codeInstance;
        private final String _scolarYear;

        Project(JSONObject project)
        {
            _codeModule = project.optString("codemodule");
            _project = project.optString("project");
            _endActi = project.optString("end_acti");
            _actiTitle = project.optString("acti_title");
            _titleModule = project.optString("title_module");
            _beginActi = project.optString("begin_acti");
            _codeActi = project.optString("codeacti");
            _registered = project.optInt("registered");
            _codeInstance = project.optString("codeinstance");
            _scolarYear = project.optString("scolaryear");
        }

        public String getCodeModule()
        {
            return _codeModule;
        }

        public String getProject()
        {
            return _project;
        }

        public String getEndActi()
        {
            return _endActi;
        }

        public String getActiTitle()
        {
            return _actiTitle;
        }

        public String getTitleModule()
        {
            return _titleModule;
        }

        public String getBeginActi()
        {
            return _beginActi;
        }

        public String getCodeActi()
        {
            return _codeActi;
        }

        public Integer getRegistered()
        {
            return _registered;
        }

        public String getCodeInstance()
        {
            return _codeInstance;
        }

        public String getScolarYear()
        {
            return _scolarYear;
        }
    }
}
