package sacamedelapuro.arg.com.sacamedelapuro.modelo;

import java.io.Serializable;

/**
 * Created by lgambino on 08/02/2017.
 */

public class Ubicacion implements Serializable{


    private Integer id;
    private String latitud;
    private String longitud;
    private String direccion;


    public Ubicacion() {

    }

    public Ubicacion(Integer id) {
        this.id = id;
    }

    public Ubicacion(Integer id, String latitud, String longitud, String direccion) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
