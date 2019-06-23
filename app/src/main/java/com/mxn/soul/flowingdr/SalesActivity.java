package com.mxn.soul.flowingdr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import adapters.SalesRecViewAdapter;

@SuppressLint("Registered")
public class SalesActivity extends AppCompatActivity {

    private RecyclerView rvFeed;
    private FlowingDrawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

     rvFeed = (RecyclerView) findViewById(R.id.rvSale);
     mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);

        setupSale();
    }

    private void setupSale() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);
        SalesRecViewAdapter salesRecViewAdapter = new SalesRecViewAdapter(this);
        rvFeed.setAdapter(salesRecViewAdapter);
        salesRecViewAdapter.updateItems();
    }
}
