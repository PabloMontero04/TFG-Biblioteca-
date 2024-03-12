package com.example.bibliotecatfg;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "biblioteca.db";

    private static final String TABLE_BIBLIOTECA = "Biblioteca";
    private static final String TABLE_USUARIO = "Usuarios";
    private static final String TABLE_LIBRO = "Libros";
    private static final String TABLE_EJEMPLAR = "Ejemplares";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla Biblioteca
        db.execSQL("CREATE TABLE " + TABLE_BIBLIOTECA + "(" +
                "id_biblioteca INTEGER PRIMARY KEY," +
                "nombre TEXT NOT NULL)");

        // Crear tabla Libros
        db.execSQL("CREATE TABLE " + TABLE_LIBRO + "(" +
                "id_libro INTEGER PRIMARY KEY," +
                "Categoria TEXT NOT NULL," +
                "ISBN TEXT NOT NULL," +
                "titulo TEXT NOT NULL," +
                "editorial TEXT NOT NULL," +
                "autor TEXT NOT NULL," +
                "paginas INTEGER NOT NULL," +
                "anio INTEGER NOT NULL," +
                "id_biblioteca INTEGER NOT NULL," +
                "FOREIGN KEY (id_biblioteca) REFERENCES " + TABLE_BIBLIOTECA + "(id_biblioteca))");

        // Crear tabla Ejemplares
        db.execSQL("CREATE TABLE " + TABLE_EJEMPLAR + "(" +
                "id_ejemplar INTEGER PRIMARY KEY," +
                "stock INTEGER NOT NULL," +
                "id_libro INTEGER NOT NULL," +
                "FOREIGN KEY (id_libro) REFERENCES " + TABLE_LIBRO + "(id_libro))");

        // Crear tabla Usuarios
        db.execSQL("CREATE TABLE " + TABLE_USUARIO + "(" +
                "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellido TEXT NOT NULL," +
                "DNI TEXT NOT NULL," +
                "correo TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "direccion TEXT NOT NULL," +
                "contraseña TEXT NOT NULL," +
                "administrador BOOLEAN NOT NULL," +
                "id_biblioteca INTEGER NOT NULL DEFAULT 1," + // Valor predeterminado para id_biblioteca
                "FOREIGN KEY (id_biblioteca) REFERENCES " + TABLE_BIBLIOTECA + "(id_biblioteca))");

        // Insertar usuario por defecto
        ContentValues valuesUsuario = new ContentValues();
        valuesUsuario.put("nombre", "Admin");
        valuesUsuario.put("apellido", "Admin");
        valuesUsuario.put("DNI", "12345678A");
        valuesUsuario.put("correo", "admin@example.com");
        valuesUsuario.put("telefono", "123456789");
        valuesUsuario.put("direccion", "Dirección del admin");
        valuesUsuario.put("contraseña", "admin123");
        valuesUsuario.put("administrador", true);
        db.insert(TABLE_USUARIO, null, valuesUsuario);

        // Insertar administrador por defecto
        ContentValues valuesAdmin = new ContentValues();
        valuesAdmin.put("nombre", "Usuario");
        valuesAdmin.put("apellido", "Usuario");
        valuesAdmin.put("DNI", "12345678B");
        valuesAdmin.put("correo", "usuario@example.com");
        valuesAdmin.put("telefono", "987654321");
        valuesAdmin.put("direccion", "Dirección del usuario");
        valuesAdmin.put("contraseña", "123");
        valuesAdmin.put("administrador", false);
        db.insert(TABLE_USUARIO, null, valuesAdmin);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIBLIOTECA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBRO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EJEMPLAR);

        // Create tables again
        onCreate(db);
    }
}
