package sacamedelapuro.arg.com.sacamedelapuro.mapa;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sacamedelapuro.arg.com.sacamedelapuro.R;
import sacamedelapuro.arg.com.sacamedelapuro.util.AlarmaTestNotificacion;
import sacamedelapuro.arg.com.sacamedelapuro.util.ConfirmacionReceiver;
import sacamedelapuro.arg.com.sacamedelapuro.util.General;

public class PerfilMapaActivity extends AppCompatActivity {

    Button btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_mapa);

        // Sacar la información correspondiente
        final General prest = (General) getIntent().getExtras().get("prestador");

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
    }
}
