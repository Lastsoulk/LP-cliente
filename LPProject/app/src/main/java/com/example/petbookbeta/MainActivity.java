package com.example.petbookbeta;

import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.petbookbeta.ui.fragments.InsertPetFragment;
import com.example.petbookbeta.ui.fragments.MyProfile;
import com.example.petbookbeta.ui.fragments.consultarVacunasFragment;
import com.example.petbookbeta.ui.fragments.emparejarFragment;
import com.example.petbookbeta.ui.fragments.homeFragment;
import com.example.petbookbeta.ui.fragments.planificarSalidaFragment;
import com.example.petbookbeta.ui.fragments.planificarVacunasFragment;
import com.example.petbookbeta.ui.fragments.verMascotasFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    new MyProfile()).commit();
            navigationView.setCheckedItem(R.id.nav_myprofile);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new homeFragment()).commit();
                break;
            case R.id.nav_vermascotas:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new verMascotasFragment()).commit();
                break;
            case R.id.nav_insertpet:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new InsertPetFragment()).commit();
                break;
            case R.id.nav_planificarsalida:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new planificarSalidaFragment()).commit();
                break;
            case R.id.nav_emparejarmascotas:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new emparejarFragment()).commit();
                break;
            case R.id.nav_consultarvacunas:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new consultarVacunasFragment()).commit();
                break;
            case R.id.nav_planificarvacunas:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new planificarVacunasFragment()).commit();
                break;
            case R.id.nav_myprofile:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                        new MyProfile()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
