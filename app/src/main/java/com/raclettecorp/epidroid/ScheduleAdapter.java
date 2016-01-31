package com.raclettecorp.epidroid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Théophile on 31/01/2016.
 */
public class ScheduleAdapter extends ArrayAdapter<Planning>
{
    private ModuleViewHolder viewHolder;

    public ScheduleAdapter(Context context, List<Planning> schedules)
    {
        super(context, 0, schedules);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_popup,parent, false);
        }

        viewHolder = (ModuleViewHolder) convertView.getTag();
        final Planning schedules = getItem(position);
        if(viewHolder == null)
        {
            viewHolder = new ModuleViewHolder();
            viewHolder._titleModuleView = (TextView) convertView.findViewById(R.id.textTitleModule);
            viewHolder._activityTitle = (TextView) convertView.findViewById(R.id.textActivityTitle);
            viewHolder._beginActivity = (TextView) convertView.findViewById(R.id.textActivityBeginDate);
            viewHolder._endActivity = (TextView) convertView.findViewById(R.id.textActivityEndDate);
            viewHolder._tokenButton = (Button) convertView.findViewById(R.id.buttonToken);

//        _room = (TextView) v.findViewById(R.id.textRoomTitle);
            convertView.setTag(viewHolder);
        }

        viewHolder._titleModuleView.setText(schedules.get_titlemodule());
        viewHolder._activityTitle.setText(schedules.get_acti_title());
        viewHolder._beginActivity.setText("Begin Activity : " + schedules.get_start());
        viewHolder._endActivity.setText("End Activity : " + schedules.get_end());
//        viewHolder.setText("End Activity : " + schedules.get_room().optString("code"));
        final View FuckYou = LayoutInflater.from(getContext()).inflate(R.layout.edit_token_box, parent, false);
        viewHolder._tokenButton.setVisibility(View.GONE);
        if (schedules.is_allow_token() == true)
        {
            viewHolder._tokenButton.setVisibility(View.VISIBLE);
            viewHolder._tokenButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    new AlertDialog.Builder(getContext())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle(R.string.string_token_validated)
                            .setView(R.layout.edit_token_box)
                            .setPositiveButton(R.string.alert_dialog_ok,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int whichButton) {
                                            final EditText editText = (EditText) FuckYou.findViewById(R.id.enter_token);
                                            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                                @Override
                                                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                                    boolean handled = false;
                                                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                                                        new ValidateTokenTask(getContext(),
                                                                schedules,
                                                                schedules.get_scolaryear(),
                                                                schedules.get_codemodule(),
                                                                schedules.get_codeinstance(),
                                                                schedules.get_codeacti(),
                                                                schedules.get_codeevent(),
                                                                editText.getText().toString()).execute();
                                                        handled = true;
                                                    }
                                                    return handled;
                                                }
                                            });
                                        }
                                    }).create().show();
                }
            });
        }
        return convertView;
    }

    public class ValidateTokenTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final Planning _api;
        private final String _scolarYear;
        private final String _codeModule;
        private final String _codeInstance;
        private final String _codeActi;
        private final String _codeEvent;
        private final String _tokenToValidate;

        ValidateTokenTask(Context c, Planning api, String scolarYear,
                          String codeModule,
                          String codeInstance,
                          String codeActi,
                          String codeEvent,
                          String tokenToValidate)
        {
            _context = c;
            _api = api;
            _scolarYear = scolarYear;
            _codeInstance = codeInstance;
            _codeModule = codeModule;
            _codeActi = codeActi;
            _codeEvent = codeEvent;
            _tokenToValidate = tokenToValidate;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                _api.validateToken(_context,
                        _scolarYear,
                        _codeModule,
                        _codeInstance,
                        _codeActi,
                        _codeEvent,
                        _tokenToValidate);
                return true;
            }
            catch (Exception e) {
//                Log.d(getString(R.string.app_name), e.toString());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {

            if (success)
            {
                new AlertDialog.Builder(_context)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.string_token_validated)
                        .setPositiveButton(R.string.alert_dialog_ok,
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton)
                                    {
                                    }
                                }).create().show();
            }
        }

        @Override
        protected void onCancelled()
        {

        }
    }

    private class ModuleViewHolder
    {
        private TextView _titleModuleView;
        private TextView _activityTitle;
        private TextView _beginActivity;
        private TextView _endActivity;
//        private TextView _room;
        private Button _tokenButton;
    }
}
