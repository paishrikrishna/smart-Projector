package com.example.myaaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class hardware extends AppCompatActivity {
    String device_no = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware);
        Intent intent   =   getIntent();
        final String remo_ip = intent.getStringExtra(barcode.EXTRA_TEXT);
        TextView scan = (TextView) findViewById(R.id.scan_but);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               opem_scan(remo_ip);

            }
        });


    }

    public void  opem_scan(final String remo_ip) {

        ZXingScannerView scannerView = new ZXingScannerView(this);scannerView = new ZXingScannerView(this);
        final ZXingScannerView finalScannerView = scannerView;
        scannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
            @Override
            public void handleResult(Result result) {
                String re= result.getText();
                Toast.makeText(hardware.this, re,Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_hardware);
                final DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference().child("device_no");
                ref.child(re).child("ip").setValue(remo_ip);
                ConstraintLayout setup = (ConstraintLayout) findViewById(R.id.setup);
                setup.setVisibility(View.GONE);
                TextView sdf = (TextView) findViewById(R.id.textView7);
                sdf.setVisibility(View.VISIBLE);
                finalScannerView.stopCamera();

            }
        });
        setContentView(scannerView);
        scannerView.startCamera();

    }
}
