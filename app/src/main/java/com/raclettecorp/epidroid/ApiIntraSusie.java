package com.raclettecorp.epidroid;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by bertrand on 29/01/16.
 */

public class ApiIntraSusie
{
        private int _id;
        private int _id_calendar;
        private Date _start, _end;


        ApiIntraSusie(JSONObject answer)
        {
            try
            {
                DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);
                _id = answer.getInt("id");
                _id_calendar = answer.getInt("id_calendar");
                try
                {
                    _start = df.parse(answer.getString("start"));
                    _end = df.parse(answer.getString("end"));
                }
                catch (ParseException eParse)
                {}
            }
            catch (JSONException e)
            {

            }
        }

        public int getId()
        {
            return _id;
        }

        public int getId_calendar()
        {
            return _id_calendar;
        }

        public Date getStartDate()
        {
            return _start;
        }

        public Date getEndDate()
    {
        return _end;
    }


}
