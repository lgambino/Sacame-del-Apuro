package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.content.Context;
import android.database.Cursor;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Servicio;

/**
 * Created by lgambino on 08/02/2017.
 */

public class ServicioDao extends GenericDaoImpl<Servicio>{


    public ServicioDao(Context context) {
        super(context);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Cursor get(Integer id) {
        return super.get(id);
    }

    @Override
    public Cursor getAll() {
        return super.getAll();
    }

    @Override
    public void save(Servicio servicio) {
        super.save(servicio);
    }

    @Override
    public void update(Servicio servicio) {
        super.update(servicio);
    }
}
