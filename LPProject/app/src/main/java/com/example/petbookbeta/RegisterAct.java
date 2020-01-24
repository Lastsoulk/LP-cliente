package com.example.petbookbeta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petbookbeta.ui.login.BackgroundWorker;

import java.io.IOException;
import java.util.UUID;
import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

public class RegisterAct extends AppCompatActivity  {
    EditText cedula, contra, nombre, ubicacion, edad;
    String str_cedula, str_contra, str_nombre, str_ubicacion, str_edad;
    private static final int IMAGE_REQUEST_CODE = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    private ImageView imageView;
    private EditText etCaption;
    private TextView tvPath;
    private Button btnUpload;
    private Bitmap bitmap;
    private Uri filePath;
    //private static final String UPLOAD_URL = "http://192.168.1.2:8080/android_upload/insert_image.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cedula = findViewById(R.id.cedula);
        contra = findViewById(R.id.password);
        nombre = findViewById(R.id.nombre);
        ubicacion = findViewById(R.id.ubicacion);
        edad = findViewById(R.id.edad);
        imageView = (ImageView) findViewById(R.id.IdProf);
        btnUpload = findViewById(R.id.UploadBtn);
        /*imageView.setOnClickListener(this);
        btnUpload.setOnClickListener(this);*/
    }

    public void OnReg(View view) {
        str_cedula = cedula.getText().toString();
        str_contra = contra.getText().toString();
        str_nombre = nombre.getText().toString();
        str_ubicacion = ubicacion.getText().toString();
        str_edad = edad.getText().toString();

        String type = "register";
        boolean val = (!str_cedula.equals(""))&&(!str_contra.equals(""))&&(!str_nombre.equals(""))&&
                (!str_ubicacion.equals(""))&&(!str_edad.equals(""));
        if(val) {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, str_cedula, str_contra, str_nombre, str_ubicacion, str_edad);
        }else if(!val){
            Toast.makeText(this,"Todos los campos deben estar llenos " , Toast.LENGTH_SHORT).show();
        }
    }



}
