package com.example.myaaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class option extends AppCompatActivity {
    public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER   =   "com.example.application.example.EXTRA_NUMBER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        ImageView mob = (ImageView) findViewById(R.id.mobile);
        ImageView remote = (ImageView) findViewById(R.id.remote);
        mob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_barcode("mobile");
            }
        });
        remote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_barcode("remote");
            }
        });
    }

    public void open_barcode(String type) {
        Intent intent = new Intent(this, barcode.class);
           intent.putExtra(EXTRA_TEXT, type);
                 startActivity(intent);
    }
}

