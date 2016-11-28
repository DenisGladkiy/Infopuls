package Metro.DataBase;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * Created by Денис on 11/19/16.
 */
public class ObjectWriter <T,K>{
    String URL = "jdbc:mysql://localhost/metro?user=root&password=root";
    Class<T> type;
    Class<K> key;

    public ObjectWriter(Class<T> type, Class<K> key) {
        this.type = type;
        this.key=key;
    }

    public void writeObject(T object){
        DbConnector connector = new DbConnector();
        ConnectionSource cs = connector.getConnectionSource(URL);
        Dao<T, K> objectDao;
        try {
            objectDao = DaoManager.createDao(cs, type);
            objectDao.create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
