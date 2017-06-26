package com.example.doctor.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.MedicineDetailExpandableListAdapter;
import com.example.doctor.ui.model.MedicineModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MedicineDetail extends AppCompatActivity implements View.OnClickListener{
    ExpandableListAdapter expandable_list_adapter;
    ExpandableListView expandable_list_view;
    List<String> listHeading;
    HashMap<String, String> listMap;
    MedicineModel medicineModel;
    Button share;

    TextView usage_instructions, overdose_instructions, possible_side_effects, brand_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);
        View footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.footer, null, false);
        expandable_list_view = (ExpandableListView) findViewById(R.id.expandable_list_view);
        expandable_list_view.addFooterView(footerView);
        if(getIntent().hasExtra("meds")){
            medicineModel = (MedicineModel) getIntent().getSerializableExtra("meds");
            bindView();
        }

        prepareData();
        expandable_list_adapter = new MedicineDetailExpandableListAdapter(this, listHeading, listMap);
        expandable_list_view.setAdapter(expandable_list_adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(medicineModel.getMedicine_name());


//        launch();
    }

    private void prepareData() {
        listHeading = new ArrayList<String>();
        listMap = new HashMap<String, String>();
        listHeading.add("Usage Instructions");
        listHeading.add("Dosage");
        listHeading.add("Method");
        listHeading.add("Frequency");
        listHeading.add("Date");
        listHeading.add("Overdose Instructions");
        listHeading.add("Side Effects");
        listHeading.add("Brand Names");

        listMap.put(listHeading.get(0),medicineModel.getUsage_instructions());
        listMap.put(listHeading.get(1),medicineModel.getDosage_amt());
        listMap.put(listHeading.get(2),medicineModel.getMethod());
        listMap.put(listHeading.get(3),String.valueOf(medicineModel.getFrequency()));
        listMap.put(listHeading.get(4),medicineModel.getMedicine_date());
        listMap.put(listHeading.get(5),medicineModel.getOverdose_instructions());
        listMap.put(listHeading.get(6),medicineModel.getPossible_sideeffects());
        listMap.put(listHeading.get(7),medicineModel.getBrand_names());
    }

    private void bindView() {
//        usage_instructions = (TextView) findViewById(R.id.usage_instructions);
//        overdose_instructions = (TextView) findViewById(R.id.overdose_instructions);
//        possible_side_effects = (TextView) findViewById(R.id.possible_side_effects);
//        brand_names = (TextView) findViewById(R.id.brand_names);
//        usage_instructions.setOnClickListener(this);
        share = (Button) findViewById(R.id.share);
        share.setOnClickListener(this);
    }

    public void launch() {
//        usage_instructions.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scramble");
//        overdose_instructions.setText("to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including vers");
//        possible_side_effects.setText("hello world");
//        brand_names.setText("he standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham");
    }

    //    public MedicineDetail(String s1, String s2, String s3, String s4){
//        usage_instructions.setText(s1);
//        overdose_instructions.setText(s2);
//        possible_side_effects.setText(s3);
//        brand_names.setText(s4  );
//
//
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            this.finish();

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share:
                Toast.makeText(this,"Sharing..",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}


