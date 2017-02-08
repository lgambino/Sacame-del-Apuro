package sacamedelapuro.arg.com.sacamedelapuro.modelo;

/**
 * Created by lgambino on 08/02/2017.
 */

public class TipoServicio {

    Integer id;
    String nombre;


    public TipoServicio() {

    }

    public TipoServicio(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
