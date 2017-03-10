package sacamedelapuro.arg.com.sacamedelapuro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import sacamedelapuro.arg.com.sacamedelapuro.dao.TipoServicioDao;
import sacamedelapuro.arg.com.sacamedelapuro.mapa.MapaActivity;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.TipoServicio;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;


public class BuscarCercanosActivity extends AppCompatActivity {

    private final int CODIGO_ORIGEN_BUSCAR=1;
    private Button btnBuscar;
    private List<TipoServicio> servicios;
    private Spinner spin;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cercanos);

        usuario= (Usuario) getIntent().getExtras().get("usuario");

        servicios = new TipoServicioDao(this).getAllPorNombre();
        ArrayList<String> servs= new ArrayList<>();
        servs.add("Todos");
        for(int i=0; i<servicios.size(); i++){
            servs.add(servicios.get(i).getNombre());
        }

        spin = (Spinner) findViewById(R.id.spinnerBuscarCercanos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, servs);
        spin.setAdapter(adapter);

        btnBuscar= (Button) findViewById(R.id.btnBuscarEnMapa);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BuscarCercanosActivity.this, MapaActivity.class);
                i.putExtra("distancia_inicial", 2); // Distancia inicial de 2 kms
                i.putExtra("usuario", usuario);
                i.putExtra("origen", CODIGO_ORIGEN_BUSCAR);
                if(spin.getSelectedItemPosition()==0){
                    i.putExtra("servicio_id", -1);
                }
                else{
                    i.putExtra("servicio_id", servicios.get(spin.getSelectedItemPosition()-1).getId());
                }
                //i.putExtra("servicio_id", servicios.get(spin.getSelectedItemPosition()-1));
                startActivity(i);
            }
        });
    }
}
