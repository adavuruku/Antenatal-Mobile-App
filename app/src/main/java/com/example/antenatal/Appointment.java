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
    private List<myModels.notice> allNoticeList;
    private static int SPLASH_TIME_OUT = 500;//5seconds
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    private noticeAdapter recyclerAdapter;
    String search, userID, allResult;
    ProgressBar progressBar;
    SharedPreferences MyId;
    ArrayList<myModels.Appointment> noticeList;
    //    String address = "https://antenantal.000webhostapp.com/antenatalrest.php";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_appointment, container, false);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        progressBar =  rootView.findViewById(R.id.simpleProgressBar);

        MyId = getActivity().getSharedPreferences("MyId", getActivity().MODE_PRIVATE);
        userID = MyId.getString("MyId", "");
        allNoticeList = new ArrayList<>();

        mSwipeRefreshLayout = rootView.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                volleyJsonArrayRequest(dbColumnList.address);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                volleyJsonArrayRequest(dbColumnList.address);
            }
        },SPLASH_TIME_OUT);

        return rootView;
    }


    public void volleyJsonArrayRequest(String url){
        String  REQUEST_TAG = "com.volley.volleyJsonArrayRequest";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        if (response.length()<=2){
                            new LoadLocalData().execute();
                        }else{
                            allResult = response;
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
                allNoticeList.clear();
                JSONArray jsonarray = new JSONArray(allResult);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    dbHelper.saveNotice(
                            jsonobject.getString("noticeid"), jsonobject.getString("NoticeDescription"),
                            jsonobject.getString("author"), jsonobject.getString("title"),
                            jsonobject.getString("noticeDate"), jsonobject.getString("delStatus")
                    );


//                    {
//                        "id":1,
//                            "HID":"ABUTH42639249",
//                            "dateSchedule":"2020-04-30 00:00:00",
//                            "purpose":"Appoi",
//                            "byId":"ABUTH188087",
//                            "valid":1,
//                            "timeSchedule":"23:23:00",
//                            "docid":"ABUTH188087",
//                            "outcome":"Done",
//                            "dateReg":"2020-02-19 10:17:59",
//                            "docId":"ABUTH188087",
//                            "doctype":"Doctor",
//                            "docname":"Abdulraheem Sherif Adavuruku",
//                            "phone":"08164377187",
//                            "email":"aabdulraheemsherif@gmail.com",
//                            "contactAdd":"D41 Inike Okene Kogi State",
//                            "active":0,
//                            "gender":"Male",
//                            "recid":8
//                    },

                    myModels.Appointment noticeList = new myModels().new Appointment(
                            jsonobject.getString("NoticeDescription"),
                            jsonobject.getString("title"),
                            jsonobject.getString("author"),
                            jsonobject.getString("noticeDate"),
                            jsonobject.getString("noticeid")
                    );

                    allNoticeList.add(noticeList);
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
                allNoticeList.clear();
                Cursor allnews = dbHelper.getAllNotice();
                if (allnews.getCount() > 0) {
                    while (allnews.moveToNext()) {

                        myModels.notice noticeList = new myModels().new notice(
                                allnews.getString(allnews.getColumnIndex(dbColumnList.abuadNotice.COLUMN_DESCRIPTION)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.abuadNotice.COLUMN_TITLE)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.abuadNotice.COLUMN_AUTHOR)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.abuadNotice.COLUMN_NOTICEDATE)),
                                allnews.getString(allnews.getColumnIndex(dbColumnList.abuadNotice.COLUMN_ID))
                        );

                        allNoticeList.add(noticeList);
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
        recyclerAdapter = new noticeAdapter( allNoticeList, getContext(), new noticeAdapter.OnItemClickListener() {
            @Override
            public void onNameClick(View v, int position) {
                String postid =  allNoticeList.get(position).getNoticeID();

//            Toast.makeText(getContext(),"Welcome "+ studentName + " To ABUAD IT - MOBILE APP",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), readNotice.class);
                intent.putExtra("NEWSID",postid);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                //getActivity().finish();
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