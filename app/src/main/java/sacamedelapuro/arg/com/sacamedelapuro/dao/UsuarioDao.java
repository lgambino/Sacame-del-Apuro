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
import sacamedelapuro.arg.com.sacamedelapuro.util.General;

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

        String idArray[] = {"_ID"};
        String columnasAux[] = new String[columnas.length+1];
        System.arraycopy(idArray, 0, columnasAux, 0, 1);
        System.arraycopy(columnas, 0, columnasAux, 1, columnas.length);

        Cursor cursor = bd.query(TablaUsuario.TABLA, columnasAux, "username='" + username + "'", null, null, null, null);

        Usuario usuario = new Usuario();
        if (cursor.moveToFirst()) {
            do {
                usuario.setId(cursor.getInt(0));
                usuario.setUsername(cursor.getString(1));
                usuario.setPass(cursor.getString(2));
                usuario.setNombre(cursor.getString(3));
                usuario.setCelular(cursor.getString(4));
                usuario.setDni(cursor.getString(5));
                usuario.setImagen(cursor.getString(6));
                usuario.setRol(new Rol(cursor.getInt(7)));

                if(cursor.getString(8)!=null) usuario.setServicio(new Servicio(cursor.getInt(7)));
                if(cursor.getString(9)!=null) usuario.setUbicacion(new Ubicacion(cursor.getInt(8)));

            } while(cursor.moveToNext());
        }

        return usuario;
    }

    public boolean existeUsername(String username){

        boolean existe = true;

        Usuario usuario = getUsuario(username);
        if(usuario.getUsername()==null) existe = false;

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

    public List<Usuario> getUsuariosComunes(){
        return getAllPorRol(1); // código de usuarios comunes
    }

    public List<Usuario> getPrestadores(){
        return getAllPorRol(2); // código de prestadores
    }

    public List<General> getUsuariosServicios(Integer idTipoServicio){
        leer();

        Cursor cursor = bd.rawQuery("SELECT us._id, us.nombre, us.celular, s._id, s.nombre, s.descripcion, s.observaciones, s.precio, s.puntaje, ub.latitud, ub.longitud, ub.direccion " +
                                    "FROM usuario as us, servicio as s, ubicacion as ub " +
                                    "WHERE us.id_rol=2 AND s.id_tipo=" + idTipoServicio + " AND us.id_servicio=s._id " +
                                    "AND ub._id=us.id_ubicacion", null);

        List<General> listado = new ArrayList<General>();
        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                Servicio servicio = new Servicio();
                Ubicacion ubicacion = new Ubicacion();

                usuario.setId(cursor.getInt(0));
                usuario.setNombre(cursor.getString(1));
                usuario.setCelular(cursor.getString(2));
                servicio.setId(cursor.getInt(3));
                servicio.setNombre(cursor.getString(4));
                servicio.setDescripcion(cursor.getString(5));
                servicio.setObservaciones(cursor.getString(6));
                servicio.setPrecio(cursor.getInt(7));
                servicio.setPuntaje(cursor.getInt(8));
                ubicacion.setLatitud(cursor.getString(9));
                ubicacion.setLongitud(cursor.getString(10));
                ubicacion.setDireccion(cursor.getString(11));

                General general = new General();
                general.setUsuario(usuario);
                general.setServicio(servicio);
                general.setUbicacion(ubicacion);

                listado.add(general);

            } while(cursor.moveToNext());
        }

        return listado;
    }

    public General getPrestadorServicio(Integer idPrestador){
        leer();

        Cursor cursor = bd.rawQuery("SELECT us._id, us.nombre, us.celular, s._id, s.nombre, s.descripcion, s.observaciones, s.precio, s.puntaje " +
                "FROM usuario as us, servicio as s " +
                "WHERE us.id_=" + idPrestador + " AND us.id_servicio=s._id", null);

        General general = new General();
        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                Servicio servicio = new Servicio();

                usuario.setId(cursor.getInt(0));
                usuario.setNombre(cursor.getString(1));
                usuario.setCelular(cursor.getString(2));
                servicio.setId(cursor.getInt(3));
                servicio.setNombre(cursor.getString(4));
                servicio.setDescripcion(cursor.getString(5));
                servicio.setObservaciones(cursor.getString(6));
                servicio.setPrecio(cursor.getInt(7));
                servicio.setPuntaje(cursor.getInt(8));

                general.setUsuario(usuario);
                general.setServicio(servicio);

            } while(cursor.moveToNext());
        }

        return general;
    }

    public General getUsuarioUbicacion(Integer idUsuario){
        leer();

        Cursor cursor = bd.rawQuery("SELECT us._id, us.nombre, us.celular, ub.latitud, ub.longitud, ub.direccion " +
                "FROM usuario as us, ubicacion as ub " +
                "WHERE us.id_=" + idUsuario + " AND ub._id=us.id_ubicacion", null);

        General general = new General();
        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                Ubicacion ubicacion = new Ubicacion();

                usuario.setId(cursor.getInt(0));
                usuario.setNombre(cursor.getString(1));
                usuario.setCelular(cursor.getString(2));
                ubicacion.setLatitud(cursor.getString(3));
                ubicacion.setLongitud(cursor.getString(4));
                ubicacion.setDireccion(cursor.getString(5));

                general.setUsuario(usuario);
                general.setUbicacion(ubicacion);

            } while(cursor.moveToNext());
        }

        return general;
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

        if(usuario.getId()!=null){
            valores.put(TablaUsuario._ID, usuario.getId());
        }

        valores.put(TablaUsuario.COLUMNA_USERNAME, usuario.getUsername());
        valores.put(TablaUsuario.COLUMNA_PASS, usuario.getPass());
        valores.put(TablaUsuario.COLUMNA_NOMBRE, usuario.getNombre());
        valores.put(TablaUsuario.COLUMNA_CELULAR, usuario.getCelular());
        valores.put(TablaUsuario.COLUMNA_DNI, usuario.getDni());

        if(usuario.getImagen()!=null && usuario.getImagen().length()>0){
            valores.put(TablaUsuario.COLUMNA_IMAGEN, usuario.getImagen());
        }
        valores.put(TablaUsuario.COLUMNA_ID_ROL, usuario.getRol().getId());

        if(usuario.getServicio()!=null){
            valores.put(TablaUsuario.COLUMNA_ID_SERVICIO, usuario.getServicio().getId());
        }

        if(usuario.getUbicacion()!=null){
            valores.put(TablaUsuario.COLUMNA_ID_UBICACION, usuario.getUbicacion().getId());
        }

        return valores;
    }
}
