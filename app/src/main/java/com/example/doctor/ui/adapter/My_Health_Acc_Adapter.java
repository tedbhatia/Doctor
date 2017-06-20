package com.example.doctor.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.activity.MainActivity;
import com.example.doctor.ui.activity.My_Insurance;
import com.example.doctor.ui.activity.My_Measurements;
import com.example.doctor.ui.model.My_Health_Acc_Info;

import java.util.Collections;
import java.util.List;

import static com.example.doctor.ui.activity.LoginScreen.loggedIn;

/**
 * Created by tejas on 1/6/17.
 */

public class My_Health_Acc_Adapter extends RecyclerView.Adapter<My_Health_Acc_Adapter.MyViewHolder> {

    private static MyClickListener myClickListener;
    private LayoutInflater inflator;
    List<My_Health_Acc_Info> data = Collections.emptyList();
    private Context context;
    private float aFloat = (float) 0.5;

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
        if(!loggedIn) {
            holder.layout.setAlpha(aFloat);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        My_Health_Acc_Adapter.myClickListener = myClickListener;
    }

    public interface MyClickListener{
        void onItemClick(int position,View v);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView icon;
        CardView layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.BoxText);
            icon = (ImageView) itemView.findViewById(R.id.BoxIcon);
            layout = (CardView) itemView.findViewById(R.id.card_view1);

            layout.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(myClickListener!=null){
                myClickListener.onItemClick(getLayoutPosition(),v);
            }
        }
    }
}