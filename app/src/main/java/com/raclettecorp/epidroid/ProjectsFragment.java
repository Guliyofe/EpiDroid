package com.raclettecorp.epidroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;


public class ProjectsFragment extends Fragment
{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private ApiIntra mParam1;

    private ListView _projectsView;
    private View _progressView;

    private ScheduleFragment.OnFragmentInteractionListener mListener;

    public ProjectsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ProjectsFragment.
     */
    public static ProjectsFragment newInstance(ApiIntra param1) {
        ProjectsFragment fragment = new ProjectsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (ApiIntra) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_projects, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _projectsView = (ListView) getView().findViewById(R.id.listProjectsView);
        _projectsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                ApiIntraProjects.Project project = (ApiIntraProjects.Project) _projectsView.getItemAtPosition(position);
                showProgressOneProject(true);
                new GetProjectTask(getActivity(), mParam1, project.getScolarYear(), project.getCodeModule(), project.getCodeInstance(), project.getCodeActi()).execute();
            }

        });
        _progressView = getView().findViewById(R.id.progressProjectsBar);
        new GetProjectsTask(getActivity(), mParam1).execute();
        showProgress(true);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            _projectsView.setVisibility(show ? View.GONE : View.VISIBLE);

            _progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            _progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    _progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }
        else
        {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            _progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            _projectsView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgressOneProject(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            _progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            _progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    _progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }
        else
        {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            _progressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ScheduleFragment.OnFragmentInteractionListener) {
            mListener = (ScheduleFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public class GetProjectsTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final ApiIntra _api;
        private ApiIntraProjects _projects;

        GetProjectsTask(Context c, ApiIntra api)
        {
            _context = c;
            _api = api;
            _projects = null;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                if ((_projects = _api.requestProjects(_context)) == null)
                    return false;
            }
            catch (Exception e) {
                Log.d(getString(R.string.app_name), e.toString());
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {

            if (success) {
                if (isAdded()) {
                    ProjectAdapter projectAdapter = new ProjectAdapter(getActivity(), Arrays.asList(_projects.getProjects()));
                    _projectsView.setAdapter(projectAdapter);
                    showProgress(false);
                }
            }
        }

        @Override
        protected void onCancelled()
        {

        }
    }

    public class GetProjectTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final ApiIntra _api;
        private final String _scolarYear;
        private final String _codeModule;
        private final String _codeInstance;
        private final String _codeActi;
        private ApiIntraProject _project;

        GetProjectTask(Context c, ApiIntra api, String scolarYear, String codeModule, String codeInstance, String codeActi)
        {
            _context = c;
            _api = api;
            _project = null;
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
                if ((_project = _api.requestProject(_context, _scolarYear, _codeModule, _codeInstance, _codeActi)) == null)
                    return false;
            }
            catch (Exception e) {
                Log.d(getString(R.string.app_name), e.toString());
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {

            if (success) {
                if (isAdded()) {
                    DialogProjectFragment dialogProject = new DialogProjectFragment().newInstance(_project, mParam1);
                    dialogProject.show(getActivity().getSupportFragmentManager(), "fuckYou");
                    showProgressOneProject(false);
                }
                return;
            }
        }

        @Override
        protected void onCancelled()
        {

        }
    }

}
