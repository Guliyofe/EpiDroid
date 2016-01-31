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

public class DialogProjectFragment extends DialogFragment
{
    private TextView _titleView;
    private TextView _descriptionView;
    private TextView _beginView;
    private TextView _endRegisterView;
    private TextView _endView;
    private Button _shareButton;
    private Button _subButton;
    private Button _unsubButton;

    private ApiIntraProject _project;
    private ApiIntra _api;

    public static DialogProjectFragment newInstance(ApiIntraProject project, ApiIntra api) {
        DialogProjectFragment dialog = new DialogProjectFragment();
        Bundle args = new Bundle();
        args.putSerializable("project", project);
        args.putSerializable("api", api);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.dialog_project, container, false);

        if (getArguments() != null) {
            _project = (ApiIntraProject) getArguments().getSerializable("project");
            _api = (ApiIntra) getArguments().getSerializable("api");
        }

        _titleView = (TextView) v.findViewById(R.id.textTitleViewProject);
        _titleView.setText(_project.getModuleTitle());
        _descriptionView = (TextView) v.findViewById(R.id.textDescriptionViewProject);
        _descriptionView.setText(_project.getDescription());
        _beginView = (TextView) v.findViewById(R.id.textBeginViewProject);
        _beginView.setText(getString(R.string.string_begin_module) + _project.getBegin());
        _endRegisterView = (TextView) v.findViewById(R.id.textEndRegisterViewProject);
        _endRegisterView.setText(getString(R.string.string_end_register_module) + _project.getEndRegistered());
        _endView = (TextView) v.findViewById(R.id.textEndViewProject);
        _endView.setText(getString(R.string.string_end_module) + _project.getEnd());
        _shareButton = (Button) v.findViewById(R.id.buttonShareProject);
        _shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody;
                Log.d("Registered", _project.getRegistered().toString());
                if (_project.getRegistered()) {
                    shareBody = "I'm doing " + _project.getTitle() + " and you ?";
                } else {
                    shareBody = "You know " + _project.getTitle() + " ?";
                }
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hi, do you know " + _project.getTitle() + " ?");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        _subButton = (Button) v.findViewById(R.id.buttonSubProject);
        _subButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new SubProjectTask(getActivity(), _api, _project.getScolarYear(), _project.getCodeModule(), _project.getCodeInstance(), _project.getCodeActi()).execute();
            }
        });
        _unsubButton = (Button) v.findViewById(R.id.buttonUnsubProject);
        _unsubButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new UnsubProjectTask(getActivity(), _api, _project.getScolarYear(), _project.getCodeModule(), _project.getCodeInstance(), _project.getCodeActi()).execute();
            }
        });
        getDialog().setTitle(_project.getTitle());

        return v;
    }

    public class UnsubProjectTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final ApiIntra _api;
        private final String _scolarYear;
        private final String _codeModule;
        private final String _codeInstance;
        private final String _codeActi;

        UnsubProjectTask(Context c, ApiIntra api, String scolarYear, String codeModule, String codeInstance, String codeActi)
        {
            _context = c;
            _api = api;
            _scolarYear = scolarYear;
            _codeInstance = codeInstance;
            _codeModule = codeModule;
            _codeActi = codeActi;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                _api.unsubProject(_context, _scolarYear, _codeModule, _codeInstance, _codeActi);
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

            if (success) {
                if (isAdded()) {
                    new AlertDialog.Builder(getActivity())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle(R.string.string_unsub)
                            .setPositiveButton(R.string.alert_dialog_ok,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int whichButton) {
                                            getDialog().cancel();
                                        }
                                    }).create().show();
                }
            }
        }

        @Override
        protected void onCancelled()
        {

        }
    }

    public class SubProjectTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final ApiIntra _api;
        private final String _scolarYear;
        private final String _codeModule;
        private final String _codeInstance;
        private final String _codeActi;

        SubProjectTask(Context c, ApiIntra api, String scolarYear, String codeModule, String codeInstance, String codeActi)
        {
            _context = c;
            _api = api;
            _scolarYear = scolarYear;
            _codeInstance = codeInstance;
            _codeModule = codeModule;
            _codeActi = codeActi;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                _api.subProject(_context, _scolarYear, _codeModule, _codeInstance, _codeActi);
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

            if (success) {
                if (isAdded()) {
                    new AlertDialog.Builder(getActivity())
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle(R.string.string_sub)
                            .setPositiveButton(R.string.alert_dialog_ok,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int whichButton) {
                                            getDialog().cancel();
                                        }
                                    }).create().show();
                }
            }
        }

        @Override
        protected void onCancelled()
        {

        }
    }
}
