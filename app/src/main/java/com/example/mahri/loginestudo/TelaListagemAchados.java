package com.example.mahri.loginestudo;
/*
Tentando fazer uma listagem somente - daí depeendendo da selecao no Spinner ele exibe
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TelaListagemAchados extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ProgressDialog pDialog;

    ListView lstAchados;
    ItemAdapter adapter;
    ArrayList<Item> itens;

    Spinner spnListaTipo;
    private ArrayAdapter<String> adpListaTipo; //pra usar no meu spinner
    String url= "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_listagem_achados);

        itens = new ArrayList<>();

        spnListaTipo = (Spinner)findViewById(R.id.spnListaTipo);
        spnListaTipo.setOnItemSelectedListener(this); //pra aparecer sem ter que clicar em ok

        lstAchados = (ListView)findViewById(R.id.lstAchados);
        adapter = new ItemAdapter(TelaListagemAchados.this, R.layout.adapter_item, itens);
        lstAchados.setAdapter(adapter);

        adpListaTipo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item); //this especifica referencia do meu activity
        adpListaTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnListaTipo.setAdapter(adpListaTipo); //estou vinculanddo o arrayadpter (adpOpcoes) ao meu Spinner (spnOpcoes), deve acontecer com cada spinner/array adapter

        adpListaTipo.add("Achado");
        adpListaTipo.add("Perdido");

        String tipo = "" + spnListaTipo.getSelectedItem(); //pega o valor referente ao tipo selecionado
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String item = parent.getItemAtPosition(position).toString();
        //mostrando item selecionado
        Toast.makeText(parent.getContext(), "Selecionado: "+item, Toast.LENGTH_SHORT).show();
        //código quando selecionado o item no spinner -- alterei em 10-04
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String tipo = "" + spnListaTipo.getSelectedItem();
            if (tipo.isEmpty()){
                Toast.makeText(getApplicationContext(), "Nenhum dado selecionado ainda", Toast.LENGTH_LONG).show();
            } else{
                url = "http://192.168.0.106/listagemachados.php?ctipo=" +tipo; //falta algumas partes ainda
                parametros = "ctipo=" +tipo; //"cnome=" + cnome + "&cdescricao=" + cdescricao + "&clocal=" + clocal+ "&cfoto=" + foto + "&ctipo=" + tipo + "&cpalavrachave=" + cpalavraChave + "&ccontato=" + ccontato + "&cauxiliar=" + auxiliar;
                new TelaListagemAchados.SolicitaDados().execute(url);
            }
        }
    }

    // fim do codigo adicionado
    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            runOnUiThread(new Runnable() {
                public void run() {
                    pDialog = ProgressDialog.show(TelaListagemAchados.this, "", getResources().getString(R.string.alertCarregandoDados), true);
                }
            });
        }

        @Override
        protected String doInBackground(String... urls) {
            return Conexao.postDados(urls[0], parametros);
        }
        //onPost executa display dos resultados do AsynTask
        @Override
        protected void onPostExecute (String resultado){
            runOnUiThread(new Runnable() {
                public void run() {
                    pDialog.dismiss();
                }
            });

            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            Type type = new TypeToken<ArrayList<Item>>() {}.getType();
            itens = gson.fromJson(resultado, type);

            adapter.clear();
            adapter.addAll(itens);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}