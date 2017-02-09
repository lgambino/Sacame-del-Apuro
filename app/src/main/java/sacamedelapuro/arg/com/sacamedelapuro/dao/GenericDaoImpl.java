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

    public static String TABLA = "TABLA";
    public static String[] COLUMNAS =  {"COLUMNA1", "COLUMNA2"};

    protected ConexionBD conexionBD;
    protected SQLiteDatabase bd;


    public GenericDaoImpl(Context context){
        this.conexionBD = new ConexionBD(context);
    }

    @Override
    public void delete(Integer id) {
        bd = conexionBD.getWritableDatabase();
        bd.delete(TABLA,"_id=?",new String[]{id.toString()});
    }

    @Override
    public Cursor get(Integer id) {
        bd = conexionBD.getReadableDatabase();

        Cursor cursor = bd.query(TABLA, COLUMNAS, "_id=" + id.toString(), null, null, null, null);

        return cursor;
    }

    @Override
    public Cursor getAll() {
        bd = conexionBD.getReadableDatabase();

        Cursor cursor = bd.rawQuery("SELECT " + COLUMNAS + " FROM " + TABLA, null);

        return cursor;
    }

    @Override
    public void save(T t) {
        bd = conexionBD.getWritableDatabase();
    }

    @Override
    public void update(T t) {
        bd = conexionBD.getWritableDatabase();
    }
}
