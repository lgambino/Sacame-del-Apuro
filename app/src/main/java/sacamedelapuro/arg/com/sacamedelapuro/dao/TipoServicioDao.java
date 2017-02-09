package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.content.Context;
import android.database.Cursor;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.TipoServicio;

/**
 * Created by lgambino on 08/02/2017.
 */

public class TipoServicioDao extends GenericDaoImpl<TipoServicio>{

    public TipoServicioDao(Context context) {
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
    public void save(TipoServicio tipoServicio) {
        super.save(tipoServicio);
    }

    @Override
    public void update(TipoServicio tipoServicio) {
        super.update(tipoServicio);
    }
}
