package Clone;

import java.util.Random;

/**
 * Created by Денис on 10/1/16.
 */
public class Detail implements Cloneable {
    private int size = 10;

    public Detail(){
    }

    public Detail(Detail initDetail, int fail){
        this.size = initDetail.getSize() - fail;
    }

    public Detail clone(){
        Detail newDetail = null;
        Random random = new Random();
        if(random.nextInt(100) < 95) {
            try {
                newDetail = (Detail) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }return newDetail;
        }else{
            return new Detail(this, 2);
        }

    }

    public int getSize(){
        return this.size;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "size=" + size +" hashCode ="+ hashCode()+
                '}';
    }

}
