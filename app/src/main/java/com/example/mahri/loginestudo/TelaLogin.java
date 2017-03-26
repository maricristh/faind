package com.example.mahri.loginestudo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class TelaLogin extends AppCompatActivity {

    private EditText edtUsuario, edtSenha;
    Button btnLogin, btnCadastre, btnEsqueceuSenha;

    String url= "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCadastre = (Button) findViewById(R.id.btnCadastre);
        btnEsqueceuSenha = (Button) findViewById(R.id.btnEsqueceuSenha);
        //o do exercicio ele fez com textView invés de botao

        //vamos criar o evento para txtcadastro lol
        //quando clica no botao, abre a outra tela

        btnCadastre.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent abreCadastro = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivity(abreCadastro);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //testo a conexão com a internet
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    String email = edtUsuario.getText().toString();
                    String senha = edtSenha.getText().toString();

                    if (email.isEmpty() || senha.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();

                    }
                    url = "http://192.168.0.106/logar.php?email=" + email + "&senha=" + senha;

                    parametros = "email=" +email+ "&senha=" +senha;
                    new SolicitaDados().execute(url);
                } else{
                    Toast.makeText(getApplicationContext(), "Nenhuma conexao encontrada", Toast.LENGTH_LONG).show();
                }
            }
        });
        }

    //realiza processamento em segundo plano
    //não comprometendo view atual
    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }
        //onPost executa display dos resultados do AsynTask
        @Override
        protected void onPostExecute (String resultado){
           //edtUsuario.setText(resultado);
            Toast.makeText(TelaLogin.this, "resultado"+ resultado, Toast.LENGTH_LONG).show();
            if (resultado.equals("LOGIN_OK")){
                Intent abreInicio = new Intent(TelaLogin.this, TelaInicial.class);
                startActivity(abreInicio);
                finish();
            } else{
                Toast.makeText(TelaLogin.this, "Usuario ou senha invalidas", Toast.LENGTH_SHORT).show();
            }
        }

        protected void onPause(){
          finish();
        }
    }
}