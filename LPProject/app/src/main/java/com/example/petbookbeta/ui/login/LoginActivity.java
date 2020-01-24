package com.example.petbookbeta.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.petbookbeta.DataClass;
import com.example.petbookbeta.R;
import com.example.petbookbeta.RegisterAct;

public class LoginActivity extends AppCompatActivity {
    EditText txtUser, txtPasw;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUser = (EditText)findViewById(R.id.username);
        txtPasw = (EditText)findViewById(R.id.password);

    }

    public void OnLogin(View view){
        username = txtUser.getText().toString();
        String password = txtPasw.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,username,password);


    }
    public void OpenReg(View view){
        startActivity(new Intent(this, RegisterAct.class));


    }

}
