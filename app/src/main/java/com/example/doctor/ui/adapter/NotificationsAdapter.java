package com.example.doctor.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.Notifications;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SHIVIKA NAGPAL on 13-06-2017.
 */

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.MyViewHolder>{

    private static My_Health_Acc_Adapter.MyClickListener myClickListener;
    private LayoutInflater inflator;

    List<Notifications> listItems = Collections.emptyList();
    private Context context;

    private List<Notifications> itemsPendingRemoval;

    private static final int PENDING_REMOVAL_TIMEOUT = 3000; // 3sec
    private Handler handler = new Handler(); // hanlder for running delayed runnables
    HashMap<Notifications, Runnable> pendingRunnables = new HashMap<>(); // map of items to pending runnables, so we can cancel a removal if need be

//    public NotificationsAdapter(Context context, List<Notifications> listItems){
//        inflator = LayoutInflater.from(context);
//        this.listItems = listItems;
//        this.context = context;
//    }

    public NotificationsAdapter(List<Notifications> listItems) {
        this.listItems = listItems;
        itemsPendingRemoval = new ArrayList<>();
    }



    @Override
    public NotificationsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_box_notifications, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final NotificationsAdapter.MyViewHolder holder, int position) {

        final Notifications current = listItems.get(position);

        if(itemsPendingRemoval.contains(current)){

            holder.regularLayout.setVisibility(View.GONE);
            holder.swipeLayout.setVisibility(View.VISIBLE);
            holder.undo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    undoOpt(current);
                }
            });

        }else{

            holder.regularLayout.setVisibility(View.VISIBLE);
            holder.swipeLayout.setVisibility(View.GONE);
            holder.title.setText(current.getTitle());
            holder.description.setText(current.getDescription());

        }

    }

    private void undoOpt(Notifications customer) {
        Runnable pendingRemovalRunnable = pendingRunnables.get(customer);
        pendingRunnables.remove(customer);
        if (pendingRemovalRunnable != null)
            handler.removeCallbacks(pendingRemovalRunnable);
        itemsPendingRemoval.remove(customer);
        // this will rebind the row in "normal" state
        notifyItemChanged(listItems.indexOf(customer));
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void pendingRemoval(int position) {

        final Notifications notifications = listItems.get(position);
        if (!itemsPendingRemoval.contains(notifications)) {
            itemsPendingRemoval.add(notifications);
            // this will redraw row in "undo" state
            notifyItemChanged(position);
            // let's create, store and post a runnable to remove the data
            Runnable pendingRemovalRunnable = new Runnable() {
                @Override
                public void run() {
                    remove(listItems.indexOf(notifications));
                }
            };
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT);
            pendingRunnables.put(notifications, pendingRemovalRunnable);
        }
    }

    public void remove(int position) {
        Notifications notifications = listItems.get(position);
        if (itemsPendingRemoval.contains(notifications)) {
            itemsPendingRemoval.remove(notifications);
        }
        if (listItems.contains(notifications)) {
            listItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public boolean isPendingRemoval(int position) {
        Notifications notifications = listItems.get(position);
        return itemsPendingRemoval.contains(notifications);
    }

    public void setOnItemClickListener(My_Health_Acc_Adapter.MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title,description;
        TextView undo;
        LinearLayout regularLayout, swipeLayout;

        public MyViewHolder(View itemView) {

            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);

            regularLayout=(LinearLayout)itemView.findViewById(R.id.regularLayout);
            swipeLayout=(LinearLayout)itemView.findViewById(R.id.swipeLayout);
            undo=(TextView)itemView.findViewById(R.id.undo);

        }


        @Override
        public void onClick(View v) {
            if(myClickListener!=null){
                myClickListener.onItemClick(getLayoutPosition(),v);
            }
        }
    }

}