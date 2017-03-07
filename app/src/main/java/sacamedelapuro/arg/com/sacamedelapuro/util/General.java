package sacamedelapuro.arg.com.sacamedelapuro.util;

import java.io.Serializable;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Servicio;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.TipoServicio;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Ubicacion;
import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;

/**
 * Created by Luciano on 14/02/2017.
 */

public class General implements Serializable {

    private Usuario usuario;
    private Servicio servicio;
    private Ubicacion ubicacion;


    public General() {
    }

    public General(Usuario usuario, Servicio servicio) {
        this.usuario = usuario;
        this.servicio = servicio;
    }

    public General(Usuario usuario, Servicio servicio, Ubicacion ubicacion) {
        this.usuario = usuario;
        this.servicio = servicio;
        this.ubicacion = ubicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
