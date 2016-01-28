package com.raclettecorp.epidroid;

/**
 * Created by Théophile on 27/01/2016.
 */
public class ApiIntraPlanning {
    private String prof_inst; // changer
    private String titlePlanning;
    private String rdv_indiv_registered;
    private String allowed_planning_end;
    private int nb_group;
    private String start;
    private String register_month;
    private String allowed_planning_start;
    private boolean project;
    private String event_registered;
    private int total_students_registered;
    private boolean allow_register;
    private String codemodule;
    private String rdv_group_registered;
    private boolean semester;
    private String type_code;
    private String is_rdv;
    private boolean allow_token;
    private String titlemodule;
    private boolean in_more_than_one_month;
    private String acti_title;
    private String instance_location;
    private String nb_hours;
    private String register_prof;
    private int nb_max_students_projet;
    private String room; // A changer
    private String codeacti;
    private String codeevent;
    private String codeinstance;
    private String dates;
    private boolean register_student;
    private String type_title;
    private int num_event;
    private String end;
    private String scolaryear;
    private boolean module_registered;
    private boolean past;
    private boolean module_available;

    ApiIntraPlanning()
    {
        prof_inst = "";
        titlePlanning = "";
        rdv_indiv_registered = "";
        allowed_planning_end = "";
        nb_group = 0;
        start = "";
        register_month = "";
        allowed_planning_start = "";
        project = false;
        event_registered = "";
        total_students_registered = 0;
        allow_register = false;
        codemodule = "";
        rdv_group_registered = "";
        semester = false;
        type_code = "";
        is_rdv = "";
        allow_token = false;
        titlemodule = "";
        in_more_than_one_month = false;
        acti_title = "";
        instance_location = "";
        nb_hours = "";
        register_prof = "";
        nb_max_students_projet = 0;
        room = ""; // A changer
        codeacti = "";
        codeevent = "";
        codeinstance = "";
        dates = "";
        register_student = false;
        type_title = "";
        num_event = 0;
        end = "";
        scolaryear = "";
        module_registered = false;
        past = false;
        module_available = false;
    }

    public String getProf_inst() {
        return prof_inst;
    }

    public String getTitlePlanning() {
        return titlePlanning;
    }

    public String getRdv_indiv_registered() {
        return rdv_indiv_registered;
    }

    public String getAllowed_planning_end() {
        return allowed_planning_end;
    }

    public int getNb_group() {
        return nb_group;
    }

    public String getStart() {
        return start;
    }

    public String getRegister_month() {
        return register_month;
    }

    public String getAllowed_planning_start() {
        return allowed_planning_start;
    }

    public boolean isProject() {
        return project;
    }

    public String getEvent_registered() {
        return event_registered;
    }

    public int getTotal_students_registered() {
        return total_students_registered;
    }

    public boolean isAllow_register() {
        return allow_register;
    }

    public String getCodemodule() {
        return codemodule;
    }

    public String getRdv_group_registered() {
        return rdv_group_registered;
    }

    public boolean isSemester() {
        return semester;
    }

    public String getType_code() {
        return type_code;
    }

    public String getIs_rdv() {
        return is_rdv;
    }

    public boolean isAllow_token() {
        return allow_token;
    }

    public String getTitlemodule() {
        return titlemodule;
    }

    public boolean isIn_more_than_one_month() {
        return in_more_than_one_month;
    }

    public String getActi_title() {
        return acti_title;
    }

    public String getInstance_location() {
        return instance_location;
    }

    public String getNb_hours() {
        return nb_hours;
    }

    public String getRegister_prof() {
        return register_prof;
    }

    public int getNb_max_students_projet() {
        return nb_max_students_projet;
    }

    public String getRoom() {
        return room;
    }

    public String getCodeacti() {
        return codeacti;
    }

    public String getCodeevent() {
        return codeevent;
    }

    public String getCodeinstance() {
        return codeinstance;
    }

    public String getDates() {
        return dates;
    }

    public boolean isRegister_student() {
        return register_student;
    }

    public String getType_title() {
        return type_title;
    }

    public int getNum_event() {
        return num_event;
    }

    public String getEnd() {
        return end;
    }

    public String getScolaryear() {
        return scolaryear;
    }

    public boolean isModule_registered() {
        return module_registered;
    }

    public boolean isPast() {
        return past;
    }

    public boolean isModule_available() {
        return module_available;
    }
}
