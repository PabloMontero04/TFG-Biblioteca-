package com.example.bibliotecatfg;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EliminarUsuarioAdmin extends AppCompatActivity {

    private EditText idEditText;
    private Button enviarButton;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario_admin);

        idEditText = findViewById(R.id.ID);
        enviarButton = findViewById(R.id.enviar);
        dbHelper = new DbHelper(this);

        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarUsuario();
            }
        });
    }

    private void eliminarUsuario() {
        String idUsuario = idEditText.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int filasEliminadas = db.delete("Usuarios", "id_usuario = ?", new String[]{idUsuario});

        db.close();

        if (filasEliminadas > 0) {
            Toast.makeText(this, "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show();
            idEditText.setText(""); // Limpiar el campo ID después de la eliminación
        } else {
            Toast.makeText(this, "No se pudo eliminar el usuario", Toast.LENGTH_SHORT).show();
        }
    }
}
