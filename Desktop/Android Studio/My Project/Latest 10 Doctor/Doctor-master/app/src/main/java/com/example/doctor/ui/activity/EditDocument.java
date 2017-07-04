package com.example.doctor.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.model.Documents;
import com.example.doctor.ui.model.Insurance;
import com.squareup.picasso.Picasso;

public class EditDocument extends AppCompatActivity {

    private Documents documents;
    private ImageView imageView;
    private EditText textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_document);
        setTitle("Document Viewer/Editor");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = (Button) findViewById(R.id.docEditButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditDocument.this,"Added Successfully",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        imageView = (ImageView) findViewById(R.id.docImageEdit);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,4000);
            }
        });
        if(getIntent().hasExtra("docs")){
            documents = (Documents) getIntent().getSerializableExtra("docs");
            bindView();
        }
    }

    private void bindView() {
        textView = (EditText) findViewById(R.id.docNameEdit);
        imageView = (ImageView) findViewById(R.id.docImageEdit);
        button = (Button) findViewById(R.id.docEditButton);

        textView.setText(documents.getDocName());
        textView.setSelection(textView.getText().length());
        button.setText("SAVE");
        Picasso.with(EditDocument.this).load(documents.getUrl()).into(imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditDocument.this,"Edited Successfully",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zoomOut();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 4000:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    imageView.setImageURI(uri);
                }
        }
    }

    private void zoomOut() {
        //
        Dialog dialog = new Dialog(this,R.style.Theme_AppCompat_DialogWhenLarge);
        dialog.setContentView(R.layout.image);
        //dialog.findViewById(R.id.imageZoom);
        //((ImageView)dialog.findViewById(R.id.imageZoom)).setImageURI(Uri.parse(person1.getUri()));
        Picasso.with(EditDocument.this).load(Uri.parse(documents.getUrl())).into((ImageView)dialog.findViewById(R.id.imageZoom));
        dialog.show();
    }

}
