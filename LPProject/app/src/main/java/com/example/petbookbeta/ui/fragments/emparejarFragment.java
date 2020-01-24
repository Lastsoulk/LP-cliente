package com.example.petbookbeta.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.petbookbeta.DataClass;
import com.example.petbookbeta.R;
import com.example.petbookbeta.clases.Animal;
import com.example.petbookbeta.ui.login.BackgroundWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class emparejarFragment extends Fragment {//pantalla de inicio

    ListView lst;
    public static List<Animal> mascotas = new ArrayList<>();
    int[] datosImg = {R.drawable.perro, R.drawable.gato, R.drawable.iguana};

    Spinner mascota;
    public static List<String>lmascotas;
    String selmascota="";

    public static String especieSel ="";
    public static String razaSel="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_emparejar, container, false);
        mascota = view.findViewById(R.id.mascota);
        lmascotas = new ArrayList<>();
        lmascotas.add("Escoga una opcion");
        BackgroundWorker bw = new BackgroundWorker(getActivity());
        bw.execute("verMascotas","comboBox");

        llenarMascotas();
        agregarAdapterMascota();
        return view;
    }

    public void agregarAdapterMascota(){
        //System.out.println(mascotaArray.size());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, lmascotas);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mascota.setAdapter(arrayAdapter);
        mascota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Escoge una opcion")){

                }else{
                    String item = parent.getItemAtPosition(position).toString();
                    String pos = String.valueOf(position);
                    //opcionMascota=pos;
                    for(Animal a:DataClass.mascotas){
                        if(a.getNombre().equals(item)){
                            BackgroundWorker bw = new BackgroundWorker(getActivity());
                            try {
                                especieSel = bw.execute("transformarEspecie",a.getEspecie()).get();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            BackgroundWorker bwe = new BackgroundWorker(getActivity());
                            try {
                                razaSel = bwe.execute("transformarRaza",a.getRaza()).get();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            selmascota = a.getId();
                            //llenar listview
                            mascotas.clear();
                            BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                            try {
                                String ss = backgroundWorker.execute("tinder",a.getId(),especieSel,razaSel,a.getGenero()).get();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            lst = (ListView) view.findViewById(R.id.lista);
                            mascotas.addAll(DataClass.tinder);
                            //mascotas.add(sap);

                            Animal[] arr = new Animal[mascotas.size()];

                            for (int i =0; i < mascotas.size(); i++)
                                arr[i] = mascotas.get(i);

                            lst.setAdapter(new CustomAdapter(getActivity(),arr,datosImg));
                        }
                    }
                    //Toast.makeText(parent.getContext(), "Selected: " + item,Toast.LENGTH_SHORT).show();


                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }

    public void llenarMascotas(){
        for(Animal a: DataClass.mascotas){
            lmascotas.add(a.getNombre());
        }
    }

}
