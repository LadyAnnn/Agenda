package Base.datos;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kikerojas on 27/10/2015.
 */
public class DBhelper extends SQLiteOpenHelper {

    // Información de la tabla
    public static final String TABLE_MEMBER = "Usuarios";
    public static final String MIEMBRO_ID = "_id";
    public static final String MIEMBRO_NOMBRE = "nombre";
    public static final String MIEMBRO_APELLIDO = "apellido";
    public static final String MIEMBRO_CELULAR = "celular";
    public static final String MIEMBRO_TELEFONO = "telefono";
    public static final String MIEMBRO_EMAIL = "email";

    // información del a base de datos
    static final String DB_NAME = "DBMIEMBRO";
    static final int DB_VERSION = 1;

    // Información de la base de datos
    private static final String CREATE_TABLE = "create table "
            + TABLE_MEMBER + "("
            + MIEMBRO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + MIEMBRO_NOMBRE + " TEXT , "
            + MIEMBRO_APELLIDO + " TEXT , "
            + MIEMBRO_CELULAR + " INTEGER , "
            + MIEMBRO_TELEFONO + " INTEGER , "
            + MIEMBRO_EMAIL + " TEXT NOT NULL);";

    public DBhelper(Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
        onCreate(db);
    }


}


