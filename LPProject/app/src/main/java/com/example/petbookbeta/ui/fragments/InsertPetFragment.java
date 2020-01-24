package com.example.petbookbeta.ui.fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.petbookbeta.DataClass;
import com.example.petbookbeta.R;
import com.example.petbookbeta.ui.login.BackgroundWorker;

import java.util.ArrayList;
import java.util.List;

public class InsertPetFragment extends Fragment {

    public static List<String> razaArray;
    public static List<String> especieArray;
    Spinner raza;
    Spinner sexo;
    Spinner especie;
    Button btn;
    EditText name;
    EditText edad;

    private String opcionRaza;
    private String opcionEspecie;
    private String opcionSexo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        View view =  inflater.inflate(R.layout.fragment_insertpet, container, false);
        razaArray=new ArrayList<String>();
        especieArray=new ArrayList<String>();
        especieArray.add(0,"Escoge una opcion");
        raza = view.findViewById(R.id.raza);
        sexo = view.findViewById(R.id.sexo);
        btn = view.findViewById(R.id.btnMascota);
        name = view.findViewById(R.id.nombre);
        edad = view.findViewById(R.id.edad);

        especie = view.findViewById(R.id.especie);
        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
        backgroundWorker.execute("consulta","especie");

        agregarAdapterEspecie();
        agregarAdapterGenero();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                boolean validar = (!name.getText().toString().equals(""))&&(!edad.getText().toString().equals("")&&
                        (!opcionEspecie.equals("Escoge una opcion"))&&(!opcionRaza.equals("Escoge una opcion")));
                if(validar){
                BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                backgroundWorker.execute("insert",name.getText().toString(),edad.getText().toString(),opcionEspecie,opcionRaza,opcionSexo,"", DataClass.loggedUser);
                }else{
                    Toast.makeText(getActivity(),"Todos los campos deben estar llenos y sin 'Escoge una opcion'" , Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e){
                    Toast.makeText(getActivity(),"Todos los campos deben estar llenos y sin 'Escoge una opcion'" , Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    public void agregarAdapterEspecie(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, especieArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        especie.setAdapter(arrayAdapter);
        especie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Escoge una opcion")){

                }else{
                    String item = parent.getItemAtPosition(position).toString();
                    String pos = String.valueOf(position);
                    opcionEspecie=pos;
                    //Toast.makeText(parent.getContext(), "Selected: " + item,Toast.LENGTH_SHORT).show();
                    razaArray.clear();
                    razaArray.add(0,"Escoge una opcion");
                    BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                    backgroundWorker.execute("consulta","raza",pos);
                    agregarAdapterRaza();
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }
    public void agregarAdapterRaza(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, razaArray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        raza.setAdapter(arrayAdapter);
        raza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Escoge una opcion")){

                }else{
                    String item = parent.getItemAtPosition(position).toString();
                    String pos = String.valueOf(position);
                    opcionRaza=pos;
                    //Toast.makeText(parent.getContext(), "Selected: " + item,Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }
    public void agregarAdapterGenero(){
        String[] stl = {"Macho","Hembra"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, stl);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sexo.setAdapter(arrayAdapter);
        sexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Escoge una opcion")){

                }else{
                    String item = parent.getItemAtPosition(position).toString();
                    opcionSexo=item;
                    //Toast.makeText(parent.getContext(), "Selected: " + item,Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }

}