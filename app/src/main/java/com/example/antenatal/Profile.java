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
    noweek,weekdetail,nomonth,monthdetail,dayC, weekC,ldp,dd, monthC, trim;
    CalendarView calendarView2;
    ProgressBar progressBar;
    ImageView img;

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
        Profile tabFragment = new Profile();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }
    int post;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        post = getArguments().getInt("pos");
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

        trim = view.findViewById(R.id.trim);

        nomonth = view.findViewById(R.id.nomonth);
        monthdetail = view.findViewById(R.id.monthdetail);

        dayC = view.findViewById(R.id.dayC);
        weekC = view.findViewById(R.id.weekC);

        monthC = view.findViewById(R.id.monthC);
        ldp = view.findViewById(R.id.ldp);
        dd = view.findViewById(R.id.dd);
        img = view.findViewById(R.id.fetalimage);

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

                    String DATE_FORMAT= "yyyy-MM-dd";
                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);



                    Date START = sdf.parse(
                            allnews.getString(allnews.getColumnIndex(dbColumnList.pregnantInformation.COLUMN_STATRDATE))
                    );
                    Date END = sdf.parse(
                            allnews.getString(allnews.getColumnIndex(dbColumnList.pregnantInformation.COLUMN_ENDDATE))
                    );

                    Calendar startDate = Calendar.getInstance();
                    Calendar endDate = Calendar.getInstance();
                    startDate.set(START.getYear(),START.getMonth(),START.getDay());
                    endDate.set(END.getYear(),END.getMonth(),END.getDay());

                    String DATE_FORMATTWO= "EEE, dd MMMM yyyy";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATTWO);

                    ldp.setText("Conception Date : " + dateFormat.format(startDate.getTime()));
                    dd.setText("Expected Due Date : "+ dateFormat.format(endDate.getTime()));

                    System.out.printf("Year o " + END.getYear());
                    System.out.printf("Year o " + START.getYear());

//                    calendarView2.setMaxDate(endDate.getTimeInMillis());

                    monthsBetweenDates(allnews.getString(allnews.getColumnIndex(dbColumnList.pregnantInformation.COLUMN_STATRDATE)));
                }
                allnews.close();

                allnews = dbHelper.getAUser(userID);
                if (allnews.getCount() > 0) {
                    allnews.moveToFirst();
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
        dayCount = (int) (diff / (24 * 60 * 60 * 1000));
        weekCount = (int) (diff / (7 * 24 * 60 * 60 * 1000));
        weekCount += (diff % (7 * 24 * 60 * 60 * 1000)) > 0 ? 1:0;
//        System.currentTimeMillis()
        monthCount = (int) (diff / (2419200000f));
        monthCount += (diff % (2419200000f)) > 0 ? 1:0;

        noday.setText(dayCount +"\n Days");
        noweek.setText(weekCount + "\n Weeks");
        nomonth.setText(monthCount + "\n Months");

        dayC.setText(dayCount +"\n Days");
        weekC.setText(weekCount + "\n Weeks");
        monthC.setText(monthCount + "\n Months");

        progressBar.setProgress(dayCount);

        if (monthCount<=3){
            trim.setText("First Trimester");
        }else if (monthCount <=6){
            trim.setText("Second Trimester");
        }else{
            trim.setText("Third Trimester");
        }


        int post = dayCount;
        if(post <=7){
            img.setImageResource(R.drawable.week1);
        }else if(post <=14){
            img.setImageResource(R.drawable.week2);
        }else if(post <=21){
            img.setImageResource(R.drawable.week3);
        }else if(post <=28){
            img.setImageResource(R.drawable.week4);
        }else if(post <=35){
            img.setImageResource(R.drawable.week5);
        }else if(post <=42){
            img.setImageResource(R.drawable.week6);
        }else if(post <=49){
            img.setImageResource(R.drawable.week7);
        }else if(post <=56){
            img.setImageResource(R.drawable.week8);
        }else if(post <=63){
            img.setImageResource(R.drawable.week9);
        }else if(post <=70){
            img.setImageResource(R.drawable.week10);
        }else if(post <=77){
            img.setImageResource(R.drawable.week11);
        }else if(post <=84){
            img.setImageResource(R.drawable.week12);
        }else if(post <=91){
            img.setImageResource(R.drawable.week13);
        }else if(post <=98){
            img.setImageResource(R.drawable.week14);
        }else if(post <=105){
            img.setImageResource(R.drawable.week15);
        }else if(post <=112){
            img.setImageResource(R.drawable.week16);
        }else if(post <=119){
            img.setImageResource(R.drawable.week17);
        }else if(post <=126){
            img.setImageResource(R.drawable.week18);
        }else if(post <=133){
            img.setImageResource(R.drawable.week19);
        }else if(post <=140){
            img.setImageResource(R.drawable.week20);
        }else if(post <=147){
            img.setImageResource(R.drawable.week21);
        }else if(post <=154){
            img.setImageResource(R.drawable.week22);
        }else if(post <=161){
            img.setImageResource(R.drawable.week23);
        }else if(post <=168){
            img.setImageResource(R.drawable.week24);
        }else if(post <=175){
            img.setImageResource(R.drawable.week25);
        }else if(post <=182){
            img.setImageResource(R.drawable.week26);
        }else if(post <=189){
            img.setImageResource(R.drawable.week27);
        }else if(post <=196){
            img.setImageResource(R.drawable.week28);
        }else if(post <=203){
            img.setImageResource(R.drawable.week29);
        }else if(post <=210){
            img.setImageResource(R.drawable.week30);
        }else if(post <=217){
            img.setImageResource(R.drawable.week31);
        }else if(post <=224){
            img.setImageResource(R.drawable.week32);
        }else if(post <=231){
            img.setImageResource(R.drawable.week33);
        }else if(post <=238){
            img.setImageResource(R.drawable.week34);
        }else if(post <=245){
            img.setImageResource(R.drawable.week35);
        }else if(post <=252){
            img.setImageResource(R.drawable.week36);
        }else if(post <=259){
            img.setImageResource(R.drawable.week37);
        }else if(post <=266){
            img.setImageResource(R.drawable.week38);
        }else if(post <=273){
            img.setImageResource(R.drawable.week39);
        }else {
            img.setImageResource(R.drawable.week40);
        }
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
