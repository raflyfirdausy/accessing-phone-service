package com.example.rafly.rps6accessingphoneservice;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class smsActivity extends AppCompatActivity {

    private EditText et_nomerHp;
    private EditText et_pesan;
    private Button btn_smsManager;
    private Button btn_smsSendTo;
    private Button btn_smsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        et_nomerHp  = (EditText) findViewById(R.id.et_nomerHp);
        et_pesan  = (EditText) findViewById(R.id.et_pesan);
        btn_smsManager = (Button) findViewById(R.id.btn_smsManager);
        btn_smsSendTo = (Button) findViewById(R.id.btn_smsSendTo);
        btn_smsView = (Button) findViewById(R.id.btn_smsView);

        btn_smsManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get the default instance of the SmsManager
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(et_nomerHp.getText().toString(),
                            null,
                            et_pesan.getText().toString(),
                            null,
                            null);
                    gaweToast("SMS Berhasil Dikirim");
                } catch (Exception ex) {
                    gaweToast("SMS Gagal Dikirim");
                    ex.printStackTrace();
                }
            }
        });

        btn_smsSendTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // menambahkan phone number ke URI data
                Uri uri = Uri.parse("smsto:" + et_nomerHp.getText().toString());
                // membuat intent baru dengan ACTION_SENDTO
                Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);
                // menambahkan isi SMS pada field sms_body
                smsSIntent.putExtra("sms_body", et_pesan.getText().toString());
                try{
                    startActivity(smsSIntent);
                } catch (Exception ex) {
                    gaweToast("Pengiriman SMS Gagal");
                    ex.printStackTrace();
                }
            }
        });

        btn_smsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsVIntent = new Intent(Intent.ACTION_VIEW);
                // hanya akan membuka aplikasi SMS/MMS default di Android
                smsVIntent.setType("vnd.android-dir/mms-sms");

                // menambahkan nomor telepon dan isi SMS otomatis
                smsVIntent.putExtra("address", et_nomerHp.getText().toString());
                smsVIntent.putExtra("sms_body", et_pesan.getText().toString());
                try{
                    startActivity(smsVIntent);
                } catch (Exception ex) {
                    gaweToast("Pengiriman SMS Gagal");
                    ex.printStackTrace();
                }
            }
        });
    }

    private void gaweToast(String pesan){
        Toast.makeText(smsActivity.this, pesan, Toast.LENGTH_LONG).show();
    }
}
