package com.example.antenatal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class doctorAdapter extends RecyclerView.Adapter<doctorAdapter.RecyclerHolder>{
    private LayoutInflater inflater;
    private List<myModels.Doctor> contacts;
    private String stat;
    private Context activity;
    private  OnItemClickListener mlistener;
    public doctorAdapter(List<myModels.Doctor> contacts, Context context, OnItemClickListener listener){
        this.activity = context;
        this.inflater = LayoutInflater.from(context);
        this.mlistener = listener;
        this.contacts = contacts;
    }
    public interface OnItemClickListener{
    }
    public void setOnitemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.customappoint,parent,false);
        RecyclerHolder holder= new RecyclerHolder(view,mlistener);
        return holder;
    }
    int prevpos=0;
    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        try {
            int g = holder.getAdapterPosition();
            myModels.Doctor contact = contacts.get(g);
            holder.docname.setText(contact.getDocName());
            holder.email.setText(contact.getDocEmail());
            holder.phone.setText(contact.getDocPhone());

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
        TextView docname, phone,email;
        public RecyclerHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);
            docname =  itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email =  itemView.findViewById(R.id.email);

        }
    }

    public void setFilter(ArrayList<myModels.Doctor> newList){
        contacts = new ArrayList<>();
        contacts.addAll(newList);
        notifyDataSetChanged();
    }

}
