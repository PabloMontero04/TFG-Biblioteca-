package com.example.bibliotecatfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String PREF_USERNAME = "username";

    EditText editTextUsuario, editTextContrasenia;
    Button botonInicioSesion, botonRegistro, botonInvitado;
    private DbHelper dbHelper;
    boolean esAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar el dbHelper
        dbHelper = new DbHelper(this);

        editTextUsuario = findViewById(R.id.editTextText);
        editTextContrasenia = findViewById(R.id.editTextTextPassword);
        botonInicioSesion = findViewById(R.id.botonInicioSesion);
        botonRegistro = findViewById(R.id.registro);
        botonInvitado = findViewById(R.id.invitado);


        botonInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivityRegistro();
            }
        });

        botonInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesionComoInvitado();
            }
        });
    }




    private void iniciarSesion() {
        String usuario = editTextUsuario.getText().toString();
        String contrasenia = editTextContrasenia.getText().toString();

        // Verificar si el usuario y la contraseña no están vacíos
        if (!usuario.isEmpty() && !contrasenia.isEmpty()) {
            // Acceder a la base de datos para verificar las credenciales del usuario
            if (verificarCredenciales(usuario, contrasenia)) {
                // Si las credenciales son correctas, determinar la actividad a abrir
                Intent intent;
                if (esAdmin) {
                    // Si el usuario es administrador, llevarlo a la actividad de administrador
                    intent = new Intent(MainActivity.this, MenuAdministrador.class);
                } else {
                    // Si no es administrador, llevarlo a la actividad normal
                    intent = new Intent(MainActivity.this, MenuPrincipal.class);
                }
                startActivity(intent);
                // Finalizar la actividad actual
                finish();
            } else {
                // Mostrar un mensaje de error si las credenciales son incorrectas
                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Si el usuario o la contraseña están vacíos, mostrar un mensaje de error
            Toast.makeText(MainActivity.this, "Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show();
        }
    }



    // Método para verificar las credenciales del usuario en la base de datos
    private boolean verificarCredenciales(String usuario, String contrasenia) {
        // Acceder a la base de datos en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Especificar las columnas que deseamos consultar
        String[] projection = {
                "id_usuario",
                "administrador"
        };

        // Filtrar los resultados WHERE "nombre" = 'usuario' AND "contraseña" = 'contrasenia'
        String selection = "nombre = ? AND contraseña = ?";
        String[] selectionArgs = {usuario, contrasenia};

        // Realizar la consulta
        Cursor cursor = db.query("Usuarios", projection, selection, selectionArgs, null, null, null);

        // Verificar si la consulta devolvió alguna fila
        boolean credencialesCorrectas = cursor.getCount() > 0;

        // Obtener el valor de "administrador" si las credenciales son correctas
        if (credencialesCorrectas && cursor.moveToFirst()) {
            esAdmin = cursor.getInt(cursor.getColumnIndexOrThrow("administrador")) == 1;
        }

        // Cerrar el cursor y la conexión a la base de datos
        cursor.close();
        db.close();

        // Retornar si las credenciales son correctas
        return credencialesCorrectas;
    }



    private void guardarUsuarioEnPrefs(int idUsuario, String nombreUsuario) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id_usuario", idUsuario);
        editor.putString(PREF_USERNAME, nombreUsuario);
        editor.apply();
    }


    private void abrirActivityRegistro() {
        Intent intent = new Intent(MainActivity.this, ActivityRegistro.class);
        startActivity(intent);
    }

    private int obtenerIdUsuario(String usuario, String contrasenia) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {"id_usuario"};
        String selection = "nombre = ? AND contraseña = ?";
        String[] selectionArgs = {usuario, contrasenia};
        Cursor cursor = db.query("Usuarios", projection, selection, selectionArgs, null, null, null);
        int idUsuario = -1; // Valor predeterminado si no se encuentra el usuario
        if (cursor.moveToFirst()) {
            idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario"));
        }
        cursor.close();
        db.close();
        return idUsuario;
    }


    private void iniciarSesionComoInvitado() {
        // Lógica para iniciar sesión como invitado
    }
}

