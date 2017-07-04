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
import com.example.doctor.ui.model.ProcedureModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aviral on 12-06-2017.
 */

public class ProcedureAdapter extends RecyclerView.Adapter<ProcedureAdapter.DataObjectHolder> implements Filterable{

    private static MyClickListener myClickListener;
    private final Context context;
    private List<ProcedureModel> models;
    private List<ProcedureModel> orig;

    public ProcedureAdapter(Context context, List<ProcedureModel> models) {
        this.context = context;
        this.models = models;
       // this.myClickListener = (MyClickListener) context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_procedure_list, parent, false);
        return new DataObjectHolder(view);

    }

    public void setOnItemClickListener(ProcedureAdapter.MyClickListener myClickListener) {
        ProcedureAdapter.myClickListener = myClickListener;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.procedure.setText(models.get(position).getProcedure_name());
    }

    public void addAll(List<ProcedureModel> models) {

        int position = getItemCount();
        //getModels().addAll(models);
        notifyItemRangeInserted(position, models.size());
    }
    public List<ProcedureModel> getModels() {
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
                List<ProcedureModel> model = new ArrayList<>();
                if(orig==null){
                    orig=models;
                }
                if(constraint!=null){
                    if(orig!=null && orig.size()>0) {
                        for (final ProcedureModel g : orig) {
                            if (g.getProcedure_name().toLowerCase().contains(constraint.toString().toLowerCase()))
                                model.add(g);

                        }
                    }
                    oReturn.values = model;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                models= (List<ProcedureModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface MyClickListener{
        void onItemClick(int position, View v);
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView procedure;
        public DataObjectHolder(View itemView) {
            super(itemView);
            procedure= (TextView) itemView.findViewById(R.id.procedure);
            itemView.findViewById(R.id.procedure_layout).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(myClickListener!=null){
                myClickListener.onItemClick(getLayoutPosition(),v);
            }
        }
    }
}
