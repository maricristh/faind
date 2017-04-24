package com.example.mahri.loginestudo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ZTelaAjuda extends AppCompatActivity {

    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ztela_ajuda);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltaMenu = new Intent(ZTelaAjuda.this, TelaMenu.class);
                startActivity(voltaMenu);
            }

        });
    }
}
