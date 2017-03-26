package com.example.mahri.loginestudo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class TelaListagemAchados extends AppCompatActivity {

    ListView lstAchados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listagem_achados);

        lstAchados = (ListView)findViewById(R.id.lstAchados);
    }
}
