package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.Appointments;

import java.util.List;

/**
 * Created by SHIVIKA NAGPAL on 05-06-2017.
 */

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder>{

    private static My_Health_Acc_Adapter.MyClickListener myClickListener;
    private List<Appointments> grid;
    private Context context;

    public AppointmentsAdapter(Context context, List<Appointments> grid) {

        this.context = context;
        this.grid = grid;

    }

    public void setOnItemClickListener(My_Health_Acc_Adapter.MyClickListener myClickListener) {

        this.myClickListener = myClickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.appointments_custom_box,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Appointments listItem=grid.get(position);

        holder.name.setText(listItem.getName());
        holder.date.setText(listItem.getDate());
        holder.time.setText(listItem.getTime());
        holder.reason.setText(listItem.getReason());
        holder.notes.setText(listItem.getNotes());

    }

    @Override
    public int getItemCount() {

        return grid.size();

    }

    public interface MyClickListener{

        void onItemClick(int position, View v);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name,date,time,reason,notes;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {

            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name);
            date=(TextView)itemView.findViewById(R.id.date);
            time=(TextView)itemView.findViewById(R.id.time);
            reason=(TextView)itemView.findViewById(R.id.reason);
            notes=(TextView)itemView.findViewById(R.id.notes);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.AppointmentsLayout);

            linearLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(myClickListener!=null){

                myClickListener.onItemClick(getLayoutPosition(),v);

            }

        }

    }

}
