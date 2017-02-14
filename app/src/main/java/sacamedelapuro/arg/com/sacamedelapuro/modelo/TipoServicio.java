package sacamedelapuro.arg.com.sacamedelapuro.modelo;

import java.io.Serializable;

/**
 * Created by lgambino on 08/02/2017.
 */

public class TipoServicio implements Serializable{

    private Integer id;
    private String nombre;


    public TipoServicio() {

    }

    public TipoServicio(Integer id) {
        this.id = id;
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
