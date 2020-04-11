package com.example.antenatal;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Profile extends Fragment {
    TextView myname, myphone,myaddress,noday,daydetail,
    noweek,weekdetail,nomonth,monthdetail,dayC, weekC,ldp,dd, monthC;
    CalendarView calendarView2;
    ProgressBar progressBar;
    ImageView fetalimage;

    private dbHelper dbHelper;

    private OnFragmentInteractionListener mListener;
    private SharedPreferences MyId;
    String userID;
    int dayCount,monthCount,weekCount;
    public Profile() {
        // Required empty public constructor
    }

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        FetalPage tabFragment = new FetalPage();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }
    int post;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        post = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        MyId = getActivity().getSharedPreferences("MyId", getActivity().MODE_PRIVATE);
        userID = MyId.getString("MyId", "");

        myname = view.findViewById(R.id.myname);
        myphone = view.findViewById(R.id.myphone);
        myaddress = view.findViewById(R.id.myaddress);

        noday = view.findViewById(R.id.noday);
        daydetail = view.findViewById(R.id.daydetail);

        noweek = view.findViewById(R.id.noweek);
        weekdetail = view.findViewById(R.id.weekdetail);

        nomonth = view.findViewById(R.id.nomonth);
        monthdetail = view.findViewById(R.id.monthdetail);

        dayC = view.findViewById(R.id.dayC);
        weekC = view.findViewById(R.id.weekC);

        monthC = view.findViewById(R.id.monthC);
        ldp = view.findViewById(R.id.ldp);
        dd = view.findViewById(R.id.dd);
        fetalimage = view.findViewById(R.id.fetalimage);

        progressBar = view.findViewById(R.id.progressBar);
        calendarView2 = view.findViewById(R.id.calendarView2);

        LoadLocalData();

        return view;
    }


    public void LoadLocalData(){
            try {
                dbHelper = new dbHelper(getContext());
                Cursor allnews = dbHelper.getAPregnantInfo(userID);
                if (allnews.getCount() > 0) {
                    allnews.moveToFirst();
                    allnews.getString(allnews.getColumnIndex(dbColumnList.pregnantInformation.COLUMN_STATRDATE));
                    allnews.getString(allnews.getColumnIndex(dbColumnList.pregnantInformation.COLUMN_ENDDATE));
                    allnews.getString(allnews.getColumnIndex(dbColumnList.pregnantInformation.COLUMN_BABYDESCRIPTION));
                    allnews.getString(allnews.getColumnIndex(dbColumnList.pregnantInformation.COLUMN_NOOFBABY));

                    String DATE_FORMAT= "EEE, d MMM yyyy, HH:mm:ss";
                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

                    Calendar startDate = Calendar.getInstance();
                    Calendar endDate = Calendar.getInstance();
                    startDate.setTime(
                                    sdf.parse(
                                            allnews.getString(allnews.getColumnIndex(dbColumnList.pregnantInformation.COLUMN_STATRDATE))
                                    ));

                    endDate.setTime(
                            sdf.parse(
                                    allnews.getString(allnews.getColumnIndex(dbColumnList.pregnantInformation.COLUMN_ENDDATE))
                            ));
                    ldp.setText(sdf.format(startDate.getTime()));
                    dd.setText(sdf.format(endDate.getTime()));

//                    calendarView2.setMaxDate();

                    monthsBetweenDates(allnews.getString(allnews.getColumnIndex(dbColumnList.pregnantInformation.COLUMN_STATRDATE)));
                }
                allnews.close();

                allnews = dbHelper.getAUser(userID);
                if (allnews.getCount() > 0) {

                    myname.setText(allnews.getString(allnews.getColumnIndex(dbColumnList.antenantalUser.COLUMN_FULLNAME)));
                    myaddress.setText(allnews.getString(allnews.getColumnIndex(dbColumnList.antenantalUser.COLUMN_CONTACTADD)));
                    myphone.setText(allnews.getString(allnews.getColumnIndex(dbColumnList.antenantalUser.COLUMN_PHONE)) + " / "+
                            allnews.getString(allnews.getColumnIndex(dbColumnList.antenantalUser.COLUMN_EMAIL)));
                }
                allnews.close();
            } catch (ParseException e) {
                e.printStackTrace();
            } finally { }
    }


    public void monthsBetweenDates(String startDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar startCalender = Calendar.getInstance();
        Calendar todayCalender = Calendar.getInstance();
        try {
            startCalender.setTime(sdf.parse(startDate));
            Date today = new Date();
            Date todayWithZeroTime = sdf.parse(sdf.format(today));
            todayCalender.setTime(todayWithZeroTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        int sYear = startCalender.get(Calendar.YEAR);
        int sMonth = startCalender.get(Calendar.MONTH);
        int sDay = startCalender.get(Calendar.DAY_OF_MONTH);

        int tYear = todayCalender.get(Calendar.YEAR);
        int tMonth = todayCalender.get(Calendar.MONTH);
        int tDay = todayCalender.get(Calendar.DAY_OF_MONTH);


        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date1.clear();
        date1.set(sYear, sMonth, sDay);
        date2.clear();
        date2.set(tYear, tMonth, tDay);

        long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        dayCount = (int) diff / (24 * 60 * 60 * 1000);

        weekCount = (int) diff / (7 * 24 * 60 * 60 * 1000);
        weekCount += diff / (7 * 24 * 60 * 60 * 1000) > 0 ? 1:0;

        monthCount = (int) diff / (4*7 * 24 * 60 * 60 * 1000);
        monthCount += diff / (4*7 * 24 * 60 * 60 * 1000) > 0 ? 1:0;

        noday.setText(dayCount +"\n Days");
        noweek.setText(weekCount + "\n Weeks");
        nomonth.setText(monthCount + "\n Months");

        dayC.setText(dayCount +"\n Days");
        weekC.setText(weekCount + "\n Weeks");
        monthC.setText(monthCount + "\n Months");

        progressBar.setProgress(dayCount);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
