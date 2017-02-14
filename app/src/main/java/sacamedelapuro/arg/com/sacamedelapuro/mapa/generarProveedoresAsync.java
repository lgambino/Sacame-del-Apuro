package sacamedelapuro.arg.com.sacamedelapuro.mapa;


import android.location.Location;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class generarProveedoresAsync extends AsyncTask<Object[] , Integer, ArrayList<LatLng> >{

    private GoogleMap miMapa;
    private TextView txtDistancia;
    private LatLng posicion;
    private float distancia;

    public generarProveedoresAsync(GoogleMap mapa, TextView txtDist, Float dist, LatLng pos){
        miMapa=mapa; // Se debe limpiar
        txtDistancia=txtDist; // Cambiar con la nueva distancia
        posicion=pos; // Usado para calcular los proveedores cercanos
        distancia=dist;
    }

    @Override
    protected ArrayList<LatLng> doInBackground(Object[]... params) {
        // Funcion lista
        return obtenerProveedoresCercanos(distancia,posicion);
    }

    protected void onPreExecute(){
        //Borrar todos los marcadores
        miMapa.clear();
    }

    protected void onPostExecute(ArrayList<LatLng> result) {
        txtDistancia.setText(" Dist. actual(kms): "+distancia+" ");
        txtDistancia.setVisibility(TextView.VISIBLE);
        // TODO llenar mapa con proveedores

        MarkerOptions markerOpts;
        for(int i = 0; result.size() > i; i++){
            // Se definen las opciones para los marcadores
            markerOpts = new MarkerOptions();
            markerOpts.position(new LatLng(result.get(i).latitude, result.get(i).longitude));
            if(result.get(i).latitude==-31.6165){ // BORRAAAAAARRRR
                markerOpts.title(new StringBuilder().append("Emergencias náuticas").toString());
            }
            else {
                markerOpts.title(new StringBuilder().append("Proveedor de prueba ").append(i).toString());
            }
            markerOpts.snippet("tel:3454430409");
            miMapa.addMarker(markerOpts);
        }
    }


    private ArrayList<LatLng> obtenerProveedoresCercanos(float kms, LatLng pos){
        ArrayList<LatLng> retorno= new ArrayList<>();
        Double lat=pos.latitude;
        Double lng=pos.longitude;
        LatLng aux;
        float[] result={0,0,0};
        ArrayList<LatLng> proveedores= buscarProveedores(); // TODO modificar
        for(int i=0; i<proveedores.size(); i++){
            aux=proveedores.get(i);
            Location.distanceBetween(lat,lng,aux.latitude,aux.longitude,result);
            if(result[0]<kms*1000)
                retorno.add(aux);
        }
        return retorno;
    }

    private ArrayList<LatLng> buscarProveedores(){
        ArrayList<LatLng> retorno = new ArrayList<>(); // TODO Buscar de base de datos

        retorno.add(new LatLng(-31.63,-60.7));
        retorno.add(new LatLng(-31.6426,-60.7068));
        retorno.add(new LatLng(-31.6424,-60.688));
        retorno.add(new LatLng(-31.6247,-60.691));
        retorno.add(new LatLng(-31.6214,-60.6814));
        retorno.add(new LatLng(-31.6167,-60.6757));
        retorno.add(new LatLng(-31.6115,-60.677));
        retorno.add(new LatLng(-31.6165,-60.6594));
        return retorno;
    }



}
