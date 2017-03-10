package sacamedelapuro.arg.com.sacamedelapuro.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Pedido;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;


public class AlarmaTestNotificacion {

    static Context contexto;
    static General prestador;
    static Pedido pedido;
    static Usuario usuario;

    public AlarmaTestNotificacion (Context context, General prest, Pedido ped, Usuario user){

        contexto=context;
        prestador=prest;
        pedido=ped;
        usuario=user;

        Intent intent = new Intent(context, ConfirmacionReceiver.class);
        intent.putExtra("prestador", prest);
        intent.putExtra("usuario", user);
        intent.putExtra("pedido", ped);


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