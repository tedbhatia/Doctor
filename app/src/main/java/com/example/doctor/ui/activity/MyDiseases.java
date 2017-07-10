package com.example.doctor.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.doctor.R;
import com.example.doctor.support.service.ApiClient;
import com.example.doctor.support.service.RequestInterface;
import com.example.doctor.ui.activity.LoginScreen;
import com.example.doctor.ui.adapter.DiseasesAdapter;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.model.Diseases;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDiseases extends AppCompatActivity implements My_Health_Acc_Adapter.MyClickListener,RecyclerTouchListener.RecyclerTouchListenerHelper {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DiseasesAdapter adapter;

    private List<Diseases> listItems;
    private Diseases listItem;

    private int flag = 0;
    private ProgressDialog progressDialog;

    private OnActivityTouchListener touchListener;
    private RecyclerTouchListener onTouchListener;

    PullRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_diseases);

        setTitle("My Diseases");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.isIndeterminate();
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (flag == 0) {
                    Toast.makeText(getApplicationContext(), "Poor Connection, Try Again", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, 20000);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        listItems=new ArrayList<>();

        /*listItems.add(0,new Diseases("Periodontitis (gum inflammation)","23/05/2017","Periodontitis is a condition that involves inflammation of the gums and other structures that support the teeth. Periodontitis is caused by a bacterial infection. The body's attempt to fight the infection results in damage to the jaw bone and the ligament around the teeth. Pockets form between the teeth and gums. Teeth may become loose and even fall out. Older adults, people with diabetes, and people who smoke are more likely to get periodontitis."));
        listItems.add(1,new Diseases("Rhinitis (nasal inflammation)","23/05/2017","Inflammation of the mucus membranes of the nose and upper respiratory tract can occur in response to an irritant. In allergic rhinitis, the immune system in the nose reacts strongly to irritants called airborne allergens. This is a common disorder, affecting at least a quarter of the population."));
        listItems.add(2,new Diseases("Arthritis","01/06/2018","Inflammation of a single joint or multiple joints. There are many causes including infection, and inflammatory conditions such as: osteoarthritis, gout, pseudogout, rheumatoid arthritis, lupus, ankylosing spondylitis, and psoriasis."));

        adapter=new DiseasesAdapter(listItems,this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);*/

        loadJSON();

        onTouchListener=new RecyclerTouchListener(this,recyclerView);

        onTouchListener
                .setIndependentViews(R.id.editButton)
                .setViewsToFade(R.id.editButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Toast.makeText(MyDiseases.this,"View",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        Toast.makeText(MyDiseases.this,"Button",Toast.LENGTH_SHORT).show();
                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {
                        Toast.makeText(MyDiseases.this,"View long clicked!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setSwipeOptionViews(R.id.delete)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                    }
                });

        refreshLayout = (PullRefreshLayout) findViewById(R.id.pullLayout);
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*refreshLayout.postDelayed(new TimerTask() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);*/
                loadJSON();
            }
        });

        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int currentFirstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (currentFirstVisibleItem > this.mLastFirstVisibleItem) {
                    MyDiseases.this.getSupportActionBar().hide();
                } else if (currentFirstVisibleItem < this.mLastFirstVisibleItem) {
                    MyDiseases.this.getSupportActionBar().show();
                }

                this.mLastFirstVisibleItem = currentFirstVisibleItem;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(onTouchListener); }

    @Override
    protected void onPause() {
        super.onPause();
        recyclerView.removeOnItemTouchListener(onTouchListener);
    }

    @Override
    public void onItemClick(int position, View v) {

        listItem=listItems.get(position);

        Intent intent=new Intent(MyDiseases.this,EditDiseases.class);
        intent.putExtra("disease",listItem);
        startActivity(intent);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchListener != null) touchListener.getTouchCoordinates(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){

            this.finish();

        }

        else{

            Intent intent=new Intent(MyDiseases.this,EditDiseases.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);

    }

    private void loadJSON() {
        final RequestInterface request = ApiClient.getClient().create(RequestInterface.class);
        Call<List<Diseases>> call = request.getMyDiseases(LoginScreen.userid);
        call.enqueue(new Callback<List<Diseases>>() {
            @Override
            public void onResponse(Call<List<Diseases>> call, Response<List<Diseases>> response) {
                try {
//                    Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_SHORT).show();
                    listItems = response.body();
                    flag = 1;
                    progressDialog.dismiss();
                    refreshLayout.setRefreshing(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                adapter = new DiseasesAdapter(listItems,getApplicationContext());
                adapter.setOnItemClickListener(MyDiseases.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Diseases>> call, Throwable t) {

            }
        });
    }

}
