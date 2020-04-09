package com.example.antenatal;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class trimesterAdapter extends RecyclerView.Adapter<trimesterAdapter.RecyclerHolder>{
    private LayoutInflater inflater;
    private List<myModels.trimesterModel> contacts;
    private String stat;
    private Context activity;
    private  OnItemClickListener mlistener;
    public trimesterAdapter(List<myModels.trimesterModel> contacts, Context context, OnItemClickListener listener){
        this.activity = context;
        this.inflater = LayoutInflater.from(context);
        this.mlistener = listener;
        this.contacts = contacts;
    }
    public interface OnItemClickListener{
        void onNameClick(View v, int position);
    }
    public void setOnitemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.customtrimester,parent,false);
        RecyclerHolder holder= new RecyclerHolder(view,mlistener);
        return holder;
    }
    int prevpos=0;
    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        try {
            int g = holder.getAdapterPosition();
            myModels.trimesterModel contact = contacts.get(g);
            holder.tipsTitle.setText(contact.getTitle());
            if (position > prevpos) {
                AnimationUtils.animate(holder, true);
            } else {
                AnimationUtils.animate(holder, false);
            }
            prevpos = position;
        }catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    //create the holder class
    class RecyclerHolder extends RecyclerView.ViewHolder{
        //the view items send here is from custom_row and is received here as itemView
        TextView tipsTitle;
        public RecyclerHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);

            tipsTitle =  itemView.findViewById(R.id.tipsTitle);
            tipsTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onNameClick(view, position);
                        }
                    }
                }
            });

        }
    }

    public void setFilter(ArrayList<myModels.trimesterModel> newList){
        contacts = new ArrayList<>();
        contacts.addAll(newList);
        notifyDataSetChanged();
    }
}

