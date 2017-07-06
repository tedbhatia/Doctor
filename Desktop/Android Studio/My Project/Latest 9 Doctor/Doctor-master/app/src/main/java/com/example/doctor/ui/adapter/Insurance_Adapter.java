package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.Insurance;
import com.example.doctor.ui.model.My_Health_Acc_Info;

import java.util.Collections;
import java.util.List;

import static android.R.attr.data;

/**
 * Created by tejas on 2/6/17.
 */

public class Insurance_Adapter extends RecyclerView.Adapter<Insurance_Adapter.MyViewHolder> {

    private LayoutInflater inflator;
    private static MyClickListener myClickListener;
    List<Insurance> data = Collections.emptyList();
    private Context context;

    public Insurance_Adapter(Context context, List<Insurance> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.insurance_custom_box,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Insurance current = data.get(position);
        holder.plan.setText(current.getInsurance_plan());
        holder.duration.setText(String.valueOf(current.getPremium()));
        holder.notes.setText(current.getNotes());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setOnItemClickListener(MyClickListener myClickListener) {
        Insurance_Adapter.myClickListener = myClickListener;
    }

    public interface MyClickListener{
        void onItemClick(int position,View v);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView plan;
        TextView notes;
        TextView duration;
        private CardView linearLayout;
        private ImageView edit;

        public MyViewHolder(View itemView) {
            super(itemView);
            plan = (TextView) itemView.findViewById(R.id.plan_edit);
            duration = (TextView) itemView.findViewById(R.id.duration_edit);
            notes = (TextView) itemView.findViewById(R.id.notes_edit);
            linearLayout = (CardView) itemView.findViewById(R.id.recycler_view);
            edit = (ImageView) itemView.findViewById(R.id.editButton);

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
