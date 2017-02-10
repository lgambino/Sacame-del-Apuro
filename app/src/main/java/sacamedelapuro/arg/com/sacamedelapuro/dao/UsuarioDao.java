package sacamedelapuro.arg.com.sacamedelapuro.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;

/**
 * Created by lgambino on 08/02/2017.
 */

public class UsuarioDao extends GenericDaoImpl<Usuario> {

    public static String TABLA = "USUARIO";
    public static String[] COLUMNAS =  {"USERNAME", "PASS", "NOMBRE", "CELULAR", "DNI", "ID_ROL", "ID_SERVICIO", "ID_UBICACION"};


    public UsuarioDao(Context context) {
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
    public void save(Usuario usuario) {
        super.save(usuario);

        ContentValues valores = new ContentValues();
        valores.put(COLUMNAS[0], usuario.getUsername());
        valores.put(COLUMNAS[1], usuario.getPass());
        valores.put(COLUMNAS[2], usuario.getNombre());
        valores.put(COLUMNAS[3], usuario.getCelular());
        valores.put(COLUMNAS[4], usuario.getDni());
        valores.put(COLUMNAS[5], usuario.getRol().getId());
        valores.put(COLUMNAS[6], usuario.getServicio().getId());
        valores.put(COLUMNAS[7], usuario.getUbicacion().getId());

        bd.insert(TABLA, null, valores);

        bd = conexionBD.getReadableDatabase();
    }

    @Override
    public void update(Usuario usuario) {
        super.update(usuario);

        ContentValues valores = new ContentValues();
        valores.put(COLUMNAS[0], usuario.getUsername());
        valores.put(COLUMNAS[1], usuario.getPass());
        valores.put(COLUMNAS[2], usuario.getNombre());
        valores.put(COLUMNAS[3], usuario.getCelular());
        valores.put(COLUMNAS[4], usuario.getDni());
        valores.put(COLUMNAS[5], usuario.getRol().getId());
        valores.put(COLUMNAS[6], usuario.getServicio().getId());
        valores.put(COLUMNAS[7], usuario.getUbicacion().getId());

        bd.update(TABLA, valores, "_id=?", new String[]{usuario.getId().toString()});

        bd = conexionBD.getReadableDatabase();
    }
}
