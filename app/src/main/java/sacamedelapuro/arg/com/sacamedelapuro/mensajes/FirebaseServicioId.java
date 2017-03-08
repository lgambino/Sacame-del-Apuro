package sacamedelapuro.arg.com.sacamedelapuro.mensajes;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseServicioId extends FirebaseInstanceIdService {


    public FirebaseServicioId() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTokenRefresh() {
    // obtener token InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        saveTokenToPrefs(refreshedToken);
    }

    private void saveTokenToPrefs(String _token){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("registration_id", _token);
        editor.apply();

    // luego en cualquier parte de la aplicación podremos recuperar el token con
    // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    // preferences.getString("registration_id", null);

    }
}


