package com.example.petbookbeta.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petbookbeta.R;
import com.example.petbookbeta.clases.Animal;

public class CustomAdapter extends BaseAdapter {

    /**/

    private static LayoutInflater inflater = null;

    Context context;
    Animal[] datosobjeto;
    int[] datosImg;


    public CustomAdapter(Context fragment, Animal[] datosobjeto, int[]datosImg) {
        this.context=fragment;
        this.datosobjeto=datosobjeto;
        this.datosImg=datosImg;

    }



    @Override
    public View getView(int i, View view,  ViewGroup viewgroup) {
        inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        final View vista = inflater.inflate(R.layout.listview_layout, null);
        System.out.println("adaptador creado");
        TextView texto1 = (TextView) vista.findViewById(R.id.nombre);
        TextView texto2 = (TextView) vista.findViewById(R.id.edadlv);
        TextView raza = vista.findViewById(R.id.razalv);
        TextView genero = vista.findViewById(R.id.generolv);
        TextView especie = vista.findViewById(R.id.especielv);
        ImageView imagen = (ImageView) vista.findViewById(R.id.lvimagen);
        texto1.setText(datosobjeto[i].getNombre());
        texto2.setText(datosobjeto[i].getEdad());
        raza.setText(datosobjeto[i].getRaza());
        genero.setText(datosobjeto[i].getGenero());
        especie.setText(datosobjeto[i].getEspecie());
        if(datosobjeto[i].getEspecie().equals("Perro")){
            imagen.setImageResource(datosImg[0]);
        }
        if(datosobjeto[i].getEspecie().equals("Gato")){
            imagen.setImageResource(datosImg[1]);
        }
        System.out.println("Si entre");
        imagen.setTag(i);
        //El listener para hacer lo que se quiera xdxd
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("le di click!!");
            }
        });
        return vista;
    }



    @Override
    public int getCount() {
        return datosobjeto.length;
    }

    @Override
    public Animal getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
