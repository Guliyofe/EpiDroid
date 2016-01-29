package com.raclettecorp.epidroid;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the*/
 // {@link HomeFragment.OnFragmentInteractionListener} interface
 /* to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private GetInfosTask minfosTask = null;
    private ApiIntra mParam1;

    private TextView _firstNameView = null;
    private TextView _lastNameView = null;
    private TextView _loginView = null;

    private ScheduleFragment.OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(ApiIntra param1) {
        HomeFragment fragment = new HomeFragment();
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
            minfosTask = new GetInfosTask(getActivity(), mParam1);
            minfosTask.execute((Void) null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _firstNameView = (TextView) getView().findViewById(R.id.textFirstNameView);
        _lastNameView = (TextView) getView().findViewById(R.id.textLastNameView);
        _loginView = (TextView) getView().findViewById(R.id.textLoginView);
    }

    /* TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

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

    public class GetInfosTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final ApiIntra _api;
        private ApiIntraInfos _infos;

        GetInfosTask(Context c, ApiIntra api)
        {
            _context = c;
            _api = api;
            _infos = null;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                if ((_infos = _api.requestInfos(_context)) == null)
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
                _loginView.setText(_infos.getInfos().getLogin());
                _lastNameView.setText(_infos.getInfos().getLastName());
                _firstNameView.setText(_infos.getInfos().getFirstName());
                GetUserTask userTask = new GetUserTask(_context, _api, _infos.getInfos().getLogin());
                userTask.execute((Void) null);
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

    public class GetUserTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final ApiIntra _api;
        private final String _login;
        private Object _infos;

        GetUserTask(Context c, ApiIntra api, String login)
        {
            _context = c;
            _api = api;
            _login = login;
            _infos = null;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                if ((_infos = _api.requestUser(_context, _login)) == null)
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
