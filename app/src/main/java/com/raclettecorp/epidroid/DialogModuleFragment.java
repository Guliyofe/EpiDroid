package com.raclettecorp.epidroid;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogModuleFragment extends DialogFragment
{

    private TextView _titleView;
    private TextView _descriptionView;
    private TextView _competenceView;
    private TextView _beginView;
    private TextView _endRegisterView;
    private TextView _endView;
    private TextView _gradeView;
    private Button _shareButton;
    private Button _subButton;
    private Button _unsubButton;
    private ApiIntraModule _module;
    private ApiIntra _api;

    public static DialogModuleFragment newInstance(ApiIntraModule module, ApiIntra api) {
        DialogModuleFragment dialog = new DialogModuleFragment();
        Bundle args = new Bundle();
        args.putSerializable("module", module);
        args.putSerializable("api", api);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.dialog_module, container, false);

        if (getArguments() != null)
        {
            _module = (ApiIntraModule) getArguments().getSerializable("module");
            _api = (ApiIntra) getArguments().getSerializable("api");
        }
        _titleView = (TextView) v.findViewById(R.id.textTitleViewModule);
        _titleView.setText(_module.getCodeModule());
        _descriptionView = (TextView) v.findViewById(R.id.textDescriptionViewModule);
        _descriptionView.setText(_module.getDescription());
        _competenceView = (TextView) v.findViewById(R.id.textCompetenceViewModule);
        _competenceView.setText(_module.getCompetence());
        _beginView = (TextView) v.findViewById(R.id.textBeginViewModule);
        _beginView.setText(getString(R.string.string_begin_module) + _module.getBegin());
        _endRegisterView = (TextView) v.findViewById(R.id.textEndRegisterViewModule);
        _endRegisterView.setText(getString(R.string.string_end_register_module) + _module.getEndRegister());
        _endView = (TextView) v.findViewById(R.id.textEndViewModule);
        _endView.setText(getString(R.string.string_end_module) + _module.getEnd());
        _gradeView = (TextView) v.findViewById(R.id.textGradeViewModule);
        _gradeView.setText(_module.getGrade());
        _shareButton = (Button) v.findViewById(R.id.buttonShareModule);
        _shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "I've got an " + _module.getGrade() + " in " + _module.getTitle();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hi, want to see my grade ?");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date nowDate = new Date();
            Date beginDate = df.parse(_module.getBegin());
            Date endDate = df.parse(_module.getEndRegister());
            _subButton = (Button) v.findViewById(R.id.buttonSubModule);
            if (nowDate.compareTo(beginDate) > 0 && nowDate.compareTo(endDate) < 0)
                _subButton.setVisibility(View.VISIBLE);
            else
                _subButton.setVisibility(View.GONE);
            _subButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //new UnsubModuleTask(getActivity(), _api, _module.getScolarYear(), _module.getCodeModule(), _module.getCodeInstance()).execute();
                }
            });
            _unsubButton = (Button) v.findViewById(R.id.buttonUnsubModule);
            if (nowDate.compareTo(beginDate) > 0 && nowDate.compareTo(endDate) < 0)
                _unsubButton.setVisibility(View.VISIBLE);
            else
                _unsubButton.setVisibility(View.GONE);
            _unsubButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    new UnsubModuleTask(getActivity(), _api, _module.getScolarYear(), _module.getCodeModule(), _module.getCodeInstance()).execute();
                }
            });
        }
        catch (Exception e)
        {
            Log.d(getString(R.string.app_name), e.toString());
        }

        getDialog().setTitle(_module.getTitle());

        return v;
    }

    public class UnsubModuleTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final ApiIntra _api;
        private final String _scolarYear;
        private final String _codeModule;
        private final String _codeInstance;

        UnsubModuleTask(Context c, ApiIntra api, String scolarYear, String codeModule, String codeInstance)
        {
            _context = c;
            _api = api;
            _module = null;
            _scolarYear = scolarYear;
            _codeInstance = codeInstance;
            _codeModule = codeModule;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                _api.unsubModule(_context, _scolarYear, _codeModule, _codeInstance);
                return true;
            }
            catch (Exception e) {
                Log.d(getString(R.string.app_name), e.toString());
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {

            if (success)
            {
                if (isAdded())
                {
                    new AlertDialog.Builder(getActivity())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle(R.string.string_unsub)
                            .setPositiveButton(R.string.alert_dialog_ok,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int whichButton) {

                                        }
                                    }).create().show();
                }
                return;
            }
            else
            {
                return;
            }
        }

        @Override
        protected void onCancelled()
        {

        }
    }
}
