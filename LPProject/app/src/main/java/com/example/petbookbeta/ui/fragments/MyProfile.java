package com.example.petbookbeta.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petbookbeta.DataClass;
import com.example.petbookbeta.R;
import com.example.petbookbeta.ui.login.BackgroundWorker;

import org.w3c.dom.Text;

public class MyProfile extends Fragment {

    static TextView tvCedula;
    static EditText ednombre;
    static EditText etubi;
    static EditText etedad;
    EditText password;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_my_profile, container, false);
        tvCedula = root.findViewById(R.id.viewcedula);
        ednombre = root.findViewById(R.id.etNombre);
        etubi = root.findViewById(R.id.etUbi);
        etedad = root.findViewById(R.id.eTedad);
        password = root.findViewById(R.id.edcontra);
        Button btn = root.findViewById(R.id.btnMyProfile);

        tvCedula.setText(DataClass.loggedUser);
        ednombre.setText(DataClass.loggedName);
        etubi.setText(DataClass.logubi);
        etedad.setText(DataClass.logedad);
        BackgroundWorker brw = new BackgroundWorker(getActivity());
        brw.execute("verMascotas");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!password.getText().toString().equals("")&&!etedad.getText().toString().equals("")&&
                        !ednombre.getText().toString().equals("")&&!etubi.getText().toString().equals("")){
                BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                backgroundWorker.execute("actualizarDatos",password.getText().toString(),ednombre.getText().toString(),etubi.getText().toString(),etedad.getText().toString());

                }
            else{
                Toast.makeText(getActivity(),"Todos los campos deben estar llenos " , Toast.LENGTH_SHORT).show();
            }
            }
        });
        return root;
    }




}
