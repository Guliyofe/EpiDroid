package com.raclettecorp.epidroid;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Théophile on 30/01/2016.
 */
public class Planning implements Serializable
{
    private static final long serialVersionUID = 4350092881346723535L;

    private String _tokenSave;
    private String _prof_inst; // changer
    private String _titlePlanning;
    private String _rdv_indiv_registered;
    private String _allowed_planning_end;
    private int _nb_group;
    private String _start;
    private String _register_month;
    private String _allowed_planning_start;
    private boolean _project;
    private String _event_registered;
    private int _total_students_registered;
    private boolean _allow_register;
    private String _codemodule;
    private String _rdv_group_registered;
    private boolean _semester;
    private String _type_code;
    private String _is_rdv;
    private boolean _allow_token;
    private String _titlemodule;
    private boolean _in_more_than_one_month;
    private String _acti_title;
    private String _instance_location;
    private String _nb_hours;
    private String _register_prof;
    private int _nb_max_students_projet;
    private JSONObject _room;
    private String _codeacti;
    private String _codeevent;
    private String _codeinstance;
    private String _dates;
    private boolean _register_student;
    private String _type_title;
    private int _num_event;
    private String _end;
    private String _scolaryear;
    private boolean _module_registered;
    private boolean _past;
    private boolean _module_available;

    Planning(JSONObject planning, String token)
    {
        _tokenSave = token;
        _prof_inst = "";
        _titlePlanning = planning.optString("title");
        _rdv_indiv_registered = "";
        _allowed_planning_end = "";
        _nb_group = 0;
        _start = planning.optString("start");
        _register_month = "";
        _allowed_planning_start = "";
        _project = false;
        _event_registered = "";
        _total_students_registered = 0;
        _allow_register = false;
        _codemodule = planning.optString("codemodule");
        _rdv_group_registered = "";
        _semester = false;
        _type_code = "";
        _is_rdv = "";
        _allow_token =  planning.optBoolean("allow_token");
        _titlemodule = planning.optString("titlemodule");
        _in_more_than_one_month = false;
        _acti_title = planning.optString("acti_title");
        _instance_location = "";
        _nb_hours = planning.optString("nb_hours");
        _register_prof = "";
        _nb_max_students_projet = 0;
        try
        {
            if (planning.optString("room") != "null")
                _room = new JSONObject(planning.optString("room"));
            else
                _room = null;
        }
        catch( Exception e)
        {
            return;
        }
        _codeacti = planning.optString("codeacti");
        _codeevent = planning.optString("codeevent");
        _codeinstance = planning.optString("codeinstance");
        _dates = "";
        _register_student = false;
        _type_title = "";
        _num_event = 0;
        _end = planning.optString("end");
        _scolaryear = planning.optString("scolaryear");
        _module_registered = false;
        _past = false;
        _module_available = false;
    }

    public void validateToken(Context context,
                              String scolarYear,
                              String codeModule,
                              String codeInstance,
                              String codeActi,
                              String codeEvent,
                              String tokenToValidate)
    {
        try
        {
            URL obj = new URL(context.getString(R.string.http_token));

            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            con.setRequestMethod(context.getString(R.string.request_post));
            con.setRequestProperty(context.getString(R.string.string_token), _tokenSave);
            con.setRequestProperty(context.getString(R.string.string_scolar_year), scolarYear);
            con.setRequestProperty(context.getString(R.string.string_code_module), codeModule);
            con.setRequestProperty(context.getString(R.string.string_code_instance), codeInstance);
            con.setRequestProperty(context.getString(R.string.string_code_acti), codeActi);
            con.setRequestProperty(context.getString(R.string.string_code_event), codeEvent);
            con.setRequestProperty(context.getString(R.string.string_token_validation_code), tokenToValidate);
            con.setDoOutput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(context.getString(R.string.string_token) + context.getString(R.string.string_equal) +_tokenSave + context.getString(R.string.string_esper) +
                    context.getString(R.string.string_scolar_year) + context.getString(R.string.string_equal) + scolarYear + context.getString(R.string.string_esper) +
                    context.getString(R.string.string_code_module) + context.getString(R.string.string_equal) + codeModule + context.getString(R.string.string_esper) +
                    context.getString(R.string.string_code_instance) + context.getString(R.string.string_equal) + codeInstance + context.getString(R.string.string_esper) +
                    context.getString(R.string.string_code_acti) + context.getString(R.string.string_equal) + codeActi + context.getString(R.string.string_esper) +
                    context.getString(R.string.string_code_event) + context.getString(R.string.string_equal) + codeEvent + context.getString(R.string.string_esper) +
                    context.getString(R.string.string_token_validation_code) + tokenToValidate);
            output.close();
        }
        catch (Exception e)
        {
            Log.d(context.getString(R.string.app_name), e.toString());
        }
    }

    public String get_tokenSave() {
        return _tokenSave;
    }

    public String get_prof_inst() {
        return _prof_inst;
    }

    public String get_titlePlanning() {
        return _titlePlanning;
    }

    public String get_rdv_indiv_registered() {
        return _rdv_indiv_registered;
    }

    public String get_allowed_planning_end() {
        return _allowed_planning_end;
    }

    public int get_nb_group() {
        return _nb_group;
    }

    public String get_start() {
        return _start;
    }

    public String get_register_month() {
        return _register_month;
    }

    public String get_allowed_planning_start() {
        return _allowed_planning_start;
    }

    public boolean is_project() {
        return _project;
    }

    public String get_event_registered() {
        return _event_registered;
    }

    public int get_total_students_registered() {
        return _total_students_registered;
    }

    public boolean is_allow_register() {
        return _allow_register;
    }

    public String get_codemodule() {
        return _codemodule;
    }

    public String get_rdv_group_registered() {
        return _rdv_group_registered;
    }

    public boolean is_semester() {
        return _semester;
    }

    public String get_type_code() {
        return _type_code;
    }

    public String get_is_rdv() {
        return _is_rdv;
    }

    public boolean is_allow_token() {
        return _allow_token;
    }

    public String get_titlemodule() {
        return _titlemodule;
    }

    public boolean is_in_more_than_one_month() {
        return _in_more_than_one_month;
    }

    public String get_acti_title() {
        return _acti_title;
    }

    public String get_instance_location() {
        return _instance_location;
    }

    public String get_nb_hours() {
        return _nb_hours;
    }

    public String get_register_prof() {
        return _register_prof;
    }

    public int get_nb_max_students_projet() {
        return _nb_max_students_projet;
    }

    public JSONObject get_room() {
        return _room;
    }

    public String get_codeacti() {
        return _codeacti;
    }

    public String get_codeevent() {
        return _codeevent;
    }

    public String get_codeinstance() {
        return _codeinstance;
    }

    public String get_dates() {
        return _dates;
    }

    public boolean is_register_student() {
        return _register_student;
    }

    public String get_type_title() {
        return _type_title;
    }

    public int get_num_event() {
        return _num_event;
    }

    public String get_end() {
        return _end;
    }

    public String get_scolaryear() {
        return _scolaryear;
    }

    public boolean is_module_registered() {
        return _module_registered;
    }

    public boolean is_past() {
        return _past;
    }

    public boolean is_module_available() {
        return _module_available;
    }
}
