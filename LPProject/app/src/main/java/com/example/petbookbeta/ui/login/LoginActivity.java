package com.example.petbookbeta.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petbookbeta.MainActivity;
import com.example.petbookbeta.R;
import com.example.petbookbeta.RegisterAct;

public class LoginActivity extends AppCompatActivity {
    public static String loggedUser;
    EditText txtUser, txtPasw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUser = (EditText)findViewById(R.id.username);
        txtPasw = (EditText)findViewById(R.id.password);
    }
    public void OnLogin(View view){
        String username = txtUser.getText().toString();
        String password = txtPasw.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,username,password);

    }
    public void OpenReg(View view){
        startActivity(new Intent(this, RegisterAct.class));
    }
}
