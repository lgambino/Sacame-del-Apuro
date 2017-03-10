package sacamedelapuro.arg.com.sacamedelapuro.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sacamedelapuro.arg.com.sacamedelapuro.R;
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

        TextView txtNombreUsuario = (TextView) row.findViewById(R.id.nombre_usuario);
        txtNombreUsuario.setText(this.getItem(position).getUsuario().getNombre());

        TextView txtNombrePrestador = (TextView) row.findViewById(R.id.nombre_prestador);
        txtNombreUsuario.setText(this.getItem(position).getPrestador().getNombre());

        TextView txtFecha = (TextView) row.findViewById(R.id.fecha);
        txtNombreUsuario.setText(this.getItem(position).getFecha());

        TextView txtPrecio = (TextView) row.findViewById(R.id.precio);
        txtNombreUsuario.setText("$ " + this.getItem(position).getServicioPrestador().getPrecio());

        return (row);
    }
}



