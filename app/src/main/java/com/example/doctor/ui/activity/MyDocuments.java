package com.example.doctor.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.DocumentsAdapter;
import com.example.doctor.ui.adapter.MyDoctorAdapter;
import com.example.doctor.ui.model.Doctor;
import com.example.doctor.ui.model.Documents;

import java.util.ArrayList;
import java.util.List;

public class MyDocuments extends AppCompatActivity implements DocumentsAdapter.MyClickListener{

    private RecyclerView recyclerView;
    private DocumentsAdapter adapter;
    private List<Documents> data;

    private Documents document;
    private LinearLayout documentLayout;
    private CheckBox checkBox;

    private MenuItem addItem,deleteItem;
    private boolean isAdd;
    private Menu mMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_documents);
        setTitle("My Documents");

        documentLayout=(LinearLayout)findViewById(R.id.documentLayout);
//        checkBox=(CheckBox)findViewById(R.id.selected);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = new ArrayList<>();

        prepareData();
        initRecyclerView();

    }

    @Override
    public void onBackPressed(){
        if(adapter.getFlag()==1){
            adapter.setFlag(0);
            startActivity(new Intent(this,MyDocuments.class));
        }
    }

    private void prepareData() {
        String[] url = {"https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-0/p480x480/19060139_1470917992968342_4075432350527055133_n.jpg?oh=235194907e7d7966b5c8780221e97bcb&oe=59DD91C6", "https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-0/p480x480/19060139_1470917992968342_4075432350527055133_n.jpg?oh=235194907e7d7966b5c8780221e97bcb&oe=59DD91C6", "https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-0/p480x480/19060139_1470917992968342_4075432350527055133_n.jpg?oh=235194907e7d7966b5c8780221e97bcb&oe=59DD91C6", "https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-0/p480x480/19060139_1470917992968342_4075432350527055133_n.jpg?oh=235194907e7d7966b5c8780221e97bcb&oe=59DD91C6"};
        String[] docName = {"Doc 1", "Doc 2", "Doc 3", "Doc 4"};
        for(int i=0;i<url.length; i++){
            Documents current = new Documents();
            current.setUrl(url[i]);
            current.setDocName(docName[i]);
            data.add(current);
        }
        adapter = new DocumentsAdapter(MyDocuments.this,data);
    }
    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.doctor_list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(MyDocuments.this,2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        mMenu=menu;

        addItem=menu.findItem(R.id.add);
        deleteItem=menu.findItem(R.id.delete);

        return super.onCreateOptionsMenu(menu);

    }

    private void setActionIcon(boolean plusButton)
    {
        MenuItem item = mMenu.findItem(R.id.actionButton);

        if(mMenu != null)
        {
            if(plusButton)
            {
                item.setIcon(R.drawable.plus);
                item.setTitle("plus");
            }
            else
            {
                item.setIcon(R.drawable.delete);
                item.setTitle("delete");
            }
        }
    }



    @Override
    public void onItemClick(int position, View v) {
        Intent intent = new Intent(MyDocuments.this,EditDocument.class);
        intent.putExtra("docs",data.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position, View v) {

        setActionIcon(false);

        document=data.get(position);
        document.toggleChecked();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
            return true;
        }
        else {

            if(item.getTitle()=="plus"){
                Intent intent=new Intent(MyDocuments.this,EditDocument.class);
                startActivity(intent);
            }
            else if(item.getTitle()=="delete"){
                Toast.makeText(MyDocuments.this,"Deleted.",Toast.LENGTH_SHORT).show();
            }

        }

        return super.onOptionsItemSelected(item);

    }




}
