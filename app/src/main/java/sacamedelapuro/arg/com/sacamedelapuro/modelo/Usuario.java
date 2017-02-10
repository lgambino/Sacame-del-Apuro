package sacamedelapuro.arg.com.sacamedelapuro.modelo;

/**
 * Created by lgambino on 08/02/2017.
 */

public class Usuario {

    private Integer id;
    private String username; // email
    private String pass;
    private String nombre; // de persona o empresa
    private String celular;
    private String dni;
    private Rol rol;
    private Servicio servicio;
    private Ubicacion ubicacion;


    public Usuario() {

    }

    public Usuario(Integer id, String username, String pass, String nombre, String celular, String dni, Rol rol, Ubicacion ubicacion) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.nombre = nombre;
        this.celular = celular;
        this.rol = rol;
        this.ubicacion = ubicacion;
        this.dni = dni;
    }

    public Usuario(Integer id, String username, String pass, String nombre, String celular, String dni, Rol rol, Servicio servicio, Ubicacion ubicacion) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.nombre = nombre;
        this.celular = celular;
        this.dni = dni;
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
