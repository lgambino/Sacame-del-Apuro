package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sacamedelapuro.arg.com.sacamedelapuro.ConexionBD;

/**
 * Created by lgambino on 09/02/2017.
 */

public class GenericDaoImpl<T> implements GenericDao<T> {

    protected ConexionBD conexionBD;
    protected SQLiteDatabase bd;


    public GenericDaoImpl(Context context){
        this.conexionBD = new ConexionBD(context);
    }

    @Override
    public void delete(String tabla, Integer id) {
        escribir();

        bd.delete(tabla,"_id=?",new String[]{id.toString()});
    }

    @Override
    public Cursor get(String tabla, String[] campos, Integer id) {
        leer();

        Cursor cursor = bd.query(tabla, campos, "_id=" + id.toString(), null, null, null, null);

        return cursor;
    }

    @Override
    public Cursor getAll(String tabla) {
        leer();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + tabla, null);

        return cursor;
    }

    @Override
    public void save(String tabla, ContentValues valores) {
        escribir();

        bd.insert(tabla, null, valores);

        bd = conexionBD.getReadableDatabase();
    }

    @Override
    public void update(String tabla, ContentValues valores, Integer id) {
        escribir();

        bd.update(tabla, valores, "_id=?", new String[]{id.toString()});

        bd = conexionBD.getReadableDatabase();
    }

    @Override
    public void leer() {
        bd = conexionBD.getReadableDatabase();
    }

    @Override
    public void escribir() {
        bd = conexionBD.getWritableDatabase();
    }
}
