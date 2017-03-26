package com.example.mahri.loginestudo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.sql.Connection;

/**
 * Created by mahri on 07/03/2017.
 */

public class Conexao {
    public static String postDados(String urlUsuario, String parametrosUsuario){
        URL url;
        HttpURLConnection connection = null;

        try{
            Log.i("conexao", urlUsuario);

            url = new URL(urlUsuario);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET"); //método post
            //conteudo que vai ser passado - tipo
            connection.setRequestProperty("Content-Type","aplication/x-www-form-urlenconded");
            //passa pra String
            connection.setRequestProperty("Content-lenght", String.valueOf(parametrosUsuario.getBytes().length));
            connection.setRequestProperty("Content-Language","pt-BR");
            //desativando o cache
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //onde armazeno os dados da saída - conexao
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(parametrosUsuario);
            dataOutputStream.flush();
            dataOutputStream.close();

            //obtendo as informacoes
            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String linha;
            StringBuffer resposta = new StringBuffer();

            while ((linha = bufferedReader.readLine()) != null) {
                resposta.append(linha);
            }
            bufferedReader.close();

            Log.i("conexao", resposta.toString());
            return resposta.toString();

        }catch (Exception erro){
            return null;
        } finally {
            if(connection !=null){
                connection.disconnect();
            }

        }
    }
}
