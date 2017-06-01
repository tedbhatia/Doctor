package com.example.doctor.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.activity.MainActivity;
import com.example.doctor.ui.activity.My_Insurance;
import com.example.doctor.ui.activity.My_Measurements;
import com.example.doctor.ui.model.My_Health_Acc_Info;

import java.util.Collections;
import java.util.List;

/**
 * Created by tejas on 1/6/17.
 */

public class My_Health_Acc_Adapter extends RecyclerView.Adapter<My_Health_Acc_Adapter.MyViewHolder> {

    private LayoutInflater inflator;
    List<My_Health_Acc_Info> data = Collections.emptyList();
    private Context context;

    public My_Health_Acc_Adapter(Context context, List<My_Health_Acc_Info> data){
        inflator = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.custom_box, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final My_Health_Acc_Info current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current.getTitle().equals("My Doctor")){
                    Toast.makeText(context,"My Doctor",Toast.LENGTH_SHORT).show();
                }
                else if(current.getTitle().equals("My Diseases")){
                    Toast.makeText(context,"My Diseases",Toast.LENGTH_SHORT).show();
                }
                else if(current.getTitle().equals("My Appointments")){
                    Toast.makeText(context,"My Appointments",Toast.LENGTH_SHORT).show();
                }
                else if(current.getTitle().equals("My Medicines")){
                    Toast.makeText(context,"My Medicines",Toast.LENGTH_SHORT).show();
                }
                else if(current.getTitle().equals("My Documents")){
                    Toast.makeText(context,"My Documents",Toast.LENGTH_SHORT).show();
                }
                else if(current.getTitle().equals("My Insurance")){
                    Toast.makeText(context,"My Insurance",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,My_Insurance.class);
                    context.startActivity(intent);
                }
                else if(current.getTitle().equals("My Measurements")){
                    Toast.makeText(context,"My Measurements",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,My_Measurements.class);
                    context.startActivity(intent);
                }
            }
        });
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current.getTitle().equals("My Doctors")){
                    Toast.makeText(context,"My Doctors",Toast.LENGTH_SHORT).show();
                }
                else if(current.getTitle().equals("My Diseases")){
                    Toast.makeText(context,"My Diseases",Toast.LENGTH_SHORT).show();
                }
                else if(current.getTitle().equals("My Appointments")){
                    Toast.makeText(context,"My Appointments",Toast.LENGTH_SHORT).show();
                }
                else if(current.getTitle().equals("My Medicines")){
                    Toast.makeText(context,"My Medicines",Toast.LENGTH_SHORT).show();
                }
                else if(current.getTitle().equals("My Documents")){
                    Toast.makeText(context,"My Documents",Toast.LENGTH_SHORT).show();
                }
                else if(current.getTitle().equals("My Insurance")){
                    Toast.makeText(context,"My Insurance",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,My_Insurance.class);
                    context.startActivity(intent);
                }
                else if(current.getTitle().equals("My Measurements")){
                    Toast.makeText(context,"My Measurements",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context,My_Measurements.class);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.BoxText);
            icon = (ImageView) itemView.findViewById(R.id.BoxIcon);
        }


        @Override
        public void onClick(View v) {

        }
    }
}
