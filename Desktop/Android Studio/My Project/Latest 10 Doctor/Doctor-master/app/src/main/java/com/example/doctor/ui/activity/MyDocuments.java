package com.example.doctor.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.DocumentsAdapter;
import com.example.doctor.ui.model.Documents;
import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class MyDocuments extends AppCompatActivity implements DocumentsAdapter.MyClickListener {

    private RecyclerView recyclerView;
    private DocumentsAdapter adapter;
    private List<Documents> data;
    private Menu menu;
    private ProgressDialog progressDialog;

    PullRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_documents);
        setTitle("My Documents");

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.isIndeterminate();
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
            }
        }, 3000);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = new ArrayList<>();

        refreshLayout = (PullRefreshLayout) findViewById(R.id.swipeLayoutDocuments);
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new TimerTask() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        prepareData();
        initRecyclerView();
    }

    private void prepareData() {
        String[] url={"http://www.psdgraphics.com/file/attachment-icon.jpg","http://www.psdgraphics.com/file/attachment-icon.jpg","http://www.psdgraphics.com/file/attachment-icon.jpg","http://www.psdgraphics.com/file/attachment-icon.jpg"};
        //String[] url = {"https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-0/p480x480/19060139_1470917992968342_4075432350527055133_n.jpg?oh=235194907e7d7966b5c8780221e97bcb&oe=59DD91C6", "https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-0/p480x480/19060139_1470917992968342_4075432350527055133_n.jpg?oh=235194907e7d7966b5c8780221e97bcb&oe=59DD91C6", "https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-0/p480x480/19060139_1470917992968342_4075432350527055133_n.jpg?oh=235194907e7d7966b5c8780221e97bcb&oe=59DD91C6", "https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-0/p480x480/19060139_1470917992968342_4075432350527055133_n.jpg?oh=235194907e7d7966b5c8780221e97bcb&oe=59DD91C6"};
        String[] docName = {"Doc 1", "Doc 2", "Doc 3", "Doc 4"};
        for (int i = 0; i < url.length; i++) {
            Documents current = new Documents();
            current.setUrl(url[i]);
            current.setDocName(docName[i]);
            data.add(current);
        }
        adapter = new DocumentsAdapter(MyDocuments.this, data);
        adapter.setCheckedStatus();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.doctor_list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(MyDocuments.this, 2));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onItemClick(int position, View v) {

        Intent intent = new Intent(MyDocuments.this, EditDocument.class);
        intent.putExtra("docs", data.get(position));
        startActivity(intent);

    }

    @Override
    public void onLongItemClick(int position, View v) {
        Toast.makeText(this, "Long click " + position, Toast.LENGTH_SHORT).show();
        menu.clear();
        getMenuInflater().inflate(R.menu.share,menu);
        getMenuInflater().inflate(R.menu.bin, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            if(adapter.getCheckedStatus()){
                recyclerView.setAdapter(new DocumentsAdapter(this,data));
                recyclerView.invalidate();
                menu.clear();
                getMenuInflater().inflate(R.menu.main, menu);
                adapter.setCheckedStatus();
            }
            else{
                Intent intent = new Intent(MyDocuments.this, MainActivity.class);
                startActivity(intent);
            }

        } else {
            if (adapter.getCheckedStatus()) {
                if(id == R.id.bin) {
                    Toast.makeText(this, "Deleted.", Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(new DocumentsAdapter(this, data));
                    recyclerView.invalidate();
                    menu.clear();
                    getMenuInflater().inflate(R.menu.main, menu);
                    adapter.setCheckedStatus();
                }
                else{
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"emailaddress@example.com"});
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "This is Subject");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }

            } else {
                Intent intent = new Intent(MyDocuments.this, EditDocument.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (adapter.getCheckedStatus()) {
            recyclerView.setAdapter(new DocumentsAdapter(this,data));
            recyclerView.invalidate();
            menu.clear();
            getMenuInflater().inflate(R.menu.main, menu);
            adapter.setCheckedStatus();
        }else{
            Intent intent = new Intent(MyDocuments.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
