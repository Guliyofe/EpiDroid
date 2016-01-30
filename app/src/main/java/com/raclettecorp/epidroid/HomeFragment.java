package com.raclettecorp.epidroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;


/*
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
    private TextView _gpaView = null;
    private TextView _logView = null;
    private ListView _historyView = null;
    private View _progressView = null;
    private View _headerView = null;

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
        _gpaView = (TextView) getView().findViewById(R.id.textGpaView);
        _logView = (TextView) getView().findViewById(R.id.textLogView);
        _progressView = getView().findViewById(R.id.progressInfosBar);
        _historyView = (ListView) getView().findViewById(R.id.listHistoryView);
        _headerView = ((NavigationView)getActivity().findViewById(R.id.nvView)).getHeaderView(0);
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

            _logView.setVisibility(show ? View.GONE : View.VISIBLE);
            _gpaView.setVisibility(show ? View.GONE : View.VISIBLE);
            _firstNameView.setVisibility(show ? View.GONE : View.VISIBLE);
            _lastNameView.setVisibility(show ? View.GONE : View.VISIBLE);
            _loginView.setVisibility(show ? View.GONE : View.VISIBLE);
            _historyView.setVisibility(show ? View.GONE : View.VISIBLE);

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
            _logView.setVisibility(show ? View.GONE : View.VISIBLE);
            _gpaView.setVisibility(show ? View.GONE : View.VISIBLE);
            _firstNameView.setVisibility(show ? View.GONE : View.VISIBLE);
            _lastNameView.setVisibility(show ? View.GONE : View.VISIBLE);
            _loginView.setVisibility(show ? View.GONE : View.VISIBLE);
            _historyView.setVisibility(show ? View.GONE : View.VISIBLE);
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
                if (isAdded())
                {
                    _loginView.setText(_infos.getInfos().getLogin());
                    _lastNameView.setText(_infos.getInfos().getLastName());
                    _firstNameView.setText(_infos.getInfos().getFirstName());
                    HistoryAdapter historyAdapter = new HistoryAdapter(getActivity(), Arrays.asList(_infos.getHistory()));
                    _historyView.setAdapter(historyAdapter);
                    GetUserTask userTask = new GetUserTask(_context, _api, _infos.getInfos().getLogin());
                    userTask.execute((Void) null);
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

    public class GetUserTask extends AsyncTask<Void, Void, Boolean>
    {

        private final Context _context;
        private final ApiIntra _api;
        private final String _login;
        private ApiIntraUser _user;

        GetUserTask(Context c, ApiIntra api, String login)
        {
            _context = c;
            _api = api;
            _login = login;
            _user = null;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            try
            {
                if ((_user = _api.requestUser(_context, _login)) == null)
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
                    new ImageLoadTask(_user.getUser().getPicture(), (ImageView) _headerView.findViewById(R.id.photoProfile)).execute();
                    ((TextView) _headerView.findViewById(R.id.loginProfile)).setText(_user.getUser().getLogin());
                    _gpaView.setText(getString(R.string.gpa_infos) + _user.getGpa(0).getGpa());
                    _logView.setText(getString(R.string.log_infos_start) + _user.getNs().getActive());
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

    public static class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }

}
