package com.example.mahri.loginestudo;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/*
Tem que alterar o banco pra foto
Ver com o caracter
mudar o tipo de entrada na tabela contato
alterei a nulidade de alguns itens pra testar - tipo a foto e tipo - depois tem que arrumar isso
 */
public class TelaCadastroItem extends AppCompatActivity {

    Button btnCadastrarItem, btnFoto;
    EditText edtNome, edtDescricao, edtLocal, edtPalavraChave, edtContato;
    EditText tipo, auxiliar;
    Image foto;
    Spinner spnTipoItem;
    private ArrayAdapter<String> adpTipoItem; //pra usar no meu spinner
    String url= "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_item);

        btnCadastrarItem = (Button) findViewById(R.id.btnCadastrarItem);

        spnTipoItem = (Spinner)findViewById(R.id.spnTipoItem);
        edtNome = (EditText) findViewById (R.id.edtNome);
        edtDescricao = (EditText) findViewById (R.id.edtDescricao);
        edtLocal = (EditText) findViewById (R.id.edtLocal);
        edtPalavraChave = (EditText) findViewById (R.id.edtPalavraChave);
        edtContato= (EditText) findViewById (R.id.edtContato);


        adpTipoItem = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item); //this especifica referencia do meu activity
        adpTipoItem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipoItem.setAdapter(adpTipoItem); //estou vinculanddo o arrayadpter (adpOpcoes) ao meu Spinner (spnOpcoes), deve acontecer com cada spinner/array adapter

        adpTipoItem.add("Achado");
        adpTipoItem.add("Perdido");

        String tipo = "" + spnTipoItem.getSelectedItem();

        btnCadastrarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {

                    String cnome = edtNome.getText().toString();
                    String cdescricao = edtDescricao.getText().toString();
                    String clocal = edtLocal.getText().toString();
                    String cpalavraChave = edtPalavraChave.getText().toString();
                    String ccontato = edtContato.getText().toString();
                    String tipo = "" + spnTipoItem.getSelectedItem();

                    if (cnome.isEmpty() || cdescricao.isEmpty() || clocal.isEmpty() || cpalavraChave.isEmpty() || ccontato.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Nenhum campo pode estar vazio", Toast.LENGTH_LONG).show();

                    } else {
                        url = "http://192.168.0.106/registraritem.php?cnome=" + cnome + "&cdescricao=" + cdescricao + "&clocal=" + clocal+ "&cfoto=" + foto + "&ctipo=" + tipo + "&cpalavrachave=" + cpalavraChave + "&ccontato=" + ccontato + "&cauxiliar=" + auxiliar; // faltando foto...

                        parametros = "cnome=" + cnome + "&cdescricao=" + cdescricao + "&clocal=" + clocal+ "&cfoto=" + foto + "&ctipo=" + tipo + "&cpalavrachave=" + cpalavraChave + "&ccontato=" + ccontato + "&cauxiliar=" + auxiliar;
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

   /*         if (resultado.contains("EMAIL_ERRO")){
                Toast.makeText(getApplicationContext(), "Este email ja esta cadastrado", Toast.LENGTH_SHORT).show();

            } nao preciso desse teste agr*/
            if (resultado.equals("REGISTRO_OK")){
                Toast.makeText(getApplicationContext(), "Registro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                Intent abreInicio = new Intent(TelaCadastroItem.this, TelaInicial.class); //depois tenho que mudar
                startActivity(abreInicio);

            } else{
                Toast.makeText(getApplicationContext(), "Mensagem" +resultado, Toast.LENGTH_SHORT).show();
            }

        }
    }
    //protected void onPause() {finish(); }
}