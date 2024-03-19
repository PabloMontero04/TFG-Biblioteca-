package com.example.bibliotecatfg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class ModificarUsuarioAdmin extends AppCompatActivity {

    private EditText idUsuarioEditText, nombreEditText, apellidoEditText, dniEditText, correoEditText, telefonoEditText, direccionEditText, contraseniaEditText;
    private Switch esAdminSwitch;
    private Button modificarButton;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario_admin);

        idUsuarioEditText = findViewById(R.id.ModificarIDUsuario);
        nombreEditText = findViewById(R.id.ModificarNombreUsuario);
        apellidoEditText = findViewById(R.id.ModificarApellidoUsuario);
        dniEditText = findViewById(R.id.ModificarDNIUsuario);
        correoEditText = findViewById(R.id.ModificarCorreoUsuario);
        telefonoEditText = findViewById(R.id.ModificarTelefonoUsuario);
        direccionEditText = findViewById(R.id.ModificarDireccionUsuario);
        contraseniaEditText = findViewById(R.id.ModificarContraseniaUsuario);
        esAdminSwitch = findViewById(R.id.switch1);
        modificarButton = findViewById(R.id.modificarBoton);
        dbHelper = new DbHelper(this);

        modificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarUsuario();
            }
        });
    }

    private void modificarUsuario() {
        String idUsuario = idUsuarioEditText.getText().toString();

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

        int rowsAffected = db.update("Usuarios", values, "id_usuario = ?", new String[]{idUsuario});
        db.close();

        if (rowsAffected > 0) {
            Toast.makeText(this, "Usuario modificado correctamente", Toast.LENGTH_SHORT).show();
            limpiarCampos();
        } else {
            Toast.makeText(this, "Error al modificar usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private void limpiarCampos() {
        idUsuarioEditText.setText("");
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
