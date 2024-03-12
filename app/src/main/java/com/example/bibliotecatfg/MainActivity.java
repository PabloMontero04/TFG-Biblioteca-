package com.example.bibliotecatfg;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bibliotecatfg.ActivityRegistro;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity"; // Etiqueta para los logs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encuentra el botón por su ID
        Button botonregistro = findViewById(R.id.registro);

        // Establece un OnClickListener en el botón
        botonregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log para verificar si se ha hecho clic en el botón "Registro"
                Log.d(TAG, "Botón 'Registro' pulsado");

                // Crea un Intent para iniciar la ActivityRegistro
                Intent intent = new Intent(MainActivity.this, ActivityRegistro.class);

                // Inicia la ActivityRegistro
                startActivity(intent);
            }
        });
    }
}
