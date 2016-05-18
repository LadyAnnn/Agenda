package com.example.kike.lp3_ep3_rojaspal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Base.datos.SQLControlador;

/**
 * Created by kike on 04/11/2015.
 */
public class ModificarMienbro extends AppCompatActivity {
    private EditText texto,texto2;
    private Button btnmodificar,btneliminar;

    long member_id;
    SQLControlador dbcon;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_miembro);

        dbcon = new SQLControlador(this);
        dbcon.abrirBaseDeDatos();

        texto=(EditText)findViewById(R.id.et_miembro_id);
        texto2=(EditText)findViewById(R.id.et_telefono2);
        btnmodificar=(Button)findViewById(R.id.btnupdate2);
        btneliminar=(Button)findViewById(R.id.btndelete);

        Intent i = getIntent();
        String memberID = i.getStringExtra("miembroId");
        String memberName = i.getStringExtra("miembroNombre");
        String membercelular = i.getStringExtra("miembrocelular");

        member_id = Long.parseLong(memberID);

        texto.setText(memberName);
        texto2.setText(membercelular);

        btnmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String memName_upd = texto.getText().toString();
                String mcelular = texto2.getText().toString();
                dbcon.actualizarDatos(member_id, memName_upd, mcelular);
                Intent home_intent = new Intent(getApplicationContext(),
                        MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(home_intent);

            }

        });
        btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbcon.deleteData(member_id);
                Intent home_intent = new Intent(getApplicationContext(),
                        MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(home_intent);

            }
        });
    }
}