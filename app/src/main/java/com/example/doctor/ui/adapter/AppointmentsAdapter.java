package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.AppointmentSuper;
import com.example.doctor.ui.model.Appointments;

import java.util.List;

/**
 * Created by SHIVIKA NAGPAL on 05-06-2017.
 */

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder>{

    private static My_Health_Acc_Adapter.MyClickListener myClickListener;
    private List<AppointmentSuper> grid;
    private Context context;

    public AppointmentsAdapter(Context context, List<AppointmentSuper> grid) {

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

        AppointmentSuper listItem=grid.get(position);

        holder.name.setText(listItem.getDocsName());
        holder.date.setText(listItem.getDate());
        holder.time.setText(listItem.getTime());
        holder.reason.setText(listItem.getReason());
        holder.notes.setText(listItem.getNotes());

    }

    @Override
    public int getItemCount() {

        return grid.size();

    }

    public void removeItem(int pos){
        grid.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos,grid.size());
    }

    public interface MyClickListener{

        void onItemClick(int position, View v);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name,date,time,reason,notes;
        //private LinearLayout linearLayout;
        private ImageButton edit;

        public ViewHolder(View itemView) {

            super(itemView);

            name=(TextView)itemView.findViewById(R.id.name_edit);
            date=(TextView)itemView.findViewById(R.id.date_edit);
            time=(TextView)itemView.findViewById(R.id.time_edit);
            reason=(TextView)itemView.findViewById(R.id.reason_edit);
            notes=(TextView)itemView.findViewById(R.id.notes_edit);
            //linearLayout=(LinearLayout)itemView.findViewById(R.id.rowFG);
            edit=(ImageButton) itemView.findViewById(R.id.editButton);

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
