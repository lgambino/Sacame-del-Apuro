package sacamedelapuro.arg.com.sacamedelapuro.dao;


import android.content.Context;
import android.database.Cursor;

import sacamedelapuro.arg.com.sacamedelapuro.modelo.Usuario;

/**
 * Created by lgambino on 08/02/2017.
 */

public class UsuarioDao extends GenericDaoImpl<Usuario> {

    public UsuarioDao(Context context) {
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
    public void save(Usuario usuario) {
        super.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        super.update(usuario);
    }
}
