package com.example.bibliotecatfg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
public class MenuPrincipal extends AppCompatActivity {

    private FragmentUno fragmentUno;
    private FragmentDos fragmentDos;
    private FragmentTres fragmentTres;
    private FragmentCuatro fragmentCuatro;
    private FragmentCinco fragmentCinco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);

        // Inicializar los fragmentos
        fragmentUno = new FragmentUno();
        fragmentDos = new FragmentDos();
        fragmentTres = new FragmentTres();
        fragmentCuatro = new FragmentCuatro();
        fragmentCinco = new FragmentCinco();

        // Cargar el FragmentUno por defecto
        loadFragment(fragmentUno);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.uno) {
                    loadFragment(fragmentUno);
                    return true;
                } else if (itemId == R.id.dos) {
                    loadFragment(fragmentDos);
                    return true;
                } else if (itemId == R.id.tres) {
                    loadFragment(fragmentTres);
                    return true;
                } else if (itemId == R.id.cuatro) {
                    loadFragment(fragmentCuatro);
                    return true;
                } else if (itemId == R.id.cinco) {
                    loadFragment(fragmentCinco);
                    return true;
                }
                return false;
            }
        });
    }

        // Método para cargar un fragmento en el contenedor
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
