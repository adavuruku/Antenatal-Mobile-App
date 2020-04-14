package com.example.antenatal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Appointment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public Appointment() {
        // Required empty public constructor
    }

    private dbHelper dbHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private List<myModels.Appointment> allNoticeAppointment;
    private static int SPLASH_TIME_OUT = 500;//5seconds
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    private appointmentAdapter recyclerAdapter;
    String search, userID, allResult;
    ProgressBar progressBar;
    SharedPreferences MyId;
    ArrayList<myModels.Appointment> noticeList;
    //    String address = "https://antenantal.000webhostapp.com/antenatalrest.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_appointment, container, false);

        recyclerView = rootView.findViewById(R.id.appointment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        progressBar =  rootView.findViewById(R.id.simpleProgressBar);

        MyId = getActivity().getSharedPreferences("MyId", getActivity().MODE_PRIVATE);
        userID = MyId.getString("MyId", "");
        allNoticeAppointment = new ArrayList<>();

        mSwipeRefreshLayout = rootView.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                volleyAppointmentRequest(dbColumnList.address);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                volleyAppointmentRequest(dbColumnList.address);
            }
        },SPLASH_TIME_OUT);

        return rootView;
    }


    public void volleyAppointmentRequest(String url){
        String  REQUEST_TAG = "com.volley.volleyAppointmentRequest";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if (response.length()<=2){
                            new LoadLocalData().execute();
                        }else{
                            allResult = response;
                            System.out.println(allResult);
                            new ReadJSON().execute();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        new LoadLocalData().execute();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("opr", "loadAppointments");
                params.put("userID", userID);
                return params;
            }
        };
        AppSingleton.getInstance(getContext()).addToRequestQueue(postRequest, REQUEST_TAG);
    }


    class ReadJSON extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                dbHelper = new dbHelper(getContext());
                dbHelper.deleteSchedule();
                allNoticeAppointment.clear();
                JSONArray jsonarray = new JSONArray(allResult);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    dbHelper.saveSchedule(
                            jsonobject.getString("recid"), jsonobject.getString("dateSchedule"),
                            jsonobject.getString("timeSchedule"), jsonobject.getString("docname"),
                            jsonobject.getString("doctype"),jsonobject.getString("valid"),
                            jsonobject.getString("outcome"),jsonobject.getString("purpose"),
                            jsonobject.getString("dateScheduleReg")
                    );

                    myModels.Appointment noticeList = new myModels().new Appointment(
                            jsonobject.getString("dateSchedule"),
                            jsonobject.getString("valid"),
                            jsonobject.getString("timeSchedule"),
                            jsonobject.getString("doctype"),
                            jsonobject.getString("docname"),
                            jsonobject.getString("purpose"),
                            jsonobject.getString("outcome"),
                            jsonobject.getString("recid")
                    );

                    System.out.println(noticeList);

                    allNoticeAppointment.add(noticeList);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            loadData();
            super.onPostExecute(s);
        }
    }


    class LoadLocalData extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                dbHelper = new dbHelper(getContext());
                allNoticeAppointment.clear();
                Cursor allnews = dbHelper.getAllSchedule();
                if (allnews.getCount() > 0) {
                    while (allnews.moveToNext()) {
                        myModels.Appointment noticeList = new myModels().new Appointment(
                                allnews.getString(allnews.getColumnIndex(dbColumnList.userSchedule.COLUMN_SCHEDULEDATE)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.userSchedule.COLUMN_SCHEDULESTATUS)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.userSchedule.COLUMN_SCHEDULETIME)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.userSchedule.COLUMN_DOCTYPE)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.userSchedule.COLUMN_SCHEDULEDOCTOR)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.userSchedule.COLUMN_PURPOSE)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.userSchedule.COLUMN_SCHEDULEOUTCOME)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.userSchedule.COLUMN_HID))
                        );

                        allNoticeAppointment.add(noticeList);
                    }
                }
                allnews.close();
            } finally { }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            loadData();
            super.onPostExecute(s);
        }
    }

    public void loadData(){
        recyclerAdapter = new appointmentAdapter( allNoticeAppointment, getContext(), new appointmentAdapter.OnItemClickListener() {
            @Override
            public void onNameClick(View v, int position) {
                String postid =  allNoticeAppointment.get(position).getRecid();
//
//            Toast.makeText(getContext(),"Welcome "+ studentName + " To ABUAD IT - MOBILE APP",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), readAppointment.class);
                intent.putExtra("NEWSID",postid);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerAdapter);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.GONE);
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