package com.example.antenatal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class DailyPage extends Fragment {
    TextView title, content;
    ImageView img;
    private OnFragmentInteractionListener mListener;

    public DailyPage() {
        // Required empty public constructor
    }

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        DailyPage tabFragment = new DailyPage();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }
    int post,dailyWork;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        post = getArguments().getInt("pos");
//        System.out.println("OnCREATE - " + post);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_daily_page, container, false);
        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        img = view.findViewById(R.id.img);
//        System.out.println("before - " + post + " ---- "+  dbColumnList.TabPostion + " ---- "+  dbColumnList.TabText);
//        post = dbColumnList.TabPostion + 1;

//        post = Arrays.asList(dbColumnList.tabs).indexOf(dbColumnList.TabText);

        title.setText(dbColumnList.tabs[post] + " Of Pregnancy.");
        content.setText(Html.fromHtml((dbColumnList.dailyTips.get(post).getTipsDescription())));

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

        return view;
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
