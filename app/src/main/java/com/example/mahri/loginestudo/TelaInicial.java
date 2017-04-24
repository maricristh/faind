package com.example.mahri.loginestudo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by mahri on 07/03/2017.
 */

public class TelaInicial extends AppCompatActivity {

    private FragmentManager fragmentManager;

    Button btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);

        //parte que eu add
        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.container, new MapsFragment(), "Maps Fragment" );

        transaction.commitAllowingStateLoss();



    // fim da parte que eu add - mudei pra container no layout

        btnMenu = (Button) findViewById(R.id.btnMenu);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreMenu = new Intent(TelaInicial.this, TelaMenu.class);
                startActivity(abreMenu);
                finish();
            }
        });
    }
}