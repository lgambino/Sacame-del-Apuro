package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.provider.BaseColumns;


/**
 * Created by lgambino on 08/02/2017.
 */

public class BDMetadata {

    public static final int VERSION_BD = 1;
    public static final String NOMBRE_BD= "sacamedelapuro.db";
    public static final String TABLA_CONTACTO= "CONTACTO";
    public static final String TABLA_ROL= "ROL";
    public static final String TABLA_SERVICIO= "SERVICIO";
    public static final String TABLA_TIPO_SERVICIO= "TIPO_SERVICIO";
    public static final String TABLA_UBICACION= "UBICACION";
    public static final String TABLA_USUARIO= "USUARIO";



    public static class TablaContactoMetadata implements BaseColumns {
        public static final String EMPRESA = "";
        public static final String NUM = "";
        public static final String EMAIL = "";

    }

    public static class TablaRolMetadata implements BaseColumns {
        public static final String NOMBRE = "NOMBRE";
    }

    public static class TablaServicioMetadata implements BaseColumns {

    }

    public static class TablaTipoServicioMetadata implements BaseColumns {

    }

    public static class TablaUbicacionMetadata implements BaseColumns {

    }

    public static class TablaUsuarioMetadata implements BaseColumns {

    }
}
