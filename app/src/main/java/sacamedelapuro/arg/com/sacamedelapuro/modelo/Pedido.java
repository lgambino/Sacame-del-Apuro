package sacamedelapuro.arg.com.sacamedelapuro.modelo;

import java.io.Serializable;


public class Pedido implements Serializable {

    private Integer id;
    private Usuario usuario;
    private Usuario prestador;
    private String fecha;
    private Servicio servicioPrestador;
    private Ubicacion ubicacionUsuario;


    public Pedido() {

    }

    public Pedido(Integer id, Usuario usuario, Usuario prestador, String fecha, Servicio servicioPrestador, Ubicacion ubicacionUsuario) {
        this.id = id;
        this.usuario = usuario;
        this.prestador = prestador;
        this.fecha = fecha;
        this.servicioPrestador = servicioPrestador;
        this.ubicacionUsuario = ubicacionUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getPrestador() {
        return prestador;
    }

    public void setPrestador(Usuario prestador) {
        this.prestador = prestador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Servicio getServicioPrestador() {
        return servicioPrestador;
    }

    public void setServicioPrestador(Servicio servicioPrestador) {
        this.servicioPrestador = servicioPrestador;
    }

    public Ubicacion getUbicacionUsuario() {
        return ubicacionUsuario;
    }

    public void setUbicacionUsuario(Ubicacion ubicacionUsuario) {
        this.ubicacionUsuario = ubicacionUsuario;
    }
}
