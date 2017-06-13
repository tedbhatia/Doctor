package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.MedicineModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aviral on 12-06-2017.
 */

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.DataObjectHolder> implements Filterable{

    private static MyClickListener myClickListener;
    private final Context context;
    private List<MedicineModel> models;
    private List<MedicineModel> orig;

    public MedicineAdapter(Context context, List<MedicineModel> models) {
        this.context = context;
        this.models = models;
       // this.myClickListener = (MyClickListener) context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine_list, parent, false);
        return new DataObjectHolder(view);

    }

    public void setOnItemClickListener(MedicineAdapter.MyClickListener myClickListener) {
        MedicineAdapter.myClickListener = myClickListener;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.medicine.setText(models.get(position).getName());
    }

    public void addAll(List<MedicineModel> models) {

        int position = getItemCount();
        //getModels().addAll(models);
        notifyItemRangeInserted(position, models.size());
    }
    public List<MedicineModel> getModels() {
        return models;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                List<MedicineModel> model = new ArrayList<>();
                if(orig==null){
                    orig=models;
                }
                if(constraint!=null){
                    if(orig!=null && orig.size()>0) {
                        for (final MedicineModel g : orig) {
                            if (g.getName().toLowerCase().contains(constraint.toString().toLowerCase()))
                                model.add(g);

                        }
                    }
                    oReturn.values = model;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                models= (List<MedicineModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface MyClickListener{
        void onItemClick(int position,View v);
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView medicine;
        public DataObjectHolder(View itemView) {
            super(itemView);
            medicine= (TextView) itemView.findViewById(R.id.medicine);
            itemView.findViewById(R.id.medicine_layout).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(myClickListener!=null){
                myClickListener.onItemClick(getLayoutPosition(),v);
            }
        }
    }
}
