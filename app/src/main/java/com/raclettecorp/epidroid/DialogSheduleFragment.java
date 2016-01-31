package com.raclettecorp.epidroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Théophile on 31/01/2016.
 */
public class DialogSheduleFragment extends DialogFragment
{
    private PlanningList _schedule;
    private ListView _schedulesView;

    public static DialogSheduleFragment newInstance(PlanningList schedule) {
        DialogSheduleFragment dialog = new DialogSheduleFragment();
        Bundle args = new Bundle();
        args.putSerializable("schedule", schedule);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.list_view_custom, container, false);

        if (getArguments() != null)
        {
            _schedule = (PlanningList) getArguments().getSerializable("schedule");

            ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getActivity(), _schedule.getmListPlanning());
            _schedulesView = (ListView) v.findViewById(R.id.listSchedulesView);
            _schedulesView.setAdapter(scheduleAdapter);
        }

        getDialog().setTitle("Activities");

        return v;
    }
}
