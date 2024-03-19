package com.example.bibliotecatfg;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class AnadirUsuarioAdmin extends AppCompatActivity {

    private EditText nombreEditText, apellidoEditText, dniEditText, correoEditText, telefonoEditText, direccionEditText, contraseniaEditText;
    private Switch esAdminSwitch;
    private Button enviarButton;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_usuario_admin);

        nombreEditText = findViewById(R.id.NombreUsuario);
        apellidoEditText = findViewById(R.id.ApellidoUsuario);
        dniEditText = findViewById(R.id.DNIUsuario);
        correoEditText = findViewById(R.id.CorreoUsuario);
        telefonoEditText = findViewById(R.id.TelefonoUsuario);
        direccionEditText = findViewById(R.id.DirreccionUsuario);
        contraseniaEditText = findViewById(R.id.ContraseniaUsuario);
        esAdminSwitch = findViewById(R.id.switch1);
        enviarButton = findViewById(R.id.enviar);
        dbHelper = new DbHelper(this);

        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarUsuario();
            }
        });
    }

    private void insertarUsuario() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombreEditText.getText().toString());
        values.put("apellido", apellidoEditText.getText().toString());
        values.put("DNI", dniEditText.getText().toString());
        values.put("correo", correoEditText.getText().toString());
        values.put("telefono", telefonoEditText.getText().toString());
        values.put("direccion", direccionEditText.getText().toString());
        values.put("contraseña", contraseniaEditText.getText().toString());
        values.put("administrador", esAdminSwitch.isChecked() ? 1 : 0); // 1 si es admin, 0 si no
        long resultado = db.insert("Usuarios", null, values);
        db.close();

        if (resultado != -1) {
            Toast.makeText(this, "Usuario añadido correctamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } else {
            Toast.makeText(this, "Error al añadir usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        nombreEditText.setText("");
        apellidoEditText.setText("");
        dniEditText.setText("");
        correoEditText.setText("");
        telefonoEditText.setText("");
        direccionEditText.setText("");
        contraseniaEditText.setText("");
        esAdminSwitch.setChecked(false);
    }
}
