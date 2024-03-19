package com.example.bibliotecatfg;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuPrincipal extends AppCompatActivity {

    private FragmentMenuPrincipal fragmentMenuPrincipal;
    private FragmentDos fragmentDos;
    private FragmentTres fragmentTres;
    private FragmentCuatro fragmentCuatro;
    private FragmentCinco fragmentCinco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);

        // Inicializar los fragmentos
        fragmentMenuPrincipal = new FragmentMenuPrincipal();
        fragmentDos = new FragmentDos();
        fragmentTres = new FragmentTres();
        fragmentCuatro = new FragmentCuatro();
        fragmentCinco = new FragmentCinco();

        // Cargar el FragmentUno por defecto
        loadFragment(fragmentMenuPrincipal);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.uno) {
                    loadFragment(fragmentMenuPrincipal);
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
