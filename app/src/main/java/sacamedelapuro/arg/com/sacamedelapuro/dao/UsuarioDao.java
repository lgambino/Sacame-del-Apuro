package sacamedelapuro.arg.com.sacamedelapuro.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;

/**
 * Created by lgambino on 08/02/2017.
 */

public class UsuarioDao extends GenericDaoImpl<Usuario> {


    public UsuarioDao(Context context) {
        super(context);
    }

    public static class TablaUsuario implements BaseColumns {
        public static final String TABLA = "USUARIO";
        public static final String COLUMNA_USERNAME = "USERNAME";
        public static final String COLUMNA_PASS = "PASS";
        public static final String COLUMNA_NOMBRE = "NOMBRE";
        public static final String COLUMNA_CELULAR = "CELULAR";
        public static final String COLUMNA_DNI = "DNI";
        public static final String COLUMNA_ID_ROL = "ID_ROL";
        public static final String COLUMNA_ID_SERVICIO = "ID_SERVICIO";
        public static final String COLUMNA_ID_UBICACION = "ID_UBICACION";
    }

    private static final String[] columnas = {"USERNAME", "PASS", "NOMBRE", "CELULAR", "DNI", "ID_ROL", "ID_SERVICIO", "ID_UBICACION"};


    public void delete(Integer id) {
        super.delete(TablaUsuario.TABLA, id);
    }

    public Cursor get(Integer id) {
        return super.get(TablaUsuario.TABLA, columnas, id);
    }

    public Cursor getUsuario(String username){
        super.leer();

        Cursor cursor = bd.query(TablaUsuario.TABLA, columnas, "username=" + username, null, null, null, null);

        return cursor;
    }

    public boolean existeUsername(String username){

        Cursor cursor = getUsuario(username);

        boolean existe = false;
        if (cursor.moveToFirst()) {
            do {
                existe = true;
            } while(cursor.moveToNext());
        }

        return existe;
    }

    public Cursor getAll() {
        return super.getAll(TablaUsuario.TABLA);
    }

    public void save(Usuario usuario) {

        ContentValues valores = valoresAll(usuario);

        super.save(TablaUsuario.TABLA, valores);
    }

    public void update(Usuario usuario) {

        ContentValues valores = valoresAll(usuario);

        super.update(TablaUsuario.TABLA, valores, usuario.getId());
    }

    private ContentValues valoresAll(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put(TablaUsuario.COLUMNA_USERNAME, usuario.getUsername());
        valores.put(TablaUsuario.COLUMNA_PASS, usuario.getPass());
        valores.put(TablaUsuario.COLUMNA_NOMBRE, usuario.getNombre());
        valores.put(TablaUsuario.COLUMNA_CELULAR, usuario.getCelular());
        valores.put(TablaUsuario.COLUMNA_DNI, usuario.getDni());
        valores.put(TablaUsuario.COLUMNA_ID_ROL, usuario.getRol().getId());
        valores.put(TablaUsuario.COLUMNA_ID_SERVICIO, usuario.getServicio().getId());
        valores.put(TablaUsuario.COLUMNA_ID_UBICACION, usuario.getUbicacion().getId());

        return valores;
    }
}
