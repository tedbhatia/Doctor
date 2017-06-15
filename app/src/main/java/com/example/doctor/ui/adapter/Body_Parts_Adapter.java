package com.example.doctor.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.example.doctor.R;
import com.example.doctor.ui.activity.DiseaseDetail;
import com.example.doctor.ui.fragment.Symptoms_Male_Fragment;
import com.example.doctor.ui.model.Body_Parts;
import com.example.doctor.ui.model.DiseasesList;


import java.util.List;

/**
 * Created by Aviral on 12-06-2017.
 */

public class Body_Parts_Adapter extends ExpandableRecyclerAdapter<Body_Parts,DiseasesList,Body_Parts_Viewholder,Diseases_ViewHolder> {

    private Context context;
    List <Body_Parts> groups;
    private LayoutInflater layoutInflater;
    public MyChildClickListener myClickListener;

    /**
     * Primary constructor. Sets up {@link #mParentList} and {@link #mFlatItemList}.
     * <p>
     * Any changes to {@link #mParentList} should be made on the original instance, and notified via
     * {@link #notifyParentInserted(int)}
     * {@link #notifyParentRemoved(int)}
     * {@link #notifyParentChanged(int)}
     * {@link #notifyParentRangeInserted(int, int)}
     * {@link #notifyChildInserted(int, int)}
     * {@link #notifyChildRemoved(int, int)}
     * {@link #notifyChildChanged(int, int)}
     * methods and not the notify methods of RecyclerView.Adapter.
     *
     * @param parentList List of all parents to be displayed in the RecyclerView that this
     *                   adapter is linked to
     */
    public Body_Parts_Adapter(Context context,@NonNull List<Body_Parts> parentList) {
        super(parentList);
        layoutInflater=LayoutInflater.from(context);
        this.context=context;
        this.groups = parentList;
    }
    public void setOnChildClickListener(MyChildClickListener myClickListener){
        this.myClickListener = myClickListener;
    }
    public interface MyChildClickListener{
        void onChildClickListener(int parent_positon, int child_position, View v);
    }

    @NonNull
    @Override
    public Body_Parts_Viewholder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View body_parts = layoutInflater.inflate(R.layout.list_item_bodyparts, parentViewGroup, false);
        return new Body_Parts_Viewholder(body_parts);
    }

    @NonNull
    @Override
    public Diseases_ViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View diseases = layoutInflater.inflate(R.layout.list_item_diseases, childViewGroup, false);
        return new Diseases_ViewHolder(diseases);
    }

    @Override
    public void onBindParentViewHolder(@NonNull Body_Parts_Viewholder parentViewHolder, int parentPosition, @NonNull Body_Parts parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull Diseases_ViewHolder childViewHolder, final int parentPosition, final int childPosition, @NonNull final DiseasesList child) {

        childViewHolder.bind(child);
        childViewHolder.getDisease_name().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myClickListener!=null){
                    myClickListener.onChildClickListener(parentPosition,childPosition,v);
                }
            }
        });
    }
}
