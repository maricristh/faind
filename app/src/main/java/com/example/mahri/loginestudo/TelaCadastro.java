package com.example.mahri.loginestudo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mahri on 07/03/2017.
 */

    public class TelaCadastro extends AppCompatActivity{

    EditText edtNome, edtEmail, edtSenha, editCidade;
    Button btnCancelar, btnCadastrar;

    String url= "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtSenha  = (EditText)findViewById(R.id.edtSenha);
        editCidade = (EditText)findViewById(R.id.editCidade);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrarItem);

        btnCancelar.setOnClickListener(new View.OnClickListener() {

           /* public void onClick (View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);

                startActivity();

                return;
            }
            */
            /*@Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            } */
            @Override
            public void onClick(View v) {
                Intent abreTelaInicial = new Intent(TelaCadastro.this, TelaLogin.class);
                startActivity(abreTelaInicial);
            }

        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    String nome = edtNome.getText().toString();
                    String email = edtEmail.getText().toString();
                    String senha = edtSenha.getText().toString();


                        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                            Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();

                    } else {
                            url = "http://192.168.0.106/registrar.php?nome=" + nome + "&email=" + email + "&senha=" + senha;

                            parametros = "nome=" + nome + "&email=" + email + "&senha=" + senha;
                            new SolicitaDados().execute(url);
                        }
                } else{
                    Toast.makeText(getApplicationContext(), "Nenhuma conexao encontrada", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
 //no cadastro de item parei nessa parte....
    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return Conexao.postDados(urls[0], parametros);
        }
        //onPost executa display dos resultados do AsynTask
        @Override
        protected void onPostExecute (String resultado){

            if (resultado.contains("EMAIL_ERRO")){
                Toast.makeText(getApplicationContext(), "Este email ja esta cadastrado", Toast.LENGTH_SHORT).show();

            }
            if (resultado.equals("REGISTRO_OK")){
                Toast.makeText(getApplicationContext(), "Registro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                Intent abreInicio = new Intent(TelaCadastro.this, TelaInicial.class);
                startActivity(abreInicio);

            } else{
                Toast.makeText(getApplicationContext(), "Sucesso", Toast.LENGTH_SHORT).show();
            }

        }
    }
   //protected void onPause() {finish(); }
}