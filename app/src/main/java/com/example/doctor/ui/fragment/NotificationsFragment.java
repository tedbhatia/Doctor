package com.example.doctor.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.adapter.NotificationsAdapter;
import com.example.doctor.ui.model.Notifications;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

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
        data = new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        getData(data);
        adapter = new NotificationsAdapter(getActivity(),data);

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        return rootView;
    }



    public static void getData(List<Notifications> data){

        String[] title = {"Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"};
        String[] description={"qwerty","qwerty","qwerty","qwerty","qwerty","qwerty"};

        for(int i=0;i<title.length; i++){
            Notifications current = new Notifications();
            current.setTitle(title[i]);
            current.setDescription(description[i]);
            data.add(current);
        }

    }

    @Override
    public void onItemClick(int position, View v) {

        Toast.makeText(getActivity(),"Notification "+(position+1)+" pressed.",Toast.LENGTH_SHORT).show();

    }
}