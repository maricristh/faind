package com.example.mahri.loginestudo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaMenu extends AppCompatActivity {

    Button btnTelaInicial, btnNovoItem ,btnEditarPerfil, btnAchados, btnMeusItens, btnSobreNos, btnAjuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_menu);

        btnTelaInicial = (Button) findViewById(R.id.btnTelaInicial);
        btnAchados = (Button) findViewById(R.id.btnAchados);
        btnMeusItens = (Button) findViewById(R.id.btnMeusItens);
        btnEditarPerfil = (Button) findViewById(R.id.btnEditarPerfil); //falta tbm
        btnSobreNos = (Button) findViewById(R.id.btnSobreNos);
        btnAjuda = (Button) findViewById(R.id.btnAjuda);
        btnNovoItem = (Button) findViewById(R.id.btnNovoItem);


        btnNovoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreCadastroItem = new Intent(TelaMenu.this, TelaCadastroItem.class);
                startActivity(abreCadastroItem);
            }

        });
        btnTelaInicial.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent abreTelaInicial = new Intent(TelaMenu.this, TelaInicial.class);
            startActivity(abreTelaInicial);
        }

    });
        btnEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreAtualizaperfil = new Intent(TelaMenu.this, TelaAtualizarPerfil.class);
                startActivity(abreAtualizaperfil);
            }
        });
        btnAchados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreListagemAchados = new Intent(TelaMenu.this, TelaListagemAchados.class);
                startActivity(abreListagemAchados);
            }

        });

        btnMeusItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreMeusItens = new Intent(TelaMenu.this, TelaMeusItens.class);
                startActivity(abreMeusItens);
            }

        });
        btnSobreNos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreSobreNos = new Intent(TelaMenu.this, ZTelaSobreNos.class);
                startActivity(abreSobreNos);
            }

        });
        btnAjuda.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent abreAjuda = new Intent(TelaMenu.this, ZTelaAjuda.class);
                startActivity(abreAjuda);
            }

        });
    }
}