package com.example.rafly.rps6accessingphoneservice;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PanggilanActivity extends AppCompatActivity {

    private EditText et_nomerHp;
    private Button btn_panggil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panggilan);

        et_nomerHp = (EditText) findViewById(R.id.et_nomerHp);
        btn_panggil = (Button) findViewById(R.id.btn_panggil);

        btn_panggil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et_nomerHp.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
    }
}
