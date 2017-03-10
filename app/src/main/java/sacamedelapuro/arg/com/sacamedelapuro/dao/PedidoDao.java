package sacamedelapuro.arg.com.sacamedelapuro.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Pedido;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Servicio;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Ubicacion;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;


public class PedidoDao extends GenericDaoImpl<Pedido> {


    public PedidoDao(Context context) {
        super(context);
    }

    public static class TablaPedido implements BaseColumns {
        public static final String TABLA = "PEDIDO";
        public static final String COLUMNA_USUARIO = "ID_USUARIO";
        public static final String COLUMNA_PRESTADOR = "ID_PRESTADOR";
        public static final String COLUMNA_FECHA = "FECHA";
        public static final String COLUMNA_SERVICIO = "ID_SERVICIO";
        public static final String COLUMNA_UBICACION = "ID_UBICACION";
        public static final String COLUMNA_CONFIRMADO = "CONFIRMADO";
    }

    private static final String[] columnas = {"ID_USUARIO", "ID_PRESTADOR", "FECHA", "ID_SERVICIO", "ID_UBICACION", "CONFIRMADO"};


    public void delete(Integer id) {
        super.delete(TablaPedido.TABLA, id);
    }

    public Pedido get(Integer id) {
        Cursor cursor = super.get(TablaPedido.TABLA, columnas, id);

        Pedido pedido = new Pedido();
        if (cursor.moveToFirst()) {
            do {
                pedido.setUsuario(new Usuario(cursor.getInt(0)));
                pedido.setPrestador(new Usuario(cursor.getInt(1)));
                pedido.setFecha(cursor.getString(2));
                pedido.setServicioPrestador(new Servicio(cursor.getInt(3)));
                pedido.setUbicacionUsuario(new Ubicacion(cursor.getInt(4)));
                if (cursor.getInt(5)==1) pedido.setConfirmado(true);
                else pedido.setConfirmado(false);


            } while(cursor.moveToNext());
        }

        return pedido;
    }

    public Cursor getAll() {
        return super.getAll(TablaPedido.TABLA);
    }

    public List<Pedido> getAllPorUsuario(Integer idUsuario) {
        leer();

        Cursor cursor = bd.rawQuery("SELECT ped.id_usuario, ped.id_prestador, ped.fecha, ped.id_servicio, ped.id_ubicacion, ped.confirmado," +
                " us.username, us.nombre, us.dni, us.celular," +
                " pre.username, pre.nombre, pre.celular," +
                " s.nombre, s.descripcion, s.observaciones, s.precio, s.puntaje," +
                " ub.latitud, ub.longitud, ub.direccion" +
                " FROM pedido as ped, usuario as us, usuario as pre, servicio as s, ubicacion as ub" +
                " WHERE (ped.id_usuario=" + idUsuario + " OR ped.id_prestador=" + idUsuario + ")" +
                " AND us._id=ped.id_usuario" +
                " AND pre._id=ped.id_prestador" +
                " AND s._id=ped.id_servicio" +
                " AND ub._id=ped.id_ubicacion " +
                " ORDER BY ped._id DESC", null);

        List<Pedido> pedidos = new ArrayList<Pedido>();
        if (cursor.moveToFirst()) {
            do {
                Pedido pedido = new Pedido();

                Usuario usuario = new Usuario(cursor.getInt(0));
                Usuario prestador = new Usuario(cursor.getInt(1));
                pedido.setFecha(cursor.getString(2));
                Servicio servicio = new Servicio(cursor.getInt(3));
                Ubicacion ubicacion = new Ubicacion(cursor.getInt(4));
                if (cursor.getInt(5)==1) pedido.setConfirmado(true);
                else pedido.setConfirmado(false);

                usuario.setUsername(cursor.getString(6));
                usuario.setNombre(cursor.getString(7));
                usuario.setDni(cursor.getString(8));
                usuario.setCelular(cursor.getString(9));

                prestador.setUsername(cursor.getString(10));
                prestador.setNombre(cursor.getString(11));
                prestador.setCelular(cursor.getString(12));

                servicio.setNombre(cursor.getString(13));
                servicio.setDescripcion(cursor.getString(14));
                servicio.setObservaciones(cursor.getString(15));
                servicio.setPrecio(cursor.getInt(16));
                servicio.setPuntaje(cursor.getInt(17));

                ubicacion.setLatitud(cursor.getString(18));
                ubicacion.setLongitud(cursor.getString(19));
                ubicacion.setDireccion(cursor.getString(20));

                pedido.setUsuario(usuario);
                pedido.setPrestador(prestador);
                pedido.setServicioPrestador(servicio);
                pedido.setUbicacionUsuario(ubicacion);

                pedidos.add(pedido);

            } while(cursor.moveToNext());
        }

        return pedidos;
    }

    public void save(Pedido pedido) {

        ContentValues valores = valoresAll(pedido);

        super.save(TablaPedido.TABLA, valores);
    }

    public void update(Pedido pedido) {

        ContentValues valores = valoresAll(pedido);

        super.update(TablaPedido.TABLA, valores, pedido.getId());
    }

    private ContentValues valoresAll(Pedido pedido){
        ContentValues valores = new ContentValues();

        valores.put(TablaPedido.COLUMNA_USUARIO, pedido.getUsuario().getId());
        valores.put(TablaPedido.COLUMNA_PRESTADOR, pedido.getPrestador().getId());
        valores.put(TablaPedido.COLUMNA_FECHA, pedido.getFecha());
        valores.put(TablaPedido.COLUMNA_SERVICIO, pedido.getServicioPrestador().getId());
        valores.put(TablaPedido.COLUMNA_UBICACION, pedido.getUbicacionUsuario().getId());
        valores.put(TablaPedido.COLUMNA_CONFIRMADO, pedido.getConfirmadoBD());

        return valores;
    }
}
