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


public class MonthlyPage extends Fragment {
    TextView title, content;
    ImageView img;
    private OnFragmentInteractionListener mListener;

    public MonthlyPage() {
        // Required empty public constructor
    }

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        MonthlyPage tabFragment = new MonthlyPage();
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

        View view = inflater.inflate(R.layout.fragment_monthly_page, container, false);
        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        img = view.findViewById(R.id.img);

        title.setText(dbColumnList.monthTabs[post] + " Of Pregnancy.");
        content.setText(Html.fromHtml((dbColumnList.monthlyTips.get(post).getTipsDescription())));
        if(post ==0){
            img.setImageResource(R.drawable.month1);
        }else if(post ==1){
            img.setImageResource(R.drawable.month2);
        }else if(post ==2){
            img.setImageResource(R.drawable.month3);
        }else if(post ==3){
            img.setImageResource(R.drawable.month4);
        }else if(post ==4){
            img.setImageResource(R.drawable.month5);
        }else if(post ==5){
            img.setImageResource(R.drawable.month6);
        }else if(post ==6){
            img.setImageResource(R.drawable.month7);
        }else if(post ==7){
            img.setImageResource(R.drawable.month8);
        }else if(post ==8){
            img.setImageResource(R.drawable.month9);
        }else {
            img.setImageResource(R.drawable.month10);
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
