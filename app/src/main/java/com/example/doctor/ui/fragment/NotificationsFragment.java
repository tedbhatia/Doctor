package com.example.doctor.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.doctor.R;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.adapter.NotificationsAdapter;
import com.example.doctor.ui.model.Notifications;
import com.example.doctor.ui.model.SwipeUtil;

import java.util.ArrayList;
import java.util.List;

import static com.example.doctor.ui.activity.MainActivity.navigationView;

/**
 * Created by SHIVIKA NAGPAL on 13-06-2017.
 */

public class NotificationsFragment extends Fragment implements My_Health_Acc_Adapter.MyClickListener{

    private RecyclerView recyclerView;
    private NotificationsAdapter adapter;
    private List<Notifications> data;

    private PullRefreshLayout pullRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_notifications,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);

        pullRefreshLayout=(PullRefreshLayout)rootView.findViewById(R.id.pullLayout);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    Health_Acc_Fragment health_acc_fragment = new Health_Acc_Fragment();

                    navigationView.getMenu().getItem(0).setChecked(true);

                    FragmentTransaction transaction=getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame,health_acc_fragment);

                    transaction.commit();

                    return true;
                }
                return false;
            }
        });


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
        String[] title={"This is a notification","This is a notification","This is a notification","This is a notification","This is a notification"};
        String[] desc={"Doctor Near You","Doctor Near You","Doctor Near You","Doctor Near You","Doctor Near You"};

        for (int i = 0; i < title.length; i++) {
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