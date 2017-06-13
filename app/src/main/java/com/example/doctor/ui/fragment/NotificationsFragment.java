package com.example.doctor.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.adapter.NotificationsAdapter;
import com.example.doctor.ui.model.Notifications;
import com.example.doctor.ui.model.SwipeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHIVIKA NAGPAL on 13-06-2017.
 */

public class NotificationsFragment extends Fragment implements My_Health_Acc_Adapter.MyClickListener{

    private RecyclerView recyclerView;
    private NotificationsAdapter adapter;
    private List<Notifications> data;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_health_acc,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
        return rootView;

    }


    @Override
    public void onResume() {
        super.onResume();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        NotificationsAdapter adapter = new NotificationsAdapter(getData());
        recyclerView.setAdapter(adapter);

        setSwipeForRecyclerView();
    }

    private List<Notifications> getData() {
        List<Notifications> modelList = new ArrayList<>();
        String[] title={"title 1","title 2","title 3","title 4","title 5","title 6","title 7"};
        String[] desc={"qwerty 1","qwerty 2","qwerty 3","qwerty 4",""};

        for (int i = 0; i < 3; i++) {
            modelList.add(new Notifications(title[i],desc[i]));
        }
        return modelList;
    }

    private void setSwipeForRecyclerView() {

        SwipeUtil swipeHelper = new SwipeUtil(0, ItemTouchHelper.LEFT, getActivity()) {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedPosition = viewHolder.getAdapterPosition();
                NotificationsAdapter adapter = (NotificationsAdapter) recyclerView.getAdapter();
                adapter.pendingRemoval(swipedPosition);
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                NotificationsAdapter adapter = (NotificationsAdapter) recyclerView.getAdapter();
                if (adapter.isPendingRemoval(position)) {
                    return 0;
                }
                return super.getSwipeDirs(recyclerView, viewHolder);
            }
        };

        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(swipeHelper);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        //set swipe label
        swipeHelper.setLeftSwipeLable("Dismiss");
        //set swipe background-Color
        swipeHelper.setLeftcolorCode(ContextCompat.getColor(getActivity(), R.color.swipebg));

    }


    @Override
    public void onItemClick(int position, View v) {

        Toast.makeText(getActivity(),"Notification "+(position+1)+" pressed.",Toast.LENGTH_SHORT).show();

    }
}