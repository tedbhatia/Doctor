package com.example.doctor.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.Measurement_Adapter;
import com.example.doctor.ui.model.Measurement_Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tejas on 2/6/17.
 */

public class Measurement_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private Measurement_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_my_measurements,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.measurement_list);

        adapter = new Measurement_Adapter(getActivity(),getData());

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),1,false));

        return rootView;
    }

    public static List<Measurement_Info> getData() {
        List<Measurement_Info> data = new ArrayList<>();

        String[] type = {"Aplha", "Beta", "Gamma", "Theta", "Psi", "Phi", "Omega"};
        String[] date = {"Aplha", "Beta", "Gamma", "Theta", "Psi", "Phi", "Omega"};
        String[] note = {"Aplha", "Beta", "Gamma", "Theta", "Psi", "Phi", "Omega"};

        for (int i = 0; i < type.length && i < date.length && i < note.length; i++) {
            Measurement_Info current = new Measurement_Info();
            current.setType(type[i]);
            current.setDate(date[i]);
            current.setNote(note[i]);
            data.add(current);
        }

        return data;
    }
}
