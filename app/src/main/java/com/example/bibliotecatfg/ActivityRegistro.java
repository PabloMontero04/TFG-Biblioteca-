package com.example.bibliotecatfg;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityRegistro extends AppCompatActivity {
    EditText editTextNombre, editTextApellido, editTextDNI, editTextCorreo, editTextDireccion, editTextTelefono, editTextContrasenia;
    Button btnRegistro;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicializar vistas y base de datos
        dbHelper = new DbHelper(this);
        editTextNombre = findViewById(R.id.editTextTextnombre);
        editTextApellido = findViewById(R.id.editTextTextapellido);
        editTextDNI = findViewById(R.id.editTextTextDNI);
        editTextCorreo = findViewById(R.id.editTextTexcorreo);
        editTextDireccion = findViewById(R.id.editTextTexDireccion);
        editTextTelefono = findViewById(R.id.editTextTexTelefono);
        editTextContrasenia = findViewById(R.id.editTextTexcontrasenia);
        btnRegistro = findViewById(R.id.Registro);

        // Manejar clic en el botón de registro
        btnRegistro.setOnClickListener(v -> registrarUsuario());
    }

    private void registrarUsuario() {
        // Obtener los datos del usuario desde las vistas
        String nombre = editTextNombre.getText().toString();
        String apellido = editTextApellido.getText().toString();
        String dni = editTextDNI.getText().toString();
        String correo = editTextCorreo.getText().toString();
        String direccion = editTextDireccion.getText().toString();
        String telefono = editTextTelefono.getText().toString();
        String contrasenia = editTextContrasenia.getText().toString();

        // Validar que todos los campos estén completos
        if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || correo.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || contrasenia.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Obtener la base de datos en modo escritura
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Crear un nuevo registro de usuario
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("DNI", dni);
        values.put("correo", correo);
        values.put("direccion", direccion);
        values.put("telefono", telefono);
        values.put("contraseña", contrasenia);
        values.put("administrador", false); // Por defecto, el usuario no es administrador

        // Insertar el registro en la tabla Usuarios
        long newRowId = db.insert("Usuarios", null, values);

        // Verificar si se insertó correctamente
        if (newRowId != -1) {
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
            // Puedes realizar alguna acción adicional aquí, como redirigir a otra actividad
            Intent intent = new Intent(ActivityRegistro.this, MainActivity.class);

            // Inicia la ActivityRegistro
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
        }
    }
}

