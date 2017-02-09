package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.database.Cursor;

import java.util.List;

/**
 * Created by lgambino on 09/02/2017.
 */

public interface GenericDao<T> {

    void delete(Integer id);

    Cursor get(Integer id);

    Cursor getAll();

    void save(T t);

    void update(T t);
}
