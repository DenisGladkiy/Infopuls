package Metro;

/**
 * Created by Денис on 10/1/16.
 */
public class Carriage implements Cloneable {
    private int id;
    private int sitsNumber;
    private CarriageType type;

    public Carriage(CarriageType type){
        this.type = type;
    }

    public Carriage clone() {
        Carriage car = null;
        try {
            car = (Carriage) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return car;
    }

    public CarriageType getType(){
        return type;
    }



}
