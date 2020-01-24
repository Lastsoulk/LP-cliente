package com.example.petbookbeta.ui.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.petbookbeta.DataClass;
import com.example.petbookbeta.R;
import com.example.petbookbeta.clases.Animal;
import com.example.petbookbeta.ui.login.BackgroundWorker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class planificarSalidaFragment extends Fragment {//pantalla de inicio

    DatePickerDialog picker;
    EditText dText;

    TimePickerDialog tpicker;
    EditText tText;

    EditText ubicacion ;

    Spinner mascota;

    String nmMascota="";
    public static List<String> mascotaArray;
    public static List<Animal> AnimalArr;
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
        backgroundWorker.execute("verMascotas","comboBox");
        View view =inflater.inflate(R.layout.fragment_planificarsalida, container, false);
        ubicacion = view.findViewById(R.id.ubicacion);
        dText = view.findViewById(R.id.fechaED);
        inicializarDatePicker();

        tText = view.findViewById(R.id.horaED);
        incializarTimePicker();

        mascotaArray=new ArrayList<String>();

        AnimalArr=new ArrayList<>();

        mascotaArray.add(0,"Escoge una opcion");

        AnimalArr.addAll(DataClass.mascotas);

        btn = view.findViewById(R.id.btns8);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dText.getText().toString().equals("")&&!tText.getText().toString().equals("")&&
                        !ubicacion.getText().toString().equals("")&&!nmMascota.equals("Escoge una opcion")){
                    //System.out.println("entre");
                    BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                    backgroundWorker.execute("planificarSalida",nmMascota,dText.getText().toString(),tText.getText().toString(),ubicacion.getText().toString());

                }
                else{
                    Toast.makeText(getActivity(),"Todos los campos deben estar llenos " , Toast.LENGTH_SHORT).show();
                }
            }
        });




        mascota= view.findViewById(R.id.mascota);
        for(Animal a: AnimalArr){
           // System.out.println(a.getNombre());
            mascotaArray.add(a.getNombre());
        }

        agregarAdapterMascota();


        return view;
    }

    private void incializarTimePicker() {
        tText.setInputType(InputType.TYPE_NULL);
        tText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                tpicker = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                tText.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                tpicker.show();
            }
        });
    }

    private void inicializarDatePicker() {
        dText.setInputType(InputType.TYPE_NULL);
        dText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    public void agregarAdapterMascota(){
        //System.out.println(mascotaArray.size());
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, mascotaArray);
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
                    for(Animal a:AnimalArr){
                        if(a.getNombre().equals(item)){
                            nmMascota = a.getId();
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


}
