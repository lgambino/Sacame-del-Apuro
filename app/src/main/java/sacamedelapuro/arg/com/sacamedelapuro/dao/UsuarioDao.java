package sacamedelapuro.arg.com.sacamedelapuro.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Rol;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Servicio;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Ubicacion;
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
        public static final String COLUMNA_IMAGEN = "IMAGEN";
        public static final String COLUMNA_ID_ROL = "ID_ROL";
        public static final String COLUMNA_ID_SERVICIO = "ID_SERVICIO";
        public static final String COLUMNA_ID_UBICACION = "ID_UBICACION";
    }

    private static final String[] columnas = {"USERNAME", "PASS", "NOMBRE", "CELULAR", "DNI", "IMAGEN", "ID_ROL", "ID_SERVICIO", "ID_UBICACION"};


    public void delete(Integer id) {
        super.delete(TablaUsuario.TABLA, id);
    }

    public Usuario get(Integer id) {
        Cursor cursor = super.get(TablaUsuario.TABLA, columnas, id);

        Usuario usuario = new Usuario();
        if (cursor.moveToFirst()) {
            do {
                usuario.setUsername(cursor.getString(0));
                usuario.setPass(cursor.getString(1));
                usuario.setNombre(cursor.getString(2));
                usuario.setCelular(cursor.getString(3));
                usuario.setDni(cursor.getString(4));
                usuario.setImagen(cursor.getString(5));
                usuario.setRol(new Rol(cursor.getInt(6)));

                if(cursor.getString(7)!=null) usuario.setServicio(new Servicio(cursor.getInt(7)));
                if(cursor.getString(8)!=null) usuario.setUbicacion(new Ubicacion(cursor.getInt(8)));

            } while(cursor.moveToNext());
        }

        return usuario;
    }

    public Usuario getUsuario(String username){
        super.leer();

        Cursor cursor = bd.query(TablaUsuario.TABLA, columnas, "username='" + username + "'", null, null, null, null);

        Usuario usuario = new Usuario();
        if (cursor.moveToFirst()) {
            do {
                usuario.setUsername(cursor.getString(0));
                usuario.setPass(cursor.getString(1));
                usuario.setNombre(cursor.getString(2));
                usuario.setCelular(cursor.getString(3));
                usuario.setDni(cursor.getString(4));
                usuario.setImagen(cursor.getString(5));
                usuario.setRol(new Rol(cursor.getInt(6)));

                if(cursor.getString(7)!=null) usuario.setServicio(new Servicio(cursor.getInt(7)));
                if(cursor.getString(8)!=null) usuario.setUbicacion(new Ubicacion(cursor.getInt(8)));

            } while(cursor.moveToNext());
        }

        return usuario;
    }

    public boolean existeUsername(String username){

        boolean existe = true;

        Usuario usuario = getUsuario(username);
        if(usuario.getUsername().isEmpty()) existe = false;

        return existe;
    }

    public Cursor getAll() {
        return super.getAll(TablaUsuario.TABLA);
    }

    public List<Usuario> getAllPorRol(Integer idRol) {
        leer();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + TablaUsuario.TABLA + " WHERE id_rol="+ idRol, null);


        List<Usuario> usuarios = new ArrayList<Usuario>();
        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                usuario.setUsername(cursor.getString(0));
                usuario.setPass(cursor.getString(1));
                usuario.setNombre(cursor.getString(2));
                usuario.setCelular(cursor.getString(3));
                usuario.setDni(cursor.getString(4));
                usuario.setImagen(cursor.getString(5));
                usuario.setRol(new Rol(cursor.getInt(6)));

                if(cursor.getString(7)!=null) usuario.setServicio(new Servicio(cursor.getInt(7)));
                if(cursor.getString(8)!=null) usuario.setUbicacion(new Ubicacion(cursor.getInt(8)));

                usuarios.add(usuario);

            } while(cursor.moveToNext());
        }

        return usuarios;
    }

    public void save(Usuario usuario) {

        ContentValues valores = valoresAll(usuario);

        super.save(TablaUsuario.TABLA, valores);
    }

    public void update(Usuario usuario) {

        ContentValues valores = valoresAll(usuario);

        super.update(TablaUsuario.TABLA, valores, usuario.getId());
    }

    public boolean validarPass(String username, String pass){
        super.leer();

        Cursor cursor = bd.query(TablaUsuario.TABLA, columnas, "username='" + username + "'" +
                " AND pass='" + pass + "'", null, null, null, null);

        boolean coincide = false;
        if (cursor.moveToFirst()) {
            do {
                coincide = true;
            } while(cursor.moveToNext());
        }

        return coincide;
    }

    private ContentValues valoresAll(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put(TablaUsuario.COLUMNA_USERNAME, usuario.getUsername());
        valores.put(TablaUsuario.COLUMNA_PASS, usuario.getPass());
        valores.put(TablaUsuario.COLUMNA_NOMBRE, usuario.getNombre());
        valores.put(TablaUsuario.COLUMNA_CELULAR, usuario.getCelular());
        valores.put(TablaUsuario.COLUMNA_DNI, usuario.getDni());
        valores.put(TablaUsuario.COLUMNA_IMAGEN, usuario.getImagen());
        valores.put(TablaUsuario.COLUMNA_ID_ROL, usuario.getRol().getId());
        valores.put(TablaUsuario.COLUMNA_ID_SERVICIO, usuario.getServicio().getId());
        valores.put(TablaUsuario.COLUMNA_ID_UBICACION, usuario.getUbicacion().getId());

        return valores;
    }
}
