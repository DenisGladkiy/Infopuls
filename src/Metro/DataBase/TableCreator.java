package Metro.DataBase;

import Metro.SuperClass;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Денис on 11/18/16.
 */
public class TableCreator {
    String URL = "jdbc:mysql://localhost/metro?user=root&password=root";

    public void createTable(Class c){
        DbConnector connector = new DbConnector();
        ConnectionSource cs = connector.getConnectionSource(URL);
        try {
            //TableUtils.dropTable(cs, c, true);
            TableUtils.createTableIfNotExists(cs, c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Set<Class<?>> getClasses(File dir){
        Set<Class<?>> classes = new HashSet<>();
        if(dir.exists()){
            String[] files = dir.list();
            for(String name : files){
                if(name.endsWith(".class")){
                    name = name.substring(0, name.length() - 6);
                    try {
                        classes.add(Class.forName("Metro." + name));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return classes;
    }

    public void createTables(){
        File directory = new File("E:\\java\\InfoPulseUniver\\out\\production\\InfoPulseUniver\\Metro");
        Set<Class<?>> classes = getClasses(directory);
        for(Class<?> clazz : classes){
            if(clazz.getSuperclass().equals(SuperClass.class)){
                System.out.println(clazz.getName());
                Annotation[] annotations = clazz.getAnnotations();
                for(Annotation a : annotations){
                    if(a.annotationType().getSimpleName().equals("DatabaseTable")){
                        createTable(clazz);
                        System.out.println("Create table " + clazz.getName());
                    }
                    //System.out.println("Annotation " + a.annotationType().getSimpleName());
                }
            }
        }
    }

}
