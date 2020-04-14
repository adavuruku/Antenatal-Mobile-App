package com.example.antenatal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class Profile extends Fragment {
    TextView myname, myphone,myaddress,noday,daydetail,
    noweek,weekdetail,nomonth,monthdetail,dayC, weekC,ldp,dd, monthC, trim;
    CalendarView calendarView2;
    ProgressBar progressBar;
    ImageView img,nutriimage;
    CardView month, day, week;
    doctorAdapter doctorListAdapter,contactListAdapter ;
    RecyclerView doctor,contact;
    private List<myModels.Doctor> allDoctorList, allContactList;

    private dbHelper dbHelper;

    private OnFragmentInteractionListener mListener;
    private SharedPreferences MyId;
    String userID,allResult,allContactResult;
    int dayCount,monthCount,weekCount,trimCount;
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

        month = view.findViewById(R.id.month);

        noday = view.findViewById(R.id.noday);
        daydetail = view.findViewById(R.id.daydetail);

        noweek = view.findViewById(R.id.noweek);
        weekdetail = view.findViewById(R.id.weekdetail);

        trim = view.findViewById(R.id.trim);

        nomonth = view.findViewById(R.id.nomonth);
        monthdetail = view.findViewById(R.id.monthdetail);

        MyId = getActivity().getSharedPreferences("MyId", getActivity().MODE_PRIVATE);
        userID = MyId.getString("MyId", "");

        allDoctorList = new ArrayList<>();
        allContactList = new ArrayList<>();
        dbHelper = new dbHelper(getContext());
        ldp = view.findViewById(R.id.ldp);
        dd = view.findViewById(R.id.dd);
        img = view.findViewById(R.id.fetalimage);
        nutriimage = view.findViewById(R.id.nutriimage);
        nutriimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NutritionTips.class);
                intent.putExtra("TABID",String.valueOf(weekCount - 1));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });



        day = view.findViewById(R.id.day);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DailyTips.class);
                intent.putExtra("TABID",String.valueOf(dayCount - 1));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        week = view.findViewById(R.id.week);
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WeeklyTips.class);
                intent.putExtra("TABID",String.valueOf(weekCount - 1));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        month = view.findViewById(R.id.month);
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MonthlyTips.class);
                intent.putExtra("TABID",String.valueOf(monthCount - 1));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FetalTips.class);
                intent.putExtra("TABID",String.valueOf(weekCount - 1));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });


        trim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TrimesterTips.class);
                intent.putExtra("TABID",String.valueOf(trimCount));
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        progressBar = view.findViewById(R.id.progressBar);
        calendarView2 = view.findViewById(R.id.calendarView2);

        LoadLocalData();

        doctor = view.findViewById(R.id.doctors);
        doctor.setLayoutManager(new LinearLayoutManager(getContext()));
        doctor.setHasFixedSize(true);

        contact = view.findViewById(R.id.contacts);
        contact.setLayoutManager(new LinearLayoutManager(getContext()));
        contact.setHasFixedSize(true);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                volleyDoctorRequest(dbColumnList.address);
                volleyContactRequest(dbColumnList.address);
            }
        },2000);
        return view;
    }


    // loading Doctors / Nurses for patient
    public void volleyDoctorRequest(String url){
        String  REQUEST_TAG = "com.volley.volleyDoctorRequest";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if (response.length()>2){
                            allResult = response;
//                           Toast.makeText(getApplicationContext(), response ,Toast.LENGTH_LONG).show();
                            new ReadDoctorsJSON().execute();
                        }else{
                            new LoadLocalDoctor().execute();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new LoadLocalDoctor().execute();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("opr", "loadDoctors");
                params.put("userID", userID);
                return params;
            }
        };
        AppSingleton.getInstance(getActivity()).addToRequestQueue(postRequest, REQUEST_TAG);
    }

    class ReadDoctorsJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                allDoctorList.clear();
                dbHelper.deleteDoctors();
                JSONArray jsonarray = new JSONArray(allResult);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    dbHelper.saveDoctorInformation(
                            jsonobject.getString("docId"),jsonobject.getString("docname"),
                            jsonobject.getString("dateReg"),jsonobject.getString("gender"),
                            jsonobject.getString("phone"),jsonobject.getString("email"),
                            jsonobject.getString("doctype"),jsonobject.getString("contactAdd")
                    );
                    myModels.Doctor noticeList = new myModels().new Doctor(
                            jsonobject.getString("docname"),
                            jsonobject.getString("email"),
                            jsonobject.getString("phone")
                    );

                    allDoctorList.add(noticeList);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            doctorListAdapter = new doctorAdapter( allDoctorList, getContext(), new doctorAdapter.OnItemClickListener() {});
            doctorListAdapter.notifyDataSetChanged();
            doctor.setAdapter(doctorListAdapter);
            super.onPostExecute(s);
        }
    }


    class LoadLocalDoctor extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
                allDoctorList.clear();
                Cursor docs = dbHelper.getAllDoctor();
                if(docs.getCount() > 0){
                    while (docs.moveToNext()) {
                        myModels.Doctor noticeList = new myModels().new Doctor(
                                docs.getString(docs.getColumnIndex(dbColumnList.hospitalDocInfo.COLUMN_DOCNAME)),
                                docs.getString(docs.getColumnIndex(dbColumnList.hospitalDocInfo.COLUMN_EMAIL)),
                                docs.getString(docs.getColumnIndex(dbColumnList.hospitalDocInfo.COLUMN_PHONE))
                        );

                        allDoctorList.add(noticeList);
                    }
                }
               docs.close();

            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            doctorListAdapter = new doctorAdapter( allDoctorList, getContext(), new doctorAdapter.OnItemClickListener() {});
            doctorListAdapter.notifyDataSetChanged();
            doctor.setAdapter(doctorListAdapter);

            super.onPostExecute(s);
        }
    }

//    Loading Doctors Ends

    public void volleyContactRequest(String url){
        String  REQUEST_TAG = "com.volley.volleyContactRequest";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if (response.length()>2){
                            allContactResult = response;
//                           Toast.makeText(getApplicationContext(), response ,Toast.LENGTH_LONG).show();
                            new ReadContactsJSON().execute();
                        }else{
                            new ReadContactsJSON().execute();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new ReadContactsJSON().execute();
                        System.out.println(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("opr", "loadContacts");
                params.put("userID", userID);
                return params;
            }
        };
        AppSingleton.getInstance(getActivity()).addToRequestQueue(postRequest, REQUEST_TAG);
    }



    class ReadContactsJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                allContactList.clear();
                dbHelper.deleteContact();
                JSONArray jsonarray = new JSONArray(allContactResult);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    dbHelper.SaveContactInformation(
                            jsonobject.getString("id"),jsonobject.getString("officeAddress"),
                            jsonobject.getString("contactname"),jsonobject.getString("relationship"),
                            jsonobject.getString("email"),jsonobject.getString("phone")
                    );
                    myModels.Doctor noticeList = new myModels().new Doctor(
                            jsonobject.getString("contactname"),
                            jsonobject.getString("email"),
                            jsonobject.getString("phone")
                    );

                    allContactList.add(noticeList);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            contactListAdapter = new doctorAdapter( allContactList, getContext(), new doctorAdapter.OnItemClickListener() {});
            contactListAdapter.notifyDataSetChanged();
            contact.setAdapter(contactListAdapter);
            super.onPostExecute(s);
        }
    }


    class LoadLocalContact extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            allContactList.clear();
            Cursor docs = dbHelper.getAllContact();
            if(docs.getCount() > 0){
                while (docs.moveToNext()) {
                    myModels.Doctor noticeList = new myModels().new Doctor(
                            docs.getString(docs.getColumnIndex(dbColumnList.contactInfo.COLUMN_CONTACTNAME)),
                            docs.getString(docs.getColumnIndex(dbColumnList.contactInfo.COLUMN_EMAIL)),
                            docs.getString(docs.getColumnIndex(dbColumnList.contactInfo.COLUMN_PHONE))
                    );

                    allContactList.add(noticeList);
                }
            }
            docs.close();

            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            contactListAdapter = new doctorAdapter( allContactList, getContext(), new doctorAdapter.OnItemClickListener() {});
            contactListAdapter.notifyDataSetChanged();
            contact.setAdapter(contactListAdapter);
            super.onPostExecute(s);
        }
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
                    startDate.set(START.getYear()+1900,START.getMonth(),START.getDay());
                    endDate.set(END.getYear()+1900,END.getMonth(),END.getDay());

                    String DATE_FORMATTWO= "EEE, dd MMMM yyyy";
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATTWO);

                    ldp.setText("Conception Date : " + dateFormat.format(startDate.getTime()));
                    dd.setText("Expected Due Date : "+ dateFormat.format(endDate.getTime()));

//                    calendarView2.setMaxDate(endDate.getTimeInMillis());

                    monthsBetweenDates(START);
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


    public void monthsBetweenDates(Date startDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Calendar startCalender = Calendar.getInstance();
        Calendar todayCalender = Calendar.getInstance();
        try {
            startCalender.setTimeInMillis(startDate.getTime());
            Date today = new Date();
            Date todayWithZeroTime = sdf.parse(sdf.format(today));
            todayCalender.setTimeInMillis(today.getTime());

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


        progressBar.setProgress(dayCount);

        if (monthCount<=3){
            trim.setText("First Trimester");
            trimCount = 0;
        }else if (monthCount <=6){
            trim.setText("Second Trimester");
            trimCount = 1;
        }else{
            trim.setText("Third Trimester");
            trimCount = 3;
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
