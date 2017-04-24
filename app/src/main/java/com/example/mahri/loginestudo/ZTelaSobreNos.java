package com.example.mahri.loginestudo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ZTelaSobreNos extends AppCompatActivity {

    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ztela_sobre_nos);
/*
        btnVoltar = (Button) findViewById(R.id.btnVoltar);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreMenu = new Intent(ZTelaSobreNos.this, TelaMenu.class);
                startActivity(abreMenu);
            }

        }); */
    }
}
