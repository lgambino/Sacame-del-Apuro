package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;



import sacamedelapuro.arg.com.sacamedelapuro.modelo.Rol;

/**
 * Created by lgambino on 08/02/2017.
 */

public class RolDao extends GenericDaoImpl<Rol>{

    public static String TABLA = "ROL";
    public static String[] COLUMNAS =  {"NOMBRE"};


    public RolDao(Context context) {
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
    public void save(Rol rol) {
        super.save(rol);

        ContentValues valores = new ContentValues();
        valores.put(COLUMNAS[0], rol.getNombre());

        bd.insert(TABLA, null, valores);

        bd = conexionBD.getReadableDatabase();
    }

    @Override
    public void update(Rol rol) {
        super.update(rol);

        ContentValues valores = new ContentValues();
        valores.put(COLUMNAS[0], rol.getNombre());

        bd.update(TABLA, valores, "_id=?", new String[]{rol.getId().toString()});

        bd = conexionBD.getReadableDatabase();
    }
}
