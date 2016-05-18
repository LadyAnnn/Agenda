package com.example.kike.lp3_ep3_rojaspal;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Base.datos.DBhelper;
import Base.datos.SQLControlador;

/**
 * Created by kike on 06/11/2015.
 */
public class Buscar extends AppCompatActivity{
    private EditText buscando;
    private EditText buscandotelefono;
    DBhelper as123;
    private Cursor fila2;

    SQLControlador dbcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_buscar);
        buscando = (EditText) findViewById(R.id.buscar_contacto);
        buscandotelefono = (EditText) findViewById(R.id.buscartelefono);


    }
        public void consulta(View v) {
            DBhelper admin = new DBhelper(getApplication());
            SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
            String dni = buscando.getText().toString();
            Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                    "select * from Usuarios where nombre=" + dni, null);
            if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
                buscando.setText(fila.getString(1));
                buscandotelefono.setText(fila.getString(4));
            } else
                Toast.makeText(this, "No existe una persona con dicho dni" ,
                        Toast.LENGTH_SHORT).show();
            bd.close();
        }
}

