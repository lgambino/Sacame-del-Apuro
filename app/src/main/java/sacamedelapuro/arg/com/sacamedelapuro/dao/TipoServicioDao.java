package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

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

    public Cursor get(Integer id) {
        return super.get(TablaTipoServicio.TABLA, columnas, id);
    }

    public Cursor getAll() {
        return super.getAll(TablaTipoServicio.TABLA);
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
