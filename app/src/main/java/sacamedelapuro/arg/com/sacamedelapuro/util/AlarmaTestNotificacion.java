package sacamedelapuro.arg.com.sacamedelapuro.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;


public class AlarmaTestNotificacion {

    static Context contexto;
    static General prestador;

    public AlarmaTestNotificacion (Context context, General prest){

        contexto=context;
        prestador=prest;

        Intent intent = new Intent(context, ConfirmacionReceiver.class);
        intent.putExtra("prestador", prest);


        PendingIntent pi = PendingIntent.getBroadcast(context,prest.getUsuario().getId(),intent,0);

        // Programar la alarma
        // Luego de 5 Segundos
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);
        AlarmManager am =(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 3000, pi);
    }

    public static void cancelarAlarma(){

        // Posibilidad de alarma infinita
        // TODO: revisar como cancelarla correctamente
        if (contexto !=null && prestador != null) {

            Intent intent = new Intent(contexto, ConfirmacionReceiver.class);
            PendingIntent pi = PendingIntent.getBroadcast(contexto, prestador.getUsuario().getId(), intent, 0);

            // Cancelar la alarma
            AlarmManager am = (AlarmManager) contexto.getSystemService(Context.ALARM_SERVICE);
            am.cancel(pi);
        }
    }

}