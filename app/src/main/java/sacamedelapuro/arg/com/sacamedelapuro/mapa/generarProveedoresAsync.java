package sacamedelapuro.arg.com.sacamedelapuro.mapa;


import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import sacamedelapuro.arg.com.sacamedelapuro.dao.TipoServicioDao;
import sacamedelapuro.arg.com.sacamedelapuro.dao.UsuarioDao;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Servicio;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.TipoServicio;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;
import sacamedelapuro.arg.com.sacamedelapuro.util.General;

public class generarProveedoresAsync extends AsyncTask<Object[] , Integer, ArrayList<General> >{

    private GoogleMap miMapa;
    private TextView txtDistancia;
    private LatLng posicion;
    private float distancia;
    private int tipo;
    Context context;

    public generarProveedoresAsync(Context contexto, GoogleMap mapa, TextView txtDist, Float dist, LatLng pos, int t){
        miMapa=mapa; // Se debe limpiar
        txtDistancia=txtDist; // Cambiar con la nueva distancia
        posicion=pos; // Usado para calcular los proveedores cercanos
        distancia=dist;
        tipo=t;
        context=contexto;
    }

    @Override
    protected ArrayList<General> doInBackground(Object[]... params) {
        // Funcion lista
        return obtenerProveedoresCercanos(distancia, posicion);
    }

    protected void onPreExecute(){
        //Borrar todos los marcadores
        miMapa.clear();
    }

    protected void onPostExecute(ArrayList<General> result) {

        // Para el formato de distancia
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        String distString = formatter.format(distancia);

        // Se muestra la distancia seleccionada
        txtDistancia.setText(" Dist. actual: "+distString+" kms");
        txtDistancia.setVisibility(TextView.VISIBLE);

        // Mal, se pasan al mapa los prestadores
        MapaActivity.setPrestadores(result);

        // Llenado del mapa con prestadores
        MarkerOptions markerOpts;
        for(General aux: result){
            // Se definen las opciones para los marcadores
            markerOpts = new MarkerOptions();
            markerOpts.position(new LatLng(Double.valueOf(aux.getUbicacion().getLatitud()),
                    Double.valueOf(aux.getUbicacion().getLongitud())));
            markerOpts.title(aux.getUsuario().getNombre());
            markerOpts.snippet("tel:"+aux.getUsuario().getCelular());

            Marker m = miMapa.addMarker(markerOpts);
            MapaActivity act = (MapaActivity) context;
            act.addMarcador(m.getId(), aux);
        }
    }


    private ArrayList<General> obtenerProveedoresCercanos(float kms, LatLng pos){
        ArrayList<General> retorno= new ArrayList<>();
        Double lat=pos.latitude;
        Double lng=pos.longitude;
        General aux;
        float[] result={0,0,0};
        ArrayList<General> proveedores= buscarProveedores(); // TODO modificar
        for(int i=0; i<proveedores.size(); i++){
            aux=proveedores.get(i);
            Location.distanceBetween(lat,lng,Double.valueOf(aux.getUbicacion().getLatitud()),
                    Double.valueOf(aux.getUbicacion().getLongitud()),result);
            if(result[0]<kms*1000)
                retorno.add(aux);
        }
        return retorno;
    }

    private ArrayList<General> buscarProveedores(){
        List<General> retorno= new ArrayList<>();
        if (tipo==-1){
            List<TipoServicio> servicios = new TipoServicioDao(context).getAllPorNombre();
            List<General> aux= new ArrayList<>();
            for(int i=0; i<servicios.size(); i++){
                aux=new UsuarioDao(context).getUsuariosServicios(servicios.get(i).getId());
                retorno.addAll(aux);
            }
        }
        else{
            retorno= new UsuarioDao(context).getUsuariosServicios(tipo);
        }
        return new ArrayList<General> (retorno);
    }

    private ArrayList<LatLng> buscarProveedoresTest(){
        ArrayList<LatLng> retorno = new ArrayList<>();
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
