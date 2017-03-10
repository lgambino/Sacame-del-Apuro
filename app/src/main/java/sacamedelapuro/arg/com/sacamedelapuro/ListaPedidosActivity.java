package sacamedelapuro.arg.com.sacamedelapuro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sacamedelapuro.arg.com.sacamedelapuro.dao.PedidoDao;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Pedido;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;
import sacamedelapuro.arg.com.sacamedelapuro.util.PedidoAdapter;

public class ListaPedidosActivity extends AppCompatActivity {

    private ListView listaPedidos;
    private PedidoAdapter pedidosAdapter;
    private List<Pedido> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedidos);

        lista= new ArrayList<>();
        listaPedidos= (ListView ) findViewById(R.id.lista_pedidos);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();

        Usuario usuario = (Usuario) getIntent().getExtras().getSerializable("usuario");
        PedidoDao pedidoDao = new PedidoDao(this);

        lista = pedidoDao.getAllPorUsuario(usuario.getId());
        if(lista==null) lista = new ArrayList<Pedido>();

        pedidosAdapter = new PedidoAdapter(ListaPedidosActivity.this, lista);
        listaPedidos.setAdapter(pedidosAdapter);

        listaPedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pedido pedidoAux = (Pedido) listaPedidos.getItemAtPosition(position);


            }
        });
    }
}




