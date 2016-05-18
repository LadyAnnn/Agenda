package Base.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

/**
 * Created by kikerojas on 27/10/2015.
 */
public class SQLControlador {

    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }


    public void insertarDatos(String name, String apellido, String celular, String telefono, String email) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.MIEMBRO_NOMBRE, name);
        cv.put(DBhelper.MIEMBRO_APELLIDO, apellido);
        cv.put(DBhelper.MIEMBRO_CELULAR, celular);
        cv.put(DBhelper.MIEMBRO_TELEFONO, telefono);
        cv.put(DBhelper.MIEMBRO_EMAIL, email);
        database.insert(DBhelper.TABLE_MEMBER, null, cv);
    }


    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[]{
                DBhelper.MIEMBRO_ID,
                DBhelper.MIEMBRO_NOMBRE,
                DBhelper.MIEMBRO_CELULAR
        };
        Cursor c = database.query(DBhelper.TABLE_MEMBER, todasLasColumnas, null,
                null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }


    public int actualizarDatos(long memberID, String memberName, String celular2) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.MIEMBRO_NOMBRE, memberName);
        cvActualizar.put(DBhelper.MIEMBRO_CELULAR, celular2);

        int i = database.update(DBhelper.TABLE_MEMBER, cvActualizar,
                DBhelper.MIEMBRO_ID + " = " + memberID, null);
        return i;
    }

    public void deleteData(long memberID) {
        database.delete(DBhelper.TABLE_MEMBER, DBhelper.MIEMBRO_ID + "="
                + memberID, null);
    }
    public int buscarcontacto(long memberID, String name, String celular22) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.MIEMBRO_NOMBRE, name);
        cvActualizar.put(DBhelper.MIEMBRO_CELULAR, celular22);

        int i = database.update(DBhelper.TABLE_MEMBER, cvActualizar,
                DBhelper.MIEMBRO_ID + " = " + memberID, null);
        return i;
    }


}

