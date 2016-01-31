package com.raclettecorp.epidroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    private ApiIntraModule _module;

    public static DialogModuleFragment newInstance(ApiIntraModule module) {
        DialogModuleFragment dialog = new DialogModuleFragment();
        Bundle args = new Bundle();
        args.putSerializable("module", module);
        dialog.setArguments(args);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.dialog_module, container, false);

        if (getArguments() != null)
            _module = (ApiIntraModule) getArguments().getSerializable("module");
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

        getDialog().setTitle(_module.getTitle());

        return v;
    }
}
