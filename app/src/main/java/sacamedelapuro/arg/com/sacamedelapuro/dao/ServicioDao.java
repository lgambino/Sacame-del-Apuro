package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Servicio;

/**
 * Created by lgambino on 08/02/2017.
 */

public class ServicioDao extends GenericDaoImpl<Servicio>{

    public static String TABLA = "SERVICIO";
    public static String[] COLUMNAS =  {"NOMBRE", "DESCRIPCION", "OBSERVACIONES", "PRECIO", "ID_TIPO"};


    public ServicioDao(Context context) {
        super(context);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Cursor get(Integer id) {
        return super.get(id);
    }

    @Override
    public Cursor getAll() {
        return super.getAll();
    }

    @Override
    public void save(Servicio servicio) {
        super.save(servicio);

        ContentValues valores = new ContentValues();
        valores.put(COLUMNAS[0], servicio.getNombre());
        valores.put(COLUMNAS[1], servicio.getDescripcion());
        valores.put(COLUMNAS[2], servicio.getObservaciones());
        valores.put(COLUMNAS[3], servicio.getPrecio());
        valores.put(COLUMNAS[4], servicio.getTipo().getId());

        bd.insert(TABLA, null, valores);

        bd = conexionBD.getReadableDatabase();
    }

    @Override
    public void update(Servicio servicio) {
        super.update(servicio);

        ContentValues valores = new ContentValues();
        valores.put(COLUMNAS[0], servicio.getNombre());
        valores.put(COLUMNAS[1], servicio.getDescripcion());
        valores.put(COLUMNAS[2], servicio.getObservaciones());
        valores.put(COLUMNAS[3], servicio.getPrecio());
        valores.put(COLUMNAS[4], servicio.getTipo().getId());

        bd.update(TABLA, valores, "_id=?", new String[]{servicio.getId().toString()});

        bd = conexionBD.getReadableDatabase();
    }
}
