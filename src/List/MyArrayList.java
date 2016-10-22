package List;

import java.util.Arrays;

/**
 * Created by Денис on 10/22/16.
 */
public class MyArrayList<T> implements Comparable<MyArrayList> {
    private T[] array;

    public MyArrayList(T[] array){
        this.array = array;
    }

    public void add(T arg){
        array = Arrays.copyOf(array, array.length+1);
        array[array.length-1] = arg;
    }

    public T get(int i){
        return array[i];
    }

    public int size(){
        return array.length;
    }

    public void addList(MyArrayList<? extends T> list){
        for(int i = 0; i < list.size(); i++){
            add(list.get(i));
        }
    }

    public boolean contains(T arg){
        for(T t : array){
            if(t.equals(arg)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(T t : array){
            builder.append(t.toString() + "; ");
        }
        return builder.toString();
    }

    @Override
    public int compareTo(MyArrayList o) {
        if(array.length > o.size()){
            return 1;
        }else if(array.length < o.size()){
            return -1;
        }else {
            return 0;
        }
    }
}
