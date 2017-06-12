package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.activity.MyDoctor;
import com.example.doctor.ui.model.Doctor;

import java.util.Collections;
import java.util.List;

/**
 * Created by tejas on 12/6/17.
 */

public class MyDoctorAdapter extends RecyclerView.Adapter<MyDoctorAdapter.MyViewHolder> {
    private LayoutInflater inflator;
    private static MyDoctorAdapter.MyClickListener myClickListener;
    List<Doctor> data = Collections.emptyList();
    private Context context;

    public MyDoctorAdapter(Context context, List<Doctor> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }


    @Override
    public MyDoctorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.doctor_custom_box,parent,false);
        MyDoctorAdapter.MyViewHolder myViewHolder = new MyDoctorAdapter.MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyDoctorAdapter.MyViewHolder holder, int position) {

        final Doctor current = data.get(position);
        holder.name.setText(current.getName());
        holder.type.setText(current.getType());
        holder.phone.setText(current.getPhone());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setOnItemClickListener(MyDoctorAdapter.MyClickListener myClickListener) {
        MyDoctorAdapter.myClickListener = myClickListener;
    }

    public interface MyClickListener{
        void onItemClick(int position,View v);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView type;
        //TextView address,notes;
        TextView phone;
        private ImageView edit;
        private Button request;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.doctor_name_edit);
            type = (TextView) itemView.findViewById(R.id.speciality_edit);
            phone = (TextView) itemView.findViewById(R.id.phone_edit);
            edit = (ImageView) itemView.findViewById(R.id.editButton);
            request = (Button) itemView.findViewById(R.id.requestButton);

            edit.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(myClickListener!=null){
                myClickListener.onItemClick(getLayoutPosition(),v);
            }
        }
    }
}
