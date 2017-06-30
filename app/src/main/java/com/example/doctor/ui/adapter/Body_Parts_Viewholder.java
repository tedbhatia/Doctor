package com.example.doctor.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.example.doctor.R;
import com.example.doctor.ui.model.Body_Parts;

/**
 * Created by Aviral on 12-06-2017.
 */

public class Body_Parts_Viewholder extends ParentViewHolder {

    private TextView male_body_parts_names;
    private ImageView imageView;
    private TextView parent_list_item_male_bodyparts;
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
        imageView = (ImageView) itemView.findViewById(R.id.expand_button);
        parent_list_item_male_bodyparts = (TextView) itemView.findViewById(R.id.parent_list_item_male_bodyparts);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isExpanded()) {
                    collapseView();
                    imageView.setImageResource(R.drawable.expand_button);
                } else {
                    expandView();
                    imageView.setImageResource(R.drawable.contract_button);
                }
            }
        });

        parent_list_item_male_bodyparts.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isExpanded()) {
                    collapseView();
                    imageView.setImageResource(R.drawable.expand_button);
                } else {
                    expandView();
                    imageView.setImageResource(R.drawable.contract_button);
                }
            }
        });
    }
    public void bind(Body_Parts body_parts){
        male_body_parts_names.setText(body_parts.getName());
    }

    @Override
    public boolean shouldItemViewClickToggleExpansion() {
        return false;
    }
}
