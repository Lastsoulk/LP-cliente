package com.example.petbookbeta.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.petbookbeta.DataClass;
import com.example.petbookbeta.MainActivity;
import com.example.petbookbeta.ui.fragments.Animal;
import com.example.petbookbeta.ui.fragments.InsertPetFragment;
import com.example.petbookbeta.ui.fragments.planificarSalidaFragment;
import com.example.petbookbeta.ui.fragments.verMascotasFragment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    public BackgroundWorker(Context ctx){
        context = ctx;
    }
    boolean logged=false;
    boolean login = false;
    @Override
    protected String doInBackground(String... params) {
        String result = "";
        String type = params[0];
        String login_url = "http://"+DataClass.ip_add+"/LPProject/LPBackEnd/login.php";
        String register_url = "http://"+DataClass.ip_add+"/LPProject/LPBackEnd/register.php";
        try{
        if(type.equals("login")) {//login basico
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);

                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                result = executePHP(post_data,url);
            logged = true;
                if(result.equals("\tLogin success")){
                        DataClass.loggedUser = user_name;

                        login=true;
                }

        }else if(type.equals("register")){//registrar usuario
                String cedula = params[1];
                String password = params[2];
                String nombre = params[3];
                String ubicacion = params[4];
                String edad = params[5];
                URL url = new URL(register_url);
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(cedula,"UTF-8")+"&"
                    +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                    +URLEncoder.encode("nombre","UTF-8")+"="+URLEncoder.encode(nombre,"UTF-8")+"&"
                    +URLEncoder.encode("ubicacion","UTF-8")+"="+URLEncoder.encode(ubicacion,"UTF-8")+"&"
                    +URLEncoder.encode("edad","UTF-8")+"="+URLEncoder.encode(edad,"UTF-8")+"&"
                        +URLEncoder.encode("foto","UTF-8")+"="+URLEncoder.encode("","UTF-8");
                result = executePHP(post_data,url);
                if(result.equals("\tRegistro exitoso")){
                        logged = true;
                    }
        }else if(type.equals("consulta")){//consultar especies

                URL url = new URL("http://"+DataClass.ip_add+"/LPProject/LPBackEnd/selectGeneral.php");
                String tabla = params[1];
                String post_data = URLEncoder.encode("especie","UTF-8")+"="+URLEncoder.encode(tabla,"UTF-8");
                if(tabla.equals("raza")){
                    String especie = params[2];
                    url = new URL("http://"+DataClass.ip_add+"/LPProject/LPBackEnd/selectPerEspecie.php");
                    post_data = URLEncoder.encode("especie","UTF-8")+"="+URLEncoder.encode(tabla,"UTF-8")+"&"
                            +URLEncoder.encode("raza","UTF-8")+"="+URLEncoder.encode(especie,"UTF-8");
                }
                result = executePHP(post_data,url);
                String[] spo = result.split("ID: ");
                for(String s:spo){
                    String [] sks = s.split("Name: ");
                    for(int i = 1;i<sks.length;i++){
                        if(tabla.equals("raza")){
                            InsertPetFragment.razaArray.add(sks[i]);
                        } else if(tabla.equals("especie")) {
                            InsertPetFragment.especieArray.add(sks[i]);
                            //System.out.println(sks[i]);
                        }
                        i++;
                    }
                }

        }else if(type.equals("insert")) {//insert pet
                String petname = params[1];
                String edad = params[2];
                String especie = params[3];
                String raza = params[4];
                String genero = params[5];
                String foto = params[6];
                String user = params[7];
                URL url = new URL("http://"+DataClass.ip_add+"/LPProject/LPBackEnd/registerPet.php");
                String post_data = URLEncoder.encode("petname","UTF-8")+"="+URLEncoder.encode(petname,"UTF-8")+"&"
                        +URLEncoder.encode("edad","UTF-8")+"="+URLEncoder.encode(edad,"UTF-8")+"&"
                        +URLEncoder.encode("especie","UTF-8")+"="+URLEncoder.encode(especie,"UTF-8")+"&"
                        +URLEncoder.encode("raza","UTF-8")+"="+URLEncoder.encode(raza,"UTF-8")+"&"
                        +URLEncoder.encode("genero","UTF-8")+"="+URLEncoder.encode(genero,"UTF-8")+"&"
                        +URLEncoder.encode("foto","UTF-8")+"="+URLEncoder.encode(foto,"UTF-8")+"&"
                        +URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user,"UTF-8");
                result = executePHP(post_data,url);
                if(result.equals("\tInsertar exitoso")){
                        logged = true;
                }
                return result;
        }
        else if(type.equals("obtenerDatos")) {//obtener datos usuario, pantalla principal

                String cedula = params[1];
                URL url = new URL("http://"+DataClass.ip_add+"/LPProject/LPBackEnd/obtenerDatosUser.php");
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(cedula,"UTF-8");
                result = executePHP(post_data,url);
                String[] sts = result.split(",");
                DataClass.loggedName = sts[1];
                DataClass.logubi = sts[2];
                DataClass.logedad = sts[3];

        }
        else if(type.equals("actualizarDatos")) {//actualizarDatos
            String password = params[1];
            String nombre = params[2];
            String ubicacion = params[3];
            String edad = params[4];
            String cedula = DataClass.loggedUser;
            URL url = new URL("http://"+DataClass.ip_add+"/LPProject/LPBackEnd/actualizarDatosUser.php");
            String post_data = URLEncoder.encode("cedula","UTF-8")+"="+URLEncoder.encode(cedula,"UTF-8")+"&"
                    +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                    +URLEncoder.encode("nombre","UTF-8")+"="+URLEncoder.encode(nombre,"UTF-8")+"&"
                    +URLEncoder.encode("ubicacion","UTF-8")+"="+URLEncoder.encode(ubicacion,"UTF-8")+"&"
                    +URLEncoder.encode("edad","UTF-8")+"="+URLEncoder.encode(edad,"UTF-8");
            result = executePHP(post_data,url);
            if(result.equals("\tCambio de datos exitoso")){
                logged=true;
            }
        }
        else if(type.equals("verMascotas")) {//verMascotas
            String cedula = DataClass.loggedUser;
            //String tipo = params[1];
            URL url = new URL("http://"+DataClass.ip_add+"/LPProject/LPBackEnd/searchPetFromUser.php");
            String post_data = URLEncoder.encode("cedula","UTF-8")+"="+URLEncoder.encode(cedula,"UTF-8");
            result = executePHP(post_data,url);
            String [] sop = result.split(",");
            DataClass.mascotas.clear();
            for(int i=0;i<=sop.length-1;i=i+6){

                    DataClass.mascotas.add(new Animal(sop[i],sop[i+1],sop[i+2],sop[i+3],sop[i+4],sop[i+5]));
            }
        }
        else if(type.equals("planificarSalida")) {//planificar salidas
            String cedula = DataClass.loggedUser;
            String mascota = params[1];
            String fecha=params[2];
            String hora = params[3];
            String ubicacion=params[4];
            URL url = new URL("http://"+DataClass.ip_add+"/LPProject/LPBackEnd/PlanificarSalidas.php");
            String post_data = URLEncoder.encode("mascota","UTF-8")+"="+URLEncoder.encode(mascota,"UTF-8")+"&"
                    +URLEncoder.encode("usuario","UTF-8")+"="+URLEncoder.encode(cedula,"UTF-8")+"&"
                    +URLEncoder.encode("fecha","UTF-8")+"="+URLEncoder.encode(fecha,"UTF-8")+"&"
                    +URLEncoder.encode("hora","UTF-8")+"="+URLEncoder.encode(hora,"UTF-8")+"&"
                    +URLEncoder.encode("ubicacion","UTF-8")+"="+URLEncoder.encode(ubicacion,"UTF-8");
            result = executePHP(post_data,url);
            if(result.equals("\tSalida planificada")){
                logged = true;
            }
        }
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return result;
    }





    protected String executePHP(String post_data,URL url){
        String result="";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
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

            String line= "";
            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }




    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    @Override
    protected void onPostExecute(String result) {

        if (logged) {
            alertDialog.setMessage(result);
            alertDialog.show();
            if (login) {
                BackgroundWorker bw = new BackgroundWorker(context);
                bw.execute("obtenerDatos", DataClass.loggedUser);
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

