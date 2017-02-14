package sacamedelapuro.arg.com.sacamedelapuro.mapa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.maps.model.LatLng;


public class BroadcastMapa extends BroadcastReceiver {

    private final int CODIGO_ORIGEN_BROADCAST=2;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, MapaActivity.class);
        i.putExtra("distancia_inicial", 2); // Distancia inicial - NO usada
        i.putExtra("origen", CODIGO_ORIGEN_BROADCAST);
        // TODO: ver que devuelve el intent, y borrar despues de que funcione la recepción del broadcast
        // i.putExtra("posicion", (LatLng)intent.getExtras().get("posicion"));
        context.startActivity(i);
    }
}
