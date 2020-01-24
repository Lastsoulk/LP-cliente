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
import com.example.petbookbeta.clases.Doctor;
import com.example.petbookbeta.clases.vacunas;
import com.example.petbookbeta.ui.login.BackgroundWorker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.petbookbeta.ui.fragments.planificarSalidaFragment.mascotaArray;


public class planificarVacunasFragment extends Fragment {//pantalla de inicio
    Spinner vacuna;
    public static List<String> lvacunas ;
    String selvacunas="";

    Spinner mascota;
    public static List<String>lmascotas;
    String selmascota="";

    Spinner doctor;
    public static List<String>ldoctor;
    String seldoctor="";

    Spinner veterinaria;
    public static List<String>lveterinaria;
    String selveterinaria="";

    DatePickerDialog picker;
    EditText dText;

    TimePickerDialog tpicker;
    EditText tText;

    Button bts;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_planificarvacunas, container, false);
        dText = view.findViewById(R.id.etDate);
        tText = view.findViewById(R.id.etDate2);
        inicializarDatePicker();
        incializarTimePicker();
        bts= view.findViewById(R.id.button2);
        lvacunas=new ArrayList<String>();
        lvacunas.add("Escoge una opcion");
        lmascotas=new ArrayList<String>();
        lmascotas.add("Escoge una opcion");
        lveterinaria=new ArrayList<String>();
        lveterinaria.add("Escoge una opcion");
        ldoctor=new ArrayList<String>();
        ldoctor.add("Escoge una opcion");
        BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());//select vacunas
        backgroundWorker.execute("vacunas","planificar");
        BackgroundWorker bw = new BackgroundWorker(getActivity());
        bw.execute("verMascotas","comboBox");
        BackgroundWorker vw = new BackgroundWorker(getActivity());
        vw.execute("veterinaria","veterinaria");

        vacuna = view.findViewById(R.id.vacuna);
        mascota = view.findViewById(R.id.mascota);
        veterinaria = view.findViewById(R.id.doctor);
        doctor = view.findViewById(R.id.veterinaria);

        llenarMascotas();
        agregarAdapterMascota();
        agregarAdapterVacuna();
        agregarAdapterVet();
        bts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!dText.getText().toString().equals("")&&!tText.getText().toString().equals("")&&
                        !selvacunas.equals("Escoge una opcion")&&!selvacunas.equals("")&&!seldoctor.equals("Escoge una opcion")&&
                !seldoctor.equals("")&&!selmascota.equals("Escoge una opcion")&&!selmascota.equals("")&&
                        !selveterinaria.equals("Escoge una opcion")&&!selveterinaria.equals("")){
                    //System.out.println("entre");
                    BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                    backgroundWorker.execute("planificarVacunas",selveterinaria,selvacunas,selmascota,seldoctor,dText.getText().toString(),tText.getText().toString());

                }
                else{
                    Toast.makeText(getActivity(),"Todos los campos deben estar llenos " , Toast.LENGTH_SHORT).show();
                }
            }
        });


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


    public void agregarAdapterVet(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, lveterinaria);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        veterinaria.setAdapter(arrayAdapter);
        veterinaria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Escoge una opcion")){

                }else{
                    //String item = parent.getItemAtPosition(position).toString();
                    String pos = String.valueOf(position);
                    selveterinaria=pos;
                    //Toast.makeText(parent.getContext(), "Selected: " + item,Toast.LENGTH_SHORT).show();
                    ldoctor.clear();
                    ldoctor.add(0,"Escoge una opcion");
                    BackgroundWorker vw = new BackgroundWorker(getActivity());
                    vw.execute("veterinaria",pos);
                    agregarAdapterDoc();
                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }

    public void agregarAdapterDoc(){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, ldoctor);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        doctor.setAdapter(arrayAdapter);
        doctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Escoge una opcion")){

                }else{
                    String item = parent.getItemAtPosition(position).toString();
                    for(Doctor d: DataClass.doctores){
                        if(d.getNombre().equals(item)){
                            seldoctor=d.getCedula();
                        }
                    }

                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });
    }


    public void llenarMascotas(){
        for(Animal a:DataClass.mascotas){
            lmascotas.add(a.getNombre());
        }
    }
    public void agregarAdapterVacuna(){
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
                            selvacunas = pos;
                        }
                    }

                }
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

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
                            selmascota = a.getId();
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
