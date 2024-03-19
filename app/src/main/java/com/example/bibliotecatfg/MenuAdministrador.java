package com.example.bibliotecatfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuAdministrador extends AppCompatActivity {
    private FragmentLibrosAdmin librosAdmin;
    private FragmentUsuarioAdmin usuarioAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);

        // Inicializar los fragmentos
        librosAdmin = new FragmentLibrosAdmin();
        usuarioAdmin = new FragmentUsuarioAdmin();

        // Cargar el FragmentUno por defecto
        loadFragment(librosAdmin);

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_administrador);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.Libros) {
                    loadFragment(librosAdmin);
                    return true;
                } else if (itemId == R.id.Usuarios) {
                    loadFragment(usuarioAdmin);
                    return true;
                } else if (itemId == R.id.Salir) {
                    return true;
                }
                return false;
            }
        });
    }

    // Método para cargar un fragmento en el contenedor
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
