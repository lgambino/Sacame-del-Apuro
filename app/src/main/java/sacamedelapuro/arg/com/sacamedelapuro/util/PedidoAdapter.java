package sacamedelapuro.arg.com.sacamedelapuro.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sacamedelapuro.arg.com.sacamedelapuro.R;
import sacamedelapuro.arg.com.sacamedelapuro.dao.ServicioDao;
import sacamedelapuro.arg.com.sacamedelapuro.dao.UsuarioDao;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Pedido;

/**
 * Created by Luciano on 09/03/2017.
 */

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    private LayoutInflater inflater;
    private Context contexto;

    public PedidoAdapter(Context contexto, List<Pedido> items) {
        super(contexto, R.layout.fila_pedido, items);
        inflater = LayoutInflater.from(contexto);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) row = inflater.inflate(R.layout.fila_pedido, parent, false);

        Pedido pedidoAux = this.getItem(position);

        TextView txtNombrePrestador = (TextView) row.findViewById(R.id.nombre_prestador);
        txtNombrePrestador.setText(pedidoAux.getPrestador().getNombre());

        TextView txtNombrePrecio = (TextView) row.findViewById(R.id.nombre_precio);
        txtNombrePrecio.setText(String.valueOf(pedidoAux.getServicioPrestador().getPrecio()));

        TextView txtNombreFecha = (TextView) row.findViewById(R.id.nombre_fecha);
        txtNombreFecha.setText(pedidoAux.getFecha());

        return (row);
    }
}



