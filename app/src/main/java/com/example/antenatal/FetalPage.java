package com.example.antenatal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;



public class FetalPage extends Fragment {
    TextView title, content;
    ImageView img;
    private OnFragmentInteractionListener mListener;

    public FetalPage() {
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

        View view = inflater.inflate(R.layout.fragment_fetal_page, container, false);
        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        img = view.findViewById(R.id.img);

        title.setText(dbColumnList.weekTabs[post] + " Fetal Image.");
        if(post ==0){
            img.setImageResource(R.drawable.week1);
        }else if(post ==1){
            img.setImageResource(R.drawable.week2);
        }else if(post ==2){
            img.setImageResource(R.drawable.week3);
        }else if(post ==3){
            img.setImageResource(R.drawable.week4);
        }else if(post ==4){
            img.setImageResource(R.drawable.week5);
        }else if(post ==5){
            img.setImageResource(R.drawable.week6);
        }else if(post ==6){
            img.setImageResource(R.drawable.week7);
        }else if(post ==7){
            img.setImageResource(R.drawable.week8);
        }else if(post ==8){
            img.setImageResource(R.drawable.week9);
        }else if(post ==9){
            img.setImageResource(R.drawable.week10);
        }else if(post ==10){
            img.setImageResource(R.drawable.week11);
        }else if(post ==11){
            img.setImageResource(R.drawable.week12);
        }else if(post ==12){
            img.setImageResource(R.drawable.week13);
        }else if(post ==13){
            img.setImageResource(R.drawable.week14);
        }else if(post ==14){
            img.setImageResource(R.drawable.week15);
        }else if(post ==15){
            img.setImageResource(R.drawable.week16);
        }else if(post ==16){
            img.setImageResource(R.drawable.week17);
        }else if(post ==17){
            img.setImageResource(R.drawable.week18);
        }else if(post ==18){
            img.setImageResource(R.drawable.week19);
        }else if(post ==19){
            img.setImageResource(R.drawable.week20);
        }else if(post ==20){
            img.setImageResource(R.drawable.week21);
        }else if(post ==21){
            img.setImageResource(R.drawable.week22);
        }else if(post ==22){
            img.setImageResource(R.drawable.week23);
        }else if(post ==23){
            img.setImageResource(R.drawable.week24);
        }else if(post ==24){
            img.setImageResource(R.drawable.week25);
        }else if(post ==25){
            img.setImageResource(R.drawable.week26);
        }else if(post ==26){
            img.setImageResource(R.drawable.week27);
        }else if(post ==27){
            img.setImageResource(R.drawable.week28);
        }else if(post ==28){
            img.setImageResource(R.drawable.week29);
        }else if(post ==29){
            img.setImageResource(R.drawable.week30);
        }else if(post ==30){
            img.setImageResource(R.drawable.week31);
        }else if(post ==31){
            img.setImageResource(R.drawable.week32);
        }else if(post ==32){
            img.setImageResource(R.drawable.week33);
        }else if(post ==33){
            img.setImageResource(R.drawable.week34);
        }else if(post ==34){
            img.setImageResource(R.drawable.week35);
        }else if(post ==35){
            img.setImageResource(R.drawable.week36);
        }else if(post ==36){
            img.setImageResource(R.drawable.week37);
        }else if(post ==37){
            img.setImageResource(R.drawable.week38);
        }else if(post ==38){
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
