package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.Measurement_Info;

import java.util.Collections;
import java.util.List;

/**
 * Created by tejas on 2/6/17.
 */

public class Measurement_Adapter extends RecyclerView.Adapter<Measurement_Adapter.MyViewHolder> {

    private LayoutInflater inflator;
    private static MyClickListener myClickListener;
    List<Measurement_Info> data = Collections.emptyList();
    private Context context;

    public Measurement_Adapter(Context context, List<Measurement_Info> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    public void setOnItemClickListener(Measurement_Adapter.MyClickListener myClickListener) {
        Measurement_Adapter.myClickListener = myClickListener;
    }

    @Override
    public Measurement_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.measurement_custom_box,parent,false);
        Measurement_Adapter.MyViewHolder myViewHolder = new Measurement_Adapter.MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(Measurement_Adapter.MyViewHolder holder, int position) {

        final Measurement_Info current = data.get(position);
        holder.type.setText(current.getType());
        holder.date.setText(current.getDate());
        holder.note.setText(current.getNote());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface MyClickListener{
        void onItemClick(int position,View v);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView type;
        TextView note;
        TextView date;
        private CardView linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            type = (TextView) itemView.findViewById(R.id.BoxTypeText);
            date = (TextView) itemView.findViewById(R.id.BoxDateText);
            note = (TextView) itemView.findViewById(R.id.BoxNoteText);

            linearLayout = (CardView) itemView.findViewById(R.id.card_view2);

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
