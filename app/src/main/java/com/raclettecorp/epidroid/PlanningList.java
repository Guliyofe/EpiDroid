package com.raclettecorp.epidroid;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Théophile on 31/01/2016.
 */
public class PlanningList implements Serializable
{
    private static final long serialVersionUID = 3350092881346723535L;

    private List<Planning> mListPlanning;

    PlanningList(List<Planning> listPlanning)
    {
        mListPlanning = listPlanning;
    }

    public List<Planning> getmListPlanning()
    {
        return mListPlanning;
    }
}
