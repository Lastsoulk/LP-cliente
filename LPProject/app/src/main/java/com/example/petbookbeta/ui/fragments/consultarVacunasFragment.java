package com.example.petbookbeta.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.petbookbeta.DataClass;
import com.example.petbookbeta.R;
import com.example.petbookbeta.clases.vacunas;
import com.example.petbookbeta.ui.login.BackgroundWorker;

import java.util.ArrayList;
import java.util.List;


public class consultarVacunasFragment extends Fragment {//pantalla de inicio

    TextView tid;
    TextView nombre;
    TextView descripcion;
    ImageView imw;
    Spinner vacuna;
    public static List<String> lvacunas ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_consultarvacunas, container, false);
        lvacunas=new ArrayList<String>();
        lvacunas.add("Escoge una opcion");
        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
        backgroundWorker.execute("vacunas","vacunas");
        imw=view.findViewById(R.id.iwVacuna);
        tid = view.findViewById(R.id.txidVacuna);
        nombre = view.findViewById(R.id.txnombreVacuna);
        descripcion = view.findViewById(R.id.txdescripcionVacuna);
        vacuna = view.findViewById(R.id.spinner2);


        agregarAdapterEspecie();
        return view;
    }


    public void agregarAdapterEspecie(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, lvacunas);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        vacuna.setAdapter(arrayAdapter);
        vacuna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Escoge una opcion")){

                }else{
                    String item = parent.getItemAtPosition(position).toString();
                    String pos = String.valueOf(position);
                    for(vacunas v: DataClass.vacunas){
                        if(v.getNombre().equals(item)){
                            imw.setImageResource(R.drawable.vacuna);
                            tid.setText(v.getId());
                            nombre.setText(v.getNombre());
                            descripcion.setText(v.getDescripion());
                        }
                    }

                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }

}
