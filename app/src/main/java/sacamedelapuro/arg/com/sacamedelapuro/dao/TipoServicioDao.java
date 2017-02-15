package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.TipoServicio;

/**
 * Created by lgambino on 08/02/2017.
 */

public class TipoServicioDao extends GenericDaoImpl<TipoServicio>{


    public TipoServicioDao(Context context) {
        super(context);
    }

    public static class TablaTipoServicio implements BaseColumns {
        public static final String TABLA = "TIPO_SERVICIO";
        public static final String COLUMNA_NOMBRE = "NOMBRE";
    }

    private static final String[] columnas = {"NOMBRE"};

    public void delete(Integer id) {
        super.delete(TablaTipoServicio.TABLA, id);
    }

    public TipoServicio get(Integer id) {
        Cursor cursor = super.get(TablaTipoServicio.TABLA, columnas, id);

        TipoServicio tipoServicio = new TipoServicio();
        if (cursor.moveToFirst()) {
            do {
                tipoServicio.setNombre(cursor.getString(0));

            } while(cursor.moveToNext());
        }

        tipoServicio.setId(id);

        return tipoServicio;
    }

    public TipoServicio get(String nombre){
        super.leer();

        Cursor cursor = bd.rawQuery("SELECT _ID FROM " + TablaTipoServicio.TABLA + " WHERE nombre="+ nombre, null);

        TipoServicio tipoServicio = new TipoServicio();
        if (cursor.moveToFirst()) {
            do {
                tipoServicio.setId(cursor.getInt(0));

            } while(cursor.moveToNext());
        }

        tipoServicio.setNombre(nombre);

        return tipoServicio;
    }

    public Cursor getAll() {
        return super.getAll(TablaTipoServicio.TABLA);
    }

    public List<TipoServicio> getAllPorNombre() {
        leer();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + TablaTipoServicio.TABLA + " ORDER BY nombre ASC", null);

        List<TipoServicio> tipos = new ArrayList<TipoServicio>();
        if (cursor.moveToFirst()) {
            do {
                TipoServicio tipoServicio = new TipoServicio(cursor.getInt(0),cursor.getString(1));

                tipos.add(tipoServicio);

            } while(cursor.moveToNext());
        }

        return tipos;
    }

    public void save(TipoServicio tipoServicio) {
        ContentValues valores = valoresAll(tipoServicio);

        super.save(TablaTipoServicio.TABLA, valores);
    }

    public void update(TipoServicio tipoServicio) {
        ContentValues valores = valoresAll(tipoServicio);

        super.update(TablaTipoServicio.TABLA, valores, tipoServicio.getId());
    }

    private ContentValues valoresAll(TipoServicio tipoServicio){
        ContentValues valores = new ContentValues();
        valores.put(TablaTipoServicio.COLUMNA_NOMBRE, tipoServicio.getNombre());

        return valores;
    }
}
