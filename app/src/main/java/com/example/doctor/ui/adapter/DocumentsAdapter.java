package com.example.doctor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.activity.MyDocuments;
import com.example.doctor.ui.model.Documents;
import com.example.doctor.ui.model.Insurance;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by tejas on 12/6/17.
 */

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.MyViewHolder> {
    private LayoutInflater inflator;
    private static DocumentsAdapter.MyClickListener myClickListener;
    List<Documents> data = Collections.emptyList();
    private Context context;
    private Picasso picasso;

    public DocumentsAdapter(Context context, List<Documents> data) {
        inflator = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }


    @Override
    public DocumentsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.documents_custom_box,parent,false);
        DocumentsAdapter.MyViewHolder myViewHolder = new DocumentsAdapter.MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(DocumentsAdapter.MyViewHolder holder, int position) {

        final Documents current = data.get(position);
        picasso.with(context).load(current.getUrl().toString()).into(holder.image);
        holder.docName.setText(current.getDocName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void setOnItemClickListener(DocumentsAdapter.MyClickListener myClickListener) {
        DocumentsAdapter.myClickListener = myClickListener;
    }

    public interface MyClickListener{
        void onItemClick(int position,View v);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CardView linearLayout;
        private ImageView image;
        private TextView docName;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = (CardView) itemView.findViewById(R.id.recycler_view);
            image = (ImageView) itemView.findViewById(R.id.imageDoc);
            docName = (TextView) itemView.findViewById(R.id.docName);

            image.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(myClickListener!=null){
                myClickListener.onItemClick(getLayoutPosition(),v);
            }
        }
    }
}
