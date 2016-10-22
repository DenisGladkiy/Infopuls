package List;

/**
 * Created by Денис on 10/21/16.
 */
public class MyList<T>{
    private T[] array;

    public MyList(){
        array = new T[0];
    }

    public void add(T arg){}

    public void add(int i, T arg){}

    public T remove(int i){return null;}

    public T get(int i){return null;}

    public int size(){return 0;}

    public int indexOf(T arg){return 0;}

    public boolean contains(T arg){return false;}

    public void clear(){}

}
