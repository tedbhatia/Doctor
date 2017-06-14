package com.example.doctor.ui.adapter;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctor.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

/**
 * Created by Aviral on 12-06-2017.
 */

public class Body_Parts_Viewholder extends GroupViewHolder {

    private TextView male_body_parts_names;
    private ImageView imageView;


    public Body_Parts_Viewholder(View itemView) {
        super(itemView);
        male_body_parts_names= (TextView) itemView.findViewById(R.id.parent_list_item_male_bodyparts);
        imageView= (ImageView) itemView.findViewById(R.id.male_expand_arrow);
    }
    public void setMale_body_parts(String name){
        male_body_parts_names.setText(name);
    }

    @Override
    public void expand() {
        imageView.setImageResource(android.R.drawable.arrow_up_float);
    }

    @Override
    public void collapse() {
        imageView.setImageResource(android.R.drawable.arrow_down_float);
    }
}
