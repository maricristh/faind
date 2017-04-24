package com.example.mahri.loginestudo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TelaMeusItens extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ProgressDialog pDialog;

    ListView lstMeusItens;
    ItemAdapter adapter;
    ArrayList<Item> itens;

    Spinner spnListaTipo;
    private ArrayAdapter<String> adpListaTipo; //pra usar no meu spinner
    String url= "";
    String parametros = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_meus_itens);

        itens = new ArrayList<>();

        spnListaTipo = (Spinner)findViewById(R.id.spnListaTipo);
        spnListaTipo.setOnItemSelectedListener(this); //pra aparecer sem ter que clicar em ok

        lstMeusItens = (ListView)findViewById(R.id.lstMeusItens);
        adapter = new ItemAdapter(TelaMeusItens.this, R.layout.adapter_item, itens);
        lstMeusItens.setAdapter(adapter);
        lstMeusItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getItemAtPosition(position);

                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                Intent intent = new Intent(TelaMeusItens.this, TelaListaItem.class);
                intent.putExtra("item", gson.toJson(item));

                startActivity(intent);
            }
        });
        lstMeusItens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = (Item) parent.getItemAtPosition(position);

                Bundle data = new Bundle();

                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                data.putString("item", gson.toJson(item));

                carregarSpinnerOpcoes(data);

                return true;
            }
        });

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
                SharedPreferences settings = getSharedPreferences("dados", 0);
                Gson gson = new Gson();
                String sUsuario = settings.getString("usuario", null); //bocó :p bobonna
                Usuario usuario = gson.fromJson(sUsuario, Usuario.class);

                url = "http://192.168.0.106/listagemItensUsuario.php?ctipo=" +tipo +"&cidusuario=" + usuario.getId(); //falta algumas partes ainda
                parametros = "ctipo=" +tipo; //"cnome=" + cnome + "&cdescricao=" + cdescricao + "&clocal=" + clocal+ "&cfoto=" + foto + "&ctipo=" + tipo + "&cpalavrachave=" + cpalavraChave + "&ccontato=" + ccontato + "&cauxiliar=" + auxiliar;
                new SolicitaDados().execute(url);
            }
        }
    }
    // fim do codigo adicionado
    private class SolicitaDados extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            runOnUiThread(new Runnable() {
                public void run() {
                    pDialog = ProgressDialog.show(TelaMeusItens.this, "", getResources().getString(R.string.alertCarregandoDados), true);
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

    private void carregarSpinnerOpcoes(final Bundle data){
        final Dialog spinnerDialog = new Dialog(this, R.style.Base_Theme_AppCompat_Light_Dialog);
        LayoutInflater li = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vi = li.inflate(R.layout.dialog_spinner, null, false);
        spinnerDialog.setContentView(vi);
        spinnerDialog.setCancelable(true);
        TextView tituloDialog = (TextView) vi.findViewById(R.id.titulo);
        tituloDialog.setText(getResources().getString(R.string.spinnerOpcoesTitulo));
        ListView listaDialog = (ListView) vi.findViewById(R.id.lista);

        String[] itensOpcoes = new String[]{"Ver item", "Compartilhar item", "Remover item"};

        ArrayAdapter<String> listaDialogAdapter = new ArrayAdapter<String>(this, R.layout.adapter_spinner, itensOpcoes);
        listaDialog.setAdapter(listaDialogAdapter);

        listaDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcao = (String) parent.getItemAtPosition(position);
                switch (opcao){
                    case "Ver item":
                        Item item = (Item) parent.getItemAtPosition(position);

                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                        Intent intent = new Intent(TelaMeusItens.this, TelaListaItem.class);
                        intent.putExtra("item", gson.toJson(item));

                        startActivity(intent);
                        break;
                    case "Compartilhar item":

                        break;
                    case "Remover item":
                        // Chamar o arquivo php que faça o delete
                        break;
                }
                spinnerDialog.cancel();
            }
        });
    }
}