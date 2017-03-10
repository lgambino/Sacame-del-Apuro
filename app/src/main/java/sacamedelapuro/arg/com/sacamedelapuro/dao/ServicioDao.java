package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Servicio;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.TipoServicio;

/**
 * Created by lgambino on 08/02/2017.
 */

public class ServicioDao extends GenericDaoImpl<Servicio>{


    public ServicioDao(Context context) {
        super(context);
    }

    public static class TablaServicio implements BaseColumns {
        public static final String TABLA = "SERVICIO";
        public static final String COLUMNA_NOMBRE = "NOMBRE";
        public static final String COLUMNA_DESCRIPCION = "DESCRIPCION";
        public static final String COLUMNA_OBSERVACIONES = "OBSERVACIONES";
        public static final String COLUMNA_PRECIO = "PRECIO";
        public static final String COLUMNA_PUNTAJE = "PUNTAJE";
        public static final String COLUMNA_ID_TIPO = "ID_TIPO";
    }

    private static final String[] columnas = {"NOMBRE", "DESCRIPCION", "OBSERVACIONES", "PRECIO", "PUNTAJE", "ID_TIPO"};


    public void delete(Integer id) {
        super.delete(TablaServicio.TABLA, id);
    }

    public Cursor get(Integer id) {
        return super.get(TablaServicio.TABLA, columnas, id);
    }

    public Cursor getAll() {
        return super.getAll(TablaServicio.TABLA);
    }

    public List<Servicio> getAllPorTipo(Integer idTipoServicio) {
        leer();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + TablaServicio.TABLA + " WHERE id_tipo="+ idTipoServicio, null);


        List<Servicio> servicios = new ArrayList<Servicio>();
        if (cursor.moveToFirst()) {
            do {
                Servicio servicio = new Servicio();

                servicio.setNombre(cursor.getString(0));
                servicio.setDescripcion(cursor.getString(1));
                servicio.setObservaciones(cursor.getString(2));
                servicio.setPrecio(cursor.getInt(3));
                servicio.setPuntaje(cursor.getInt(4));
                servicio.setTipo(new TipoServicio(cursor.getInt(5)));

                servicios.add(servicio);

            } while(cursor.moveToNext());
        }

        return servicios;
    }

    public void save(Servicio servicio) {
        ContentValues valores = valoresAll(servicio);

        super.save(TablaServicio.TABLA, valores);
    }

    public void update(Servicio servicio) {
        ContentValues valores = valoresAll(servicio);

        super.update(TablaServicio.TABLA, valores, servicio.getId());
    }

    private ContentValues valoresAll(Servicio servicio){
        ContentValues valores = new ContentValues();
        valores.put(TablaServicio.COLUMNA_NOMBRE, servicio.getNombre());
        valores.put(TablaServicio.COLUMNA_DESCRIPCION, servicio.getDescripcion());
        valores.put(TablaServicio.COLUMNA_OBSERVACIONES, servicio.getObservaciones());
        valores.put(TablaServicio.COLUMNA_PRECIO, servicio.getPrecio());
        valores.put(TablaServicio.COLUMNA_PUNTAJE, servicio.getPuntaje());
        valores.put(TablaServicio.COLUMNA_ID_TIPO, servicio.getTipo().getId());

        return valores;
    }
}
