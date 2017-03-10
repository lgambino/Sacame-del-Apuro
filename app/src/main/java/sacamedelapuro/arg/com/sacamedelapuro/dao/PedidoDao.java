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
    }

    private static final String[] columnas = {"ID_USUARIO", "ID_PRESTADOR", "FECHA", "ID_SERVICIO", "ID_UBICACION"};


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

            } while(cursor.moveToNext());
        }

        return pedido;
    }

    public Cursor getAll() {
        return super.getAll(TablaPedido.TABLA);
    }

    public List<Pedido> getAllPorUsuario(Integer idUsuario) {
        leer();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + TablaPedido.TABLA + " WHERE id_usuario="+ idUsuario +
                " OR id_prestador=" + idUsuario, null);

        List<Pedido> pedidos = new ArrayList<Pedido>();
        if (cursor.moveToFirst()) {
            do {
                Pedido pedido = new Pedido();
                pedido.setUsuario(new Usuario(cursor.getInt(0)));
                pedido.setPrestador(new Usuario(cursor.getInt(1)));
                pedido.setFecha(cursor.getString(2));
                pedido.setServicioPrestador(new Servicio(cursor.getInt(3)));
                pedido.setUbicacionUsuario(new Ubicacion(cursor.getInt(4)));

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

        return valores;
    }
}
