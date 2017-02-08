package sacamedelapuro.arg.com.sacamedelapuro.modelo;

/**
 * Created by lgambino on 08/02/2017.
 */

public class Contacto {

    Integer id;
    String empresa;
    String num;
    String email;


    public Contacto() {

    }

    public Contacto(Integer id, String empresa, String num, String email) {
        this.id = id;
        this.empresa = empresa;
        this.num = num;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
