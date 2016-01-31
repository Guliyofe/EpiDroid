package com.raclettecorp.epidroid;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduleFragment extends Fragment{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private ApiIntra mParam1;
    private PlanningTask mPlanningTask = null;
    CalendarView mCalendar = null;
    String mSelectedDate = null;

    private OnFragmentInteractionListener mListener;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ScheduleFragment.
     */
    public static ScheduleFragment newInstance(ApiIntra param1) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam1 = (ApiIntra) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCalendar = (CalendarView) getView().findViewById(R.id.calendarView);
        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                mListener.onFragmentInteraction(view);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                mSelectedDate = Integer.toString(year) + "-" + "0" + Integer.toString(month + 1) + "-" + Integer.toString(dayOfMonth);
//                mCalendar.setVisibility(View.INVISIBLE);
                TextView output = (TextView) getView().findViewById(R.id.textView1);
                output.setText(mParam1.getApiIntraPlanning().getIntraPlanning(getActivity(), mSelectedDate, mSelectedDate));
            }

        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
        void onFragmentInteraction(View view);
    }

    public class PlanningTask extends AsyncTask<Void, Void, Boolean> {
        private final Context mC;
        private final ApiIntra mApi;
        private String mToken;

        PlanningTask(Context c, ApiIntra api) {
            mC = c;
            mApi = api;
            mToken = null;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try
            {

                Thread.sleep(2000);
            }
            catch (Exception e)
            {
                Log.d(getString(R.string.app_name), e.toString());
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mPlanningTask = null;
//            showProgress(false);

            if (success)
            {
/*                android.content.Intent i = new android.content.Intent(mC, MainActivity.class);
                i.putExtra(getString(R.string.string_api), mToken);
                mC.startActivity(i);*/
            }
            else
            {
/*                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();*/
            }
        }

        @Override
        protected void onCancelled() {
            mPlanningTask = null;
//            showProgress(false);
        }
    }
}
