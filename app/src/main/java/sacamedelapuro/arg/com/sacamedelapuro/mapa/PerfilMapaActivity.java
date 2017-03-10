package sacamedelapuro.arg.com.sacamedelapuro.mapa;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import sacamedelapuro.arg.com.sacamedelapuro.R;
import sacamedelapuro.arg.com.sacamedelapuro.util.General;

public class PerfilMapaActivity extends AppCompatActivity {

    private Button btnConfirmar;
    private TextView txtNombre;
    private TextView txtServicio;
    private TextView txtCelular;
    private TextView txtPrecio;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_mapa);

        txtNombre= (TextView) findViewById(R.id.textViewPerfilPrestadorNombre);
        txtServicio= (TextView) findViewById(R.id.textViewPerfilServicioNombre);
        txtCelular= (TextView) findViewById(R.id.textViewPerfilCelularNumero);
        txtPrecio= (TextView) findViewById(R.id.textViewPerfilPrecioValor);
        imageView = (ImageView) findViewById(R.id.img_perfil_mapa);

        // Sacar la información correspondiente
        final General prest = (General) getIntent().getExtras().get("prestador");

        txtNombre.setText(prest.getUsuario().getNombre());
        txtServicio.setText(prest.getServicio().getNombre());
        txtCelular.setText(String.valueOf(prest.getUsuario().getCelular()));
        txtPrecio.setText(String.valueOf(prest.getServicio().getPrecio()));

        btnConfirmar = (Button) findViewById(R.id.btnPerfilMapa);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "Esperando confirmación del proveedor." , Toast.LENGTH_SHORT).show();

                //Se pasa el prestador al mapa para que realice la alarma para notificacion
                Intent returnIntent = new Intent();
                returnIntent.putExtra("prestador", prest);
                setResult(MapaActivity.RESULT_OK, returnIntent);
                finish();
            }
        });

        if((int)getIntent().getExtras().get("origen")==2){
            btnConfirmar.setEnabled(false);
        }

        String aux = prest.getUsuario().getImagen();
        int resID = getResources().getIdentifier(aux, "drawable", getPackageName());
        imageView.setImageResource(resID);

    }
}
