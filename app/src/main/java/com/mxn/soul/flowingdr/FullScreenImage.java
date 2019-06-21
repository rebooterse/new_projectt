package com.mxn.soul.flowingdr;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class FullScreenImage extends AppCompatActivity {

    String img;
    TextView shopName, price, city, address, sizes, colors, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        final ImageView fullScreenImage = findViewById(R.id.full_screen_image);
        final ImageButton backBtn = findViewById(R.id.backBtn_FullScreen);
        ImageButton infoBtn = findViewById(R.id.info_btn_fullScreen);
        final LinearLayout infoTexts = findViewById(R.id.infoAllTexts_fullScreen);
        shopName = findViewById(R.id.shopName_fullScreen);
        price = findViewById(R.id.clothePrice_fullScreen);
        city = findViewById(R.id.cityName_fullScreen);
        address = findViewById(R.id.address_fullScreen);
        sizes = findViewById(R.id.clotheSize_fullScreen);
        colors = findViewById(R.id.clotheColor_fullScreen);
        about = findViewById(R.id.about_fullScreen);

        Intent intent = getIntent();
        img = intent.getStringExtra("IMG");
        Uri uri = Uri.parse(img);
        Picasso.with(this)
                .load(uri)
                .into(fullScreenImage);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FullScreenImage.this, MainActivity.class));
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (infoTexts.getVisibility()) {
                    case View.INVISIBLE: {
                        fullScreenImage.setColorFilter(Color.HSVToColor(200, new float[3]));
                        infoTexts.setVisibility(View.VISIBLE);
                        backBtn.setVisibility(View.GONE);
                        break;
                    }
                    case View.VISIBLE: {
                        fullScreenImage.clearColorFilter();
                        infoTexts.setVisibility(View.INVISIBLE);
                        backBtn.setVisibility(View.VISIBLE);
                        break;
                    }
                }
            }
        });
    }

    //todo
    // shopName =           sranc axper bazayi het petqa kapvi
    //price =
    //city =
    //address
    //sizes =
    //colors
    //about =
}
