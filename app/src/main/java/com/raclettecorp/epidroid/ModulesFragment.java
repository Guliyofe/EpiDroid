package com.raclettecorp.epidroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;


/*
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModulesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModulesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModulesFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private ApiIntra mParam1;

    private ListView _modulesView;
    private View _progressView;

    private ScheduleFragment.OnFragmentInteractionListener mListener;

    public ModulesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ModulesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModulesFragment newInstance(ApiIntra param1) {
        ModulesFragment fragment = new ModulesFragment();
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
        return inflater.inflate(R.layout.fragment_modules, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _modulesView = (ListView) getView().findViewById(R.id.listModulesView);
        _modulesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                Module module = (Module) _modulesView.getItemAtPosition(position);
                showProgressOneModule(true);
                new GetModuleTask(getActivity(), mParam1, String.valueOf(module.getScholarYear()), module.getCodeModule(), module.getCodeInstance(), module.getGrade()).execute();

                /*Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "I've got an " + module.getGrade() + " in " + module.getTitle();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hi, want to see my grade ?");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));*/

            }

        });
        _progressView = getView().findViewById(R.id.progressModulesBar);
        new GetModulesTask(getActivity(), mParam1).execute();
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

            _modulesView.setVisibility(show ? View.GONE : View.VISIBLE);

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
            _modulesView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgressOneModule(final boolean show) {
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

    public class GetModulesTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final ApiIntra _api;
        private ApiIntraModules _modules;

        GetModulesTask(Context c, ApiIntra api)
        {
            _context = c;
            _api = api;
            _modules = null;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                if ((_modules = _api.requestModules(_context)) == null)
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

            if (success)
            {
                if (isAdded())
                {
                    ModuleAdapter moduleAdapter = new ModuleAdapter(getActivity(), Arrays.asList(_modules.getModules()));
                    _modulesView.setAdapter(moduleAdapter);
                    showProgress(false);
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

    public class GetModuleTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final ApiIntra _api;
        private final String _scolarYear;
        private final String _codeModule;
        private final String _codeInstance;
        private final String _grade;
        private ApiIntraModule _module;

        GetModuleTask(Context c, ApiIntra api, String scolarYear, String codeModule, String codeInstance, String grade)
        {
            _context = c;
            _api = api;
            _module = null;
            _scolarYear = scolarYear;
            _codeInstance = codeInstance;
            _codeModule = codeModule;
            _grade = grade;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                if ((_module = _api.requestModule(_context, _scolarYear, _codeModule, _codeInstance, _grade)) == null)
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

            if (success)
            {
                if (isAdded())
                {
                    DialogModuleFragment dialogModule = new DialogModuleFragment().newInstance(_module, mParam1);
                    //FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    dialogModule.show(getActivity().getSupportFragmentManager(), "fuckYou");
                    /*ModuleAdapter moduleAdapter = new ModuleAdapter(getActivity(), Arrays.asList(_modules.getModules()));
                    _modulesView.setAdapter(moduleAdapter);*/
                    showProgressOneModule(false);
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
