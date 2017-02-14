package sacamedelapuro.arg.com.sacamedelapuro.modelo;

import java.io.Serializable;

/**
 * Created by lgambino on 08/02/2017.
 */

public class Usuario implements Serializable {

    private Integer id;
    private String username; // email
    private String pass;
    private String nombre; // de persona o empresa
    private String celular;
    private String dni;
    private String imagen; // url
    private Rol rol;
    private Servicio servicio;
    private Ubicacion ubicacion;


    public Usuario() {

    }

    public Usuario(Integer id, String username, String pass, String nombre, String celular,
                   String dni, String imagen, Rol rol, Ubicacion ubicacion) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.nombre = nombre;
        this.celular = celular;
        this.imagen = imagen;
        this.rol = rol;
        this.ubicacion = ubicacion;
        this.dni = dni;
    }

    public Usuario(Integer id, String username, String pass, String nombre, String celular,
                   String dni, String imagen, Rol rol, Servicio servicio, Ubicacion ubicacion) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.nombre = nombre;
        this.celular = celular;
        this.dni = dni;
        this.imagen = imagen;
        this.rol = rol;
        this.servicio = servicio;
        this.ubicacion = ubicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
