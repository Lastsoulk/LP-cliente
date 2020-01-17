package com.example.lpproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText txtUser, txtPasw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUser = (EditText)findViewById(R.id.txtUser);
        txtPasw = (EditText)findViewById(R.id.txtPassw);
    }
    public void OnLogin(View view){
        String username = txtUser.getText().toString();
        String password = txtPasw.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,username,password);
    }
}
