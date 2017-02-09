package sacamedelapuro.arg.com.sacamedelapuro.modelo;

/**
 * Created by lgambino on 08/02/2017.
 */

public class Usuario {

    private Integer id;
    private String username;
    private String nombre;
    private String dni;
    private Contacto contacto;
    private Rol rol;
    private Servicio servicio;
    private Ubicacion ubicacion;


    public Usuario() {

    }

    public Usuario(Integer id, String username, String nombre, Rol rol, Ubicacion ubicacion, String dni, Contacto contacto) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.rol = rol;
        this.ubicacion = ubicacion;
        this.dni = dni;
        this.contacto = contacto;
    }

    public Usuario(Integer id, String username, String nombre, String dni, Contacto contacto, Rol rol, Ubicacion ubicacion, Servicio servicio) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.dni = dni;
        this.contacto = contacto;
        this.rol = rol;
        this.ubicacion = ubicacion;
        this.servicio = servicio;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
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
