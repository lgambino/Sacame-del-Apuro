package sacamedelapuro.arg.com.sacamedelapuro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import sacamedelapuro.arg.com.sacamedelapuro.mapa.MapaActivity;


public class BuscarCercanosActivity extends AppCompatActivity {

    private Button btnBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cercanos);

        btnBuscar= (Button) findViewById(R.id.btnBuscarEnMapa);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BuscarCercanosActivity.this, MapaActivity.class);
                i.putExtra("distancia_inicial", 2); // Distancia inicial de 2 kms - NO usada
                startActivity(i);
            }
        });
    }
}
