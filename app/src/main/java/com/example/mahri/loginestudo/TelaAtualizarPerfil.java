package com.example.mahri.loginestudo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TelaAtualizarPerfil extends AppCompatActivity {

    Button btnCancelar, btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_atualizar_perfil);

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnOk = (Button) findViewById(R.id.btnOk);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abreMenu = new Intent(TelaAtualizarPerfil.this, TelaMenu.class);
                startActivity(abreMenu);
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TelaAtualizarPerfil.this, "Função ainda sendo trabalhada, atualização em breve...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
