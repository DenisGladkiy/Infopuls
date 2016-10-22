package List;

/**
 * Created by Денис on 10/21/16.
 */
public class MyList<T> implements Comparable<MyList>{
    private Node<T>  first;
    private Node<T>  next;
    private Node<T>  current;
    private Node<T>  last;
    private int size;

    public MyList(){
        first = new Node<T>(null);
        last = first;
    }

    public MyList(T[] arr){
        first = new Node<T>(null);
        last = first;
        for(T t : arr){
            add(t);
        }
    }

    public void add(T arg){
        Node<T>  newNode = new Node<T> (arg, null);
        last.setNext(newNode);
        last = newNode;
        size++;
    }

    public void add(int i, T arg){
        Node<T>  newNode = new Node<T> (arg, null);
        current = getNodeByIndex(i - 1);
        next = current.getNext();
        current.setNext(newNode);
        newNode.setNext(next);
        size++;
    }

    public T remove(int i){
        current = getNodeByIndex(i - 1);
        next = current.getNext();
        if(next == last) last = current;
        current.setNext(next.getNext());
        size--;
        return next.getValue();
    }

    public T get(int i){
        next = getNodeByIndex(i);
        return next.getValue();
    }

    public int size(){return size;}

    public int indexOf(T arg){
        next = first;
        int i = 0;
        while (next.getNext() != null) {
            next = next.getNext();
            if (next.getValue().equals(arg)) {
                return i;
            }else{i++;}
        }
        return -1;
    }

    public boolean contains(T arg){
        next = first;
        while (next.getNext() != null) {
            next = next.getNext();
            if (next.getValue().equals(arg)) {
                return true;
            }
        }
        return false;
    }

    public void addList(MyList<? extends T> list){
        for(int i = 0; i < list.size(); i++){
            add(list.get(i));
        }
    }

    public void clear(){
        size = 0;
        first.setNext(null);
    }

    private Node<T>  getNodeByIndex(int index) {
        next = first;
        for (int i = 0; i <= index; i++) {
            next = next.getNext();
        }
        return next;
    }

    public String toString() {
        String value = "";
        next = first;
        while (next.getNext() != null) {
            value += next.getNext().getValue().toString() + "; ";
            next = next.getNext();
        }
        return value;
    }

    @Override
    public int compareTo(MyList list) {
        if(size > list.size()){
            return 1;
        }else if(size < list.size()){
            return -1;
        }else {
            return 0;
        }
    }
}
