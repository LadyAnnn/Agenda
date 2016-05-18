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
public class AgregarMienbro extends AppCompatActivity{
    private EditText texto1,texto2,texto3,texto4,texto5;
    private Button botonagregar;
    SQLControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_miembro);

        texto1=(EditText)findViewById(R.id.et_miembro_id);
        texto2=(EditText)findViewById(R.id.et_apellido);
        texto3=(EditText)findViewById(R.id.et_celular);
        texto4=(EditText)findViewById(R.id.et_telefono);
        texto5=(EditText)findViewById(R.id.et_email);

        botonagregar=(Button)findViewById(R.id.btnAgregarId);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();

        botonagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnAgregarId:
                        String name = texto1.getText().toString();
                        String apellido = texto2.getText().toString();
                        String celular = texto3.getText().toString();
                        String telefono = texto4.getText().toString();
                        String email = texto5.getText().toString();
                        dbconeccion.insertarDatos(name,apellido,celular,telefono,email);
                        Intent main = new Intent(AgregarMienbro.this, MainActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);
                        break;

                    default:
                        break;
                }

            }
        });
    }
}
