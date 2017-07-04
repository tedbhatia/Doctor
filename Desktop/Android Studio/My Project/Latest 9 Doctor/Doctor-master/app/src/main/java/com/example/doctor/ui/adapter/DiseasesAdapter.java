package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.Diseases;

import java.util.List;

/**
 * Created by SHIVIKA NAGPAL on 12-06-2017.
 */

public class DiseasesAdapter extends RecyclerView.Adapter<DiseasesAdapter.ViewHolder> {

    private static My_Health_Acc_Adapter.MyClickListener myClickListener;
    private List<Diseases> grid;
    private Context context;

    public DiseasesAdapter(List<Diseases> grid, Context context) {
        this.grid = grid;
        this.context = context;
    }

    public void setOnItemClickListener(My_Health_Acc_Adapter.MyClickListener myClickListener) {

        this.myClickListener = myClickListener;

    }

    @Override
    public DiseasesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.diseases_custom_box,parent,false);
        return new DiseasesAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(DiseasesAdapter.ViewHolder holder, int position) {

        Diseases listItem=grid.get(position);

        holder.disease_name.setText(listItem.getDiseaseName());
        holder.date.setText(listItem.getDate());
        holder.notes.setText(listItem.getNotes());

    }

    @Override
    public int getItemCount() {

        return grid.size();

    }

    public void removeItem(int pos){
        grid.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos,grid.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView disease_name,date,notes;
        //private LinearLayout linearLayout;
        private ImageButton edit;

        public ViewHolder(View itemView) {

            super(itemView);

            disease_name=(TextView)itemView.findViewById(R.id.disease_name_edit);
            date=(TextView)itemView.findViewById(R.id.date_edit);
            notes=(TextView)itemView.findViewById(R.id.notes_edit);
            //linearLayout=(LinearLayout)itemView.findViewById(R.id.rowFG);
            edit=(ImageButton) itemView.findViewById(R.id.editButton);

            edit.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(myClickListener!=null){

                myClickListener.onItemClick(getLayoutPosition(),v);

            }

        }

    }


}
