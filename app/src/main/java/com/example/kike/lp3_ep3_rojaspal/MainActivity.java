package com.example.kike.lp3_ep3_rojaspal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import Base.datos.DBhelper;
import Base.datos.SQLControlador;

public class MainActivity extends AppCompatActivity {
    private Button agregarmienbro;
    private ListView lista;
    private TextView txtid,txtnombre,txttelefono;
    private FloatingActionButton floatingActionButton;

    SQLControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();

        agregarmienbro = (Button) findViewById(R.id.btnAgregarMiembro);
        lista = (ListView) findViewById(R.id.listViewMiembros);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent buscar = new Intent(getBaseContext(),Buscar.class);
                startActivity(buscar);

                Snackbar.make(floatingActionButton, "Buscar contactos", Snackbar.LENGTH_LONG).show();

            }
        });

        agregarmienbro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgregarMienbro.class);
                startActivity(intent);
            }
        });
        // Tomar los datos desde la base de datos para poner en el curso y después en el adapter
        Cursor cursor = dbconeccion.leerDatos();

        String[] from = new String[]{
                DBhelper.MIEMBRO_ID,
                DBhelper.MIEMBRO_NOMBRE,
                DBhelper.MIEMBRO_CELULAR
        };
        int[] to = new int[]{
                R.id.miembro_id,
                R.id.miembro_nombre,
                R.id.miembro_celular

        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this, R.layout.formato_fila, cursor, from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                txtid = (TextView) view.findViewById(R.id.miembro_id);
                txtnombre = (TextView) view.findViewById(R.id.miembro_nombre);
                txttelefono = (TextView) view.findViewById(R.id.miembro_celular);

                String aux_miembroId = txtid.getText().toString();
                String aux_miembroNombre = txtnombre.getText().toString();
                String aux_miembrocelular = txttelefono.getText().toString();


                Intent modify_intent = new Intent(getApplicationContext(), ModificarMienbro.class);
                modify_intent.putExtra("miembroId", aux_miembroId);
                modify_intent.putExtra("miembroNombre", aux_miembroNombre);
                modify_intent.putExtra("miembrocelular", aux_miembrocelular);
                startActivity(modify_intent);
            }
        });
    }  //termina el onCreate






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
