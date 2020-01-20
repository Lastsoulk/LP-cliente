package com.example.petbookbeta.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.petbookbeta.MainActivity;
import com.example.petbookbeta.ui.home.HomeFragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    public BackgroundWorker(Context ctx){
        context = ctx;
    }
    boolean logged=false;
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://192.168.100.130/LPProject/LPBackEnd/login.php";
        String register_url = "http://192.168.100.130/LPProject/LPBackEnd/register.php";
        if(type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line= "";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                    if(line.equals("Login success")){
                        LoginActivity.loggedUser = user_name;
                        logged = true;
                    }
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("register")){
            try {
                String cedula = params[1];
                String password = params[2];
                String nombre = params[3];
                String ubicacion = params[4];
                String edad = params[5];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(cedula,"UTF-8")+"&"
                    +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                    +URLEncoder.encode("nombre","UTF-8")+"="+URLEncoder.encode(nombre,"UTF-8")+"&"
                    +URLEncoder.encode("ubicacion","UTF-8")+"="+URLEncoder.encode(ubicacion,"UTF-8")+"&"
                    +URLEncoder.encode("edad","UTF-8")+"="+URLEncoder.encode(edad,"UTF-8")+"&"
                        +URLEncoder.encode("foto","UTF-8")+"="+URLEncoder.encode("","UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                    if(line.equals("Registro exitoso")){
                        logged = true;
                    }
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("consulta")){
            try {
                URL url = new URL("http://192.168.100.130/LPProject/LPBackEnd/selectGeneral.php");
                String tabla = params[1];
                String post_data = URLEncoder.encode("especie","UTF-8")+"="+URLEncoder.encode(tabla,"UTF-8");
                if(tabla.equals("raza")){
                    String especie = params[2];
                    url = new URL("http://192.168.100.130/LPProject/LPBackEnd/selectPerEspecie.php");
                    post_data = URLEncoder.encode("especie","UTF-8")+"="+URLEncoder.encode(tabla,"UTF-8")+"&"
                            +URLEncoder.encode("raza","UTF-8")+"="+URLEncoder.encode(especie,"UTF-8");
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line= "";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                String[] spo = result.split("ID: ");
                for(String s:spo){
                    String [] sks = s.split("Name: ");
                    for(int i = 1;i<sks.length;i++){
                        if(tabla.equals("raza")){
                            HomeFragment.razaArray.add(sks[i]);
                        } else if(tabla.equals("especie")) {
                            HomeFragment.especieArray.add(sks[i]);
                            //System.out.println(sks[i]);
                        }
                        i++;
                    }
                }


                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("insert")) {
            try {
                String petname = params[1];
                String edad = params[2];
                String especie = params[3];
                String raza = params[4];
                String genero = params[5];
                String foto = params[6];
                String user = params[7];

                URL url = new URL("http://192.168.100.130/LPProject/LPBackEnd/registerPet.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("petname","UTF-8")+"="+URLEncoder.encode(petname,"UTF-8")+"&"
                        +URLEncoder.encode("edad","UTF-8")+"="+URLEncoder.encode(edad,"UTF-8")+"&"
                        +URLEncoder.encode("especie","UTF-8")+"="+URLEncoder.encode(especie,"UTF-8")+"&"
                        +URLEncoder.encode("raza","UTF-8")+"="+URLEncoder.encode(raza,"UTF-8")+"&"
                        +URLEncoder.encode("genero","UTF-8")+"="+URLEncoder.encode(genero,"UTF-8")+"&"
                        +URLEncoder.encode("foto","UTF-8")+"="+URLEncoder.encode(foto,"UTF-8")+"&"
                        +URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line= "";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                    if(line.equals("Insertar exitoso")){
                        logged = true;
                    }
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {

        if (logged) {
            alertDialog.setMessage(result);
            alertDialog.show();
            Intent i = new Intent(context,MainActivity.class);
            context.startActivity(i);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

