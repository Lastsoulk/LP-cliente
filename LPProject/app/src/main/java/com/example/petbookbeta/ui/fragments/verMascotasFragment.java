package com.example.petbookbeta.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petbookbeta.DataClass;
import com.example.petbookbeta.R;
import com.example.petbookbeta.clases.Animal;
import com.example.petbookbeta.ui.login.BackgroundWorker;

import java.util.ArrayList;
import java.util.List;


public class verMascotasFragment extends Fragment {//pantalla de inicio

    ListView lst;
    public static List<Animal> mascotas = new ArrayList<>();;
    int[] datosImg = {R.drawable.perro, R.drawable.gato, R.drawable.iguana};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vermascota, container, false);
        mascotas.clear();
        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
        backgroundWorker.execute("verMascotas","verMascota");
        lst = (ListView) view.findViewById(R.id.lista);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Animal sap = new Animal("El perro","4","Perro","Sas","Masculino");
        mascotas.addAll(DataClass.mascotas);
        //mascotas.add(sap);

        Animal[] arr = new Animal[mascotas.size()];
        for(Animal a:mascotas){
            System.out.println(a.getNombre());
        }
        for (int i =0; i < mascotas.size(); i++)
            arr[i] = mascotas.get(i);

        lst.setAdapter(new CustomAdapter(getActivity(),arr,datosImg));
        //System.out.println("olabb");
    }
}
