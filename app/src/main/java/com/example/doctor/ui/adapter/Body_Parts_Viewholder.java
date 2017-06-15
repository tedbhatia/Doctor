package com.example.doctor.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.example.doctor.R;
import com.example.doctor.ui.model.Body_Parts;

/**
 * Created by Aviral on 12-06-2017.
 */

public class Body_Parts_Viewholder extends ParentViewHolder {

    private TextView male_body_parts_names;

    public TextView getMale_body_parts_names() {
        return male_body_parts_names;
    }

    public void setMale_body_parts_names(TextView male_body_parts_names) {
        this.male_body_parts_names = male_body_parts_names;
    }

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */

    public Body_Parts_Viewholder(@NonNull View itemView) {
        super(itemView);
        male_body_parts_names= (TextView) itemView.findViewById(R.id.parent_list_item_male_bodyparts);
    }
    public void bind(Body_Parts body_parts){
        male_body_parts_names.setText(body_parts.getName());
    }
}
