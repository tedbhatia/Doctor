package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.Notifications;

import java.util.Collections;
import java.util.List;

/**
 * Created by SHIVIKA NAGPAL on 13-06-2017.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.MyViewHolder>{

    private static My_Health_Acc_Adapter.MyClickListener myClickListener;
    private LayoutInflater inflator;

    List<Notifications> listItems = Collections.emptyList();
    private Context context;

    public NotificationsAdapter(Context context, List<Notifications> listItems){
        inflator = LayoutInflater.from(context);
        this.listItems = listItems;
        this.context = context;
    }


    @Override
    public NotificationsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.custom_box_notifications, parent, false);
        NotificationsAdapter.MyViewHolder myViewHolder = new NotificationsAdapter.MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final NotificationsAdapter.MyViewHolder holder, int position) {

        final Notifications current = listItems.get(position);
        holder.title.setText(current.getTitle());
        holder.description.setText(current.getDescription());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void setOnItemClickListener(My_Health_Acc_Adapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title,description;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
        }


        @Override
        public void onClick(View v) {
            if(myClickListener!=null){
                myClickListener.onItemClick(getLayoutPosition(),v);
            }
        }
    }

}
