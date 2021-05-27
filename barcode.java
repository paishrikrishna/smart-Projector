package com.example.myaaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.StringSearch;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class barcode extends AppCompatActivity {
    public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER   =   "com.example.application.example.EXTRA_NUMBER";
    String re;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        Intent intent   =   getIntent();

        final String type = intent.getStringExtra(option.EXTRA_TEXT);
        ZXingScannerView scannerView = new ZXingScannerView(this);scannerView = new ZXingScannerView(this);
        final ZXingScannerView finalScannerView = scannerView;
        scannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {
               re = result.getText();
                Toast.makeText(barcode.this,re,Toast.LENGTH_SHORT).show();
                //setContentView(R.layout.activity_main);

                finalScannerView.stopCamera();
                if(type.equals("mobile")) {
                    open_console(re, type);
                }
                else if(type.equals("remote")){
                    opem_remote(re);
                }
            }
        });
        setContentView(scannerView);
        scannerView.startCamera();

    }

    public void opem_remote(String remo_ip) {
        Intent intent = new Intent(this, hardware.class);
        intent.putExtra(EXTRA_TEXT, remo_ip);
        startActivity(intent);
    }

    public void open_console(String ip, String type) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_TEXT, ip);
        startActivity(intent);
    }
}
