package com.example.rafly.rps6accessingphoneservice;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_panggilanTelepone;
    private Button btn_membacaStatusTelepone;
    private Button btn_kirimPesanMMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] PERMISSIONS = {Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS};
        ActivityCompat.requestPermissions(this, PERMISSIONS, 1234);

        btn_panggilanTelepone   = (Button) findViewById(R.id.btn_panggilanTelepone);
        btn_membacaStatusTelepone   = (Button) findViewById(R.id.btn_membacaStatusTelepone);
        btn_kirimPesanMMS   = (Button) findViewById(R.id.btn_kirimPesanMMS);

        btn_membacaStatusTelepone.setVisibility(View.GONE);

        btn_panggilanTelepone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PanggilanActivity.class));
            }
        });

        btn_kirimPesanMMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, smsActivity.class));
            }
        });
    }

    private void gaweToast(String pesan){
        Toast.makeText(MainActivity.this, pesan, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 1234: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    gaweToast("Permission Granted");
                } else
                {
                    gaweToast("Permission Denied!");
                    finish();
                }
            }
        }
    }
}
