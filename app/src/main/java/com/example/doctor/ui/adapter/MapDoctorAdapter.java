package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.Doctor;

import java.util.Collections;
import java.util.List;

import static com.example.doctor.ui.activity.LoginScreen.loggedIn;

/**
 * Created by tejas on 12/6/17.
 */

public class MapDoctorAdapter extends RecyclerView.Adapter<MapDoctorAdapter.MyViewHolder> {
    private LayoutInflater inflator;
    private static MapDoctorAdapter.MyClickListener myClickListener;
    List<MapsDoctorModel> data = Collections.emptyList();
    private Context context;

    public MapDoctorAdapter(Context context, List<MapsDoctorModel> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }


    @Override
    public MapDoctorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.map_doctor_custom_box,parent,false);
        MapDoctorAdapter.MyViewHolder myViewHolder = new MapDoctorAdapter.MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MapDoctorAdapter.MyViewHolder holder, int position) {

        final MapsDoctorModel current = data.get(position);
        holder.name.setText(current.getName());
        holder.type.setText(current.getAddress());
        if(!loggedIn){
            holder.add.setText("CALL");
        }
        /*holder.phone.setText(current.getPhone());*/
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setOnItemClickListener(MapDoctorAdapter.MyClickListener myClickListener) {
        MapDoctorAdapter.myClickListener = myClickListener;
    }

    public interface MyClickListener{
        void onItemClick(int position, View v);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        TextView type;
        Button add;
        //TextView address,notes;
        /*TextView phone;
        private ImageView edit;
        private Button request;
        LinearLayout viewDetails;*/

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.doctor_name_edit);
            type = (TextView) itemView.findViewById(R.id.speciality_edit);
            add = (Button) itemView.findViewById(R.id.requestButton);
            /*phone = (TextView) itemView.findViewById(R.id.phone_edit);
            edit = (ImageView) itemView.findViewById(R.id.editButton);
            request = (Button) itemView.findViewById(R.id.requestButton);
            viewDetails = (LinearLayout) itemView.findViewById(R.id.viewDetails);

            edit.setOnClickListener(this);
            request.setOnClickListener(this);
            viewDetails.setOnClickListener(this);*/
        }


        @Override
        public void onClick(View v) {
            if(myClickListener!=null){
                myClickListener.onItemClick(getLayoutPosition(),v);
            }
        }
    }
}