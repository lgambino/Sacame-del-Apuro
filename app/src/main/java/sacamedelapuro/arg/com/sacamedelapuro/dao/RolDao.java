package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;


import sacamedelapuro.arg.com.sacamedelapuro.modelo.Rol;

/**
 * Created by lgambino on 08/02/2017.
 */

public class RolDao extends GenericDaoImpl<Rol>{


    public RolDao(Context context) {
        super(context);
    }

    public static class TablaRol implements BaseColumns {
        public static final String TABLA = "ROL";
        public static final String COLUMNA_NOMBRE = "NOMBRE";
    }

    private static final String[] columnas = {"NOMBRE"};


    public void delete(Integer id) {
        super.delete(TablaRol.TABLA, id);
    }

    public Cursor get(Integer id) {
        return super.get(TablaRol.TABLA, columnas, id);
    }

    public Cursor getAll() {
        return super.getAll(TablaRol.TABLA);
    }

    public void save(Rol rol) {
        ContentValues valores = valoresAll(rol);

        super.save(TablaRol.TABLA, valores);
    }

    public void update(Rol rol) {
        ContentValues valores = valoresAll(rol);

        super.update(TablaRol.TABLA, valores, rol.getId());
    }

    private ContentValues valoresAll(Rol rol){
        ContentValues valores = new ContentValues();
        valores.put(TablaRol.COLUMNA_NOMBRE, rol.getNombre());

        return valores;
    }
}
