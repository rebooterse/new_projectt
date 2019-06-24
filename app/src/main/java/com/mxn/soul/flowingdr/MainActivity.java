package com.mxn.soul.flowingdr;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import adapters.RecyclerViewGalleryMain;
import fragments.MenuListFragment;
import fragments.SaveImages;
import models.Clothes;
import models.Shoes;


public class MainActivity extends AppCompatActivity {

    private FlowingDrawer mDrawer;
    RecyclerView myRecyclerView;
    RecyclerViewGalleryMain recyclerViewAdapter;

  public static   List<Shoes> shoesList;

  public static final int REQUEST_PERMISSION_CODE = 100;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSION_CODE:
            {
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Permision Granted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Permision Denied",Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    public static final int NUM_COLUMNS = 3;
    public static int x;

    public static List<Clothes> finalList = sizer();
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String [] {Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION_CODE);
        }

        x = 0;

        myRecyclerView = findViewById(R.id.rv_main_activity);



         shoesList = new ArrayList<>();
        for (int i = 0; i <18 ; i++) {
            shoesList.add(new Shoes(R.drawable.bridget));
        }
        final RecyclerViewGalleryMain recyclerViewAdapter = new RecyclerViewGalleryMain( MainActivity.this,shoesList);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        myRecyclerView.setAdapter(recyclerViewAdapter);


        mDrawer = findViewById(R.id.drawerlayout);

        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);

           final SwipeRefreshLayout mRefreshLayout = findViewById(R.id.refresh_gallery_main);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        x = 1;
                        shoesList.removeAll(shoesList);
                        recyclerViewAdapter.notifyItemRangeChanged(0, shoesList.size());

                        RecyclerViewGalleryMain recyclerViewGalleryMain = new RecyclerViewGalleryMain(finalList,MainActivity.this);
                        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
                        myRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                        myRecyclerView.setAdapter(recyclerViewGalleryMain);

                        recyclerViewAdapter.notifyItemChanged(0,finalList);
                        recyclerViewAdapter.notifyItemRangeInserted(0, finalList.size());
                        recyclerViewAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);

                    }
                });
        setupMenu();




    }

    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }

    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
    private static List<Clothes> sizer (){
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("message");
            finalList = new ArrayList<>();

            myRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Clothes user = dataSnapshot1.getValue(Clothes.class);
                        finalList.add(user);
                    }

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });


    return  finalList;
        }








}
