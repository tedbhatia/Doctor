package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.doctor.R;
import com.example.doctor.ui.model.find_doctor_model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class FindDoctorAdapter extends RecyclerView.Adapter<FindDoctorAdapter.DataObjectHolder> implements Filterable {
    private static MyClickListener myClickListener;
    private final Context context;
    private List<find_doctor_model> models;
    private List<find_doctor_model> orig; //original

    public FindDoctorAdapter(Context context, List<find_doctor_model> models) {
        this.context = context;
        this.models = models;
        //this.myClickListener = (MyClickListener) context;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new DataObjectHolder(view);
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        FindDoctorAdapter.myClickListener = myClickListener;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Picasso.with(context).load(models.get(position).getId()).into(holder.doctorImage);
        holder.name.setText(models.get(position).getName());
        holder.mobileNumber.setText(models.get(position).getMobile_number());
        holder.speciality.setText(models.get(position).getSpeciality());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public void addAll(List<find_doctor_model> models) {

        int position = getItemCount();
        //getModels().addAll(models);
        notifyItemRangeInserted(position, models.size());
    }

    public void addItem(find_doctor_model model, int index) {
        getModels().add(model);
        notifyItemInserted(index);
    }

    public void addItem(find_doctor_model model) {
        getModels().add(model);
        notifyItemInserted(getItemCount() - 1);
    }

    public void deleteAll() {

        getModels().clear();
        notifyDataSetChanged();
    }

    public void replaceAll(List<find_doctor_model> models) {
        deleteAll();
        addAll(models);
    }

    public interface MyClickListener{
        void onItemClick(int position, View v);
    }

    public List<find_doctor_model> getModels() {
        return models;
    }

    public find_doctor_model getItem(int index) {
        return getModels().get(index);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                List<find_doctor_model> model = new ArrayList<find_doctor_model>();
                if(orig==null)
                    orig = models;
                if(constraint!=null){
                    if(orig!=null && orig.size()>0){
                        for(final find_doctor_model g:orig){
                            if(g.getName().toLowerCase().contains(constraint.toString().toLowerCase())||g.getDescription().toLowerCase().contains(constraint.toString().toLowerCase()))
                                model.add(g);
                        }
                    }
                    oReturn.values = model;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                models= (List<find_doctor_model>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView doctorImage;
        private TextView name;
        private TextView mobileNumber;
        private TextView speciality;
        private LinearLayout llayout;

        public DataObjectHolder(View itemView) {
            super(itemView);

            doctorImage = (ImageView) itemView.findViewById(R.id.doctor_image);
            name = (TextView) itemView.findViewById(R.id.name);
            mobileNumber = (TextView) itemView.findViewById(R.id.mobile_number);
            speciality = (TextView) itemView.findViewById(R.id.speciality);
            llayout = (LinearLayout) itemView.findViewById(R.id.llayout);

            llayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (myClickListener != null) {
                myClickListener.onItemClick(getLayoutPosition(), v);
            }
        }
    }
}
