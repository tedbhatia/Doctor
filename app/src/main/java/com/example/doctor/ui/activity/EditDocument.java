package com.example.doctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.Documents;
import com.example.doctor.ui.model.Insurance;
import com.squareup.picasso.Picasso;

public class EditDocument extends AppCompatActivity {

    private Documents documents;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_document);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra("docs")){
            documents = (Documents) getIntent().getSerializableExtra("docs");
            bindView();
        }
    }

    private void bindView() {
        textView = (TextView) findViewById(R.id.docNameEdit);
        imageView = (ImageView) findViewById(R.id.docImageEdit);

        textView.setText(documents.getDocName());
        Picasso.with(EditDocument.this).load(documents.getUrl()).into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
