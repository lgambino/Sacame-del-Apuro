package sacamedelapuro.arg.com.sacamedelapuro.mapa;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import sacamedelapuro.arg.com.sacamedelapuro.R;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback,
            GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowLongClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback{

    private GoogleMap miMapa;
    private Button btnDistancia;
    private TextView txtDistancia;
    private final int CODIGO_RESULTADO_0=0;
    private final int CODIGO_PEDIDO_PERMISOS=3;
    private final int CODIGO_RESULTADO_DISTANCIA =4;


    private ArrayList<LatLng> proveedores;
    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        v =  (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Poner aca? NO Tiene que ser asincrónico
        //proveedores = buscarProveedores();

        txtDistancia= (TextView) findViewById(R.id.txtDistancia);

        btnDistancia= (Button) findViewById(R.id.btnDistancia);
        btnDistancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapaActivity.this, DistanciaActivity.class);
                startActivityForResult(i, CODIGO_RESULTADO_DISTANCIA);
            }
            });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        miMapa = googleMap;
        miMapa.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(-31.6,-60.7) , 5) );

        miMapa.setOnInfoWindowLongClickListener(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                ActivityCompat.requestPermissions(MapaActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        CODIGO_PEDIDO_PERMISOS);
            }
            return;
        }
        miMapa.setMyLocationEnabled(true);

        // TODO Llamada asincrónica a que ponga todos los marcadores en el mapa
        // Distancia inicial de 2 kms
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case CODIGO_RESULTADO_DISTANCIA:
                // Resultado
                if (resultCode == Activity.RESULT_OK) {
                    Float distancia= (Float) data.getExtras().get("activity_pedido_distancia");
                    generarNuevosProveedores(distancia);
                }
                break;
        }
    }

    private void generarNuevosProveedores(float distancia){
        // Obtener la distancia del extra
        LatLng posicion= getPosicion();
        new generarProveedoresAsync(miMapa, txtDistancia, distancia, posicion).execute();
    }

    private LatLng getPosicion(){

        // Obtener la posicion actual - Idealmente no usar esta forma, sino la tercera
        Location loc = miMapa.getMyLocation();

        // Sino poner una posicion de marcador

        // O usar Location currentLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        // Ver mas en https://developer.android.com/training/location/retrieve-current.html

        return new LatLng(loc.getLatitude(), loc.getLongitude());
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
        } else {
            marker.showInfoWindow();
        }
        return true;
    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {
        v.vibrate(50);
        // Mostrar informacion o contactar al proveedor (intent de llamada puede ser)
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(marker.getSnippet()));
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODIGO_PEDIDO_PERMISOS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    try{
                        miMapa.setMyLocationEnabled(true);
                    } catch(SecurityException ex){
                        Toast.makeText(MapaActivity.this, "Error al acceder a los permisos de localización.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
