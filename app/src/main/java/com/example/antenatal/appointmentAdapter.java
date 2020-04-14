package com.example.antenatal;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class appointmentAdapter extends RecyclerView.Adapter<appointmentAdapter.RecyclerHolder>{
    private LayoutInflater inflater;
    private List<myModels.Appointment> contacts;
    private String stat;
    private Context activity;
    private  OnItemClickListener mlistener;
    public appointmentAdapter(List<myModels.Appointment> contacts, Context context, OnItemClickListener listener){
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
        View view = inflater.inflate(R.layout.customappoint,parent,false);
        RecyclerHolder holder= new RecyclerHolder(view,mlistener);
        return holder;
    }
    int prevpos=0;
    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        try {
            int g = holder.getAdapterPosition();
            myModels.Appointment contact = contacts.get(g);
            holder.scheduledate.setText(contact.getDateSchedule() + " - " + contact.getTimeSchedule());

            String purpose = contact.getPurpose().length() > 300 ? TextUtils.substring(contact.getPurpose(),0,300).concat("...") :contact.getPurpose();
            holder.purpose.setText(purpose);
            holder.doctor.setText(contact.getDoctype() + " - " + contact.getDocname());

            if (contact.getValid().equals("0")){
                holder.scheduledate.setTextColor(activity.getResources().getColor(R.color.colorGreen));
            }
            if (position > prevpos) {
                //you are scrooling down
                AnimationUtils.animate(holder, true);
            } else {
                //no you are scroolingup
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
        TextView scheduledate, doctor,purpose;
        CardView card;
        public RecyclerHolder(View itemView,final OnItemClickListener listener) {
            super(itemView);
            scheduledate =  itemView.findViewById(R.id.scheduledate);
            doctor = itemView.findViewById(R.id.appoint_doctor);
            purpose =  itemView.findViewById(R.id.appoint_purpose);
            card = itemView.findViewById(R.id.card);

            card.setOnClickListener(new View.OnClickListener() {
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

    public void setFilter(ArrayList<myModels.Appointment> newList){
        contacts = new ArrayList<>();
        contacts.addAll(newList);
        notifyDataSetChanged();
    }

}
