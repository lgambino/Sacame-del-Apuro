package sacamedelapuro.arg.com.sacamedelapuro.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

/**
 * Created by lgambino on 09/02/2017.
 */

public interface GenericDao<T> {

    void delete(String tabla, Integer id);

    void escribir();

    Cursor get(String tabla, String[] campos, Integer id);

    Cursor getAll(String tabla);

    void leer();

    void save(String tabla, ContentValues valores);

    void update(String tabla, ContentValues valores, Integer id);
}
