package com.example.bibliotecatfg;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ListarUsuarioAdmin extends AppCompatActivity {

    private TextView resultadosTextView;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_usuario_admin);

        resultadosTextView = findViewById(R.id.resultadosTextView);
        dbHelper = new DbHelper(this);

        listarUsuarios();
    }

    private void listarUsuarios() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "id_usuario",
                "nombre",
                "apellido",
                "DNI",
                "correo",
                "telefono",
                "direccion",
                "contraseña",
                "administrador"
        };

        Cursor cursor = db.query("Usuarios", projection, null, null, null, null, null);

        StringBuilder stringBuilder = new StringBuilder();

        while (cursor.moveToNext()) {
            int idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow("id_usuario"));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
            String apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido"));
            String dni = cursor.getString(cursor.getColumnIndexOrThrow("DNI"));
            String correo = cursor.getString(cursor.getColumnIndexOrThrow("correo"));
            String telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
            String contraseña = cursor.getString(cursor.getColumnIndexOrThrow("contraseña"));
            int esAdmin = cursor.getInt(cursor.getColumnIndexOrThrow("administrador"));

            stringBuilder.append("ID: ").append(idUsuario).append("\n");
            stringBuilder.append("Nombre: ").append(nombre).append("\n");
            stringBuilder.append("Apellido: ").append(apellido).append("\n");
            stringBuilder.append("DNI: ").append(dni).append("\n");
            stringBuilder.append("Correo: ").append(correo).append("\n");
            stringBuilder.append("Teléfono: ").append(telefono).append("\n");
            stringBuilder.append("Dirección: ").append(direccion).append("\n");
            stringBuilder.append("Contraseña: ").append(contraseña).append("\n");
            stringBuilder.append("Es administrador: ").append(esAdmin == 1 ? "Sí" : "No").append("\n\n");
        }

        cursor.close();
        db.close();

        resultadosTextView.setText(stringBuilder.toString());
    }

}
