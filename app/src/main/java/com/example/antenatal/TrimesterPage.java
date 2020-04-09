package com.example.antenatal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;


public class TrimesterPage extends Fragment {
    TextView title, content;
    ImageView img;
    List<myModels.trimesterModel> trimesterType;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    private trimesterAdapter recyclerAdapter;
    private OnFragmentInteractionListener mListener;

    public TrimesterPage() {
        // Required empty public constructor
    }

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TrimesterPage tabFragment = new TrimesterPage();
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

        View view = inflater.inflate(R.layout.fragment_trimester_page, container, false);
        trimesterType = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        switch (post){
            case 0:
                trimesterType = dbColumnList.trimeseterOne;
                break;
            case 1:
                trimesterType = dbColumnList.trimeseterTwo;
                break;
            case 2:
                trimesterType = dbColumnList.trimeseterThree;
                break;
        }

        loadData();
        return view;
    }

    public void loadData(){
        recyclerAdapter = new trimesterAdapter(trimesterType, getContext(), new trimesterAdapter.OnItemClickListener() {
            @Override
            public void onNameClick(View v, int position) {
                String trimType =  trimesterType.get(position).getTrimmester();
                String trimDescription =  trimesterType.get(position).getFullContent();
                String trimTitle =  trimesterType.get(position).getTitle();

//                Toast.makeText(getContext(),"Welcome "+ postid + " To ABUAD IT - MOBILE APP",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), ReadTips.class);
                intent.putExtra("trimDescription",trimDescription);
                intent.putExtra("trimTitle",trimTitle);
                intent.putExtra("trimType",trimType);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                //getActivity().finish();
            }
        });
        recyclerAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(recyclerAdapter);
    }


    // TODO: Rename method, update argument and hook method into UI event
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
