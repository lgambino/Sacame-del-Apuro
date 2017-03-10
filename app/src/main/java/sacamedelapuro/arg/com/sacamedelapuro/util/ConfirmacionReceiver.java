package sacamedelapuro.arg.com.sacamedelapuro.util;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import sacamedelapuro.arg.com.sacamedelapuro.R;
import sacamedelapuro.arg.com.sacamedelapuro.mapa.MapaActivity;
import sacamedelapuro.arg.com.sacamedelapuro.mapa.PerfilMapaActivity;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Pedido;

public class ConfirmacionReceiver extends BroadcastReceiver {
    private static final String tag = "ConfirmacionReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (System.currentTimeMillis() % 2 == 0) {

            AlarmaTestNotificacion.cancelarAlarma();

            // Actualizar el pedido para mostrar que está confirmado por el prestador
            Pedido ped = (Pedido) intent.getExtras().get("pedido");
            ped.setConfirmado(true);

            // Crear la notificacion

            NotificationManager notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent i = new Intent(context, PerfilMapaActivity.class);
            General prestador = (General) intent.getExtras().get("prestador");
            i.putExtra("prestador", prestador);
            i.putExtra("origen", 2);

            PendingIntent pi = PendingIntent.getActivity(context, 1, i, PendingIntent.FLAG_ONE_SHOT);

            // Ringtone de la notificación
            //SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(context);
            String ringtone = "content://settings/system/notification_sound";
            Uri UriRingtone = Uri.parse(ringtone);

            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.sos);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(bm)
                    .setContentIntent(pi)
                    .setContentTitle("Confirmacion de prestador")
                    .setContentText(prestador.getUsuario().getNombre()+" está en camino")
                    .setAutoCancel(true)
                    .setSound(UriRingtone);

            notifManager.notify(1, builder.build());
        }
    }
}