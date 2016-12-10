package Metro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Денис on 10/1/16.
 */
@DatabaseTable(tableName = "carriage")
public class Carriage extends SuperClass implements Cloneable {

//    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
//    private Train train;

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private int sitsNumber;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private CarriageType type;

    private Set<Passenger> passengers = new HashSet<>();

    private int passCounter = 0;

    public Carriage(){}

    public Carriage(Carriage origin){
        type = origin.getType();
        sitsNumber = origin.getSitsNumber();
    }

    public Carriage(CarriageType type){
        this.type = type;
        if(type == CarriageType.HEAD){
            sitsNumber = 40;
        }else{
            sitsNumber = 42;
        }
    }

    public Set<Passenger> addPassengers(Set<Passenger> p){
        int totalPass = passengers.size() + p.size();
        if(totalPass > sitsNumber){
            return p;
        }else{
            passengers.addAll(p);
            passCounter += p.size();
            return new HashSet<>();
        }
    }

    public void removePassengers(int number){
        Passenger passenger;
        for(int i = 0; i < number; i++){
            if(passengers.isEmpty()){
                return;
            }
            passenger = passengers.iterator().next();
            passengers.remove(passenger);
        }
    }

//    public Carriage clone() {
//        Carriage car = null;
//        try {
//            car = (Carriage) super.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return car;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSitsNumber() {
        return sitsNumber;
    }

    public void setSitsNumber(int sitsNumber) {
        this.sitsNumber = sitsNumber;
    }

    public void setType(CarriageType type) {
        this.type = type;
    }

    public CarriageType getType(){
        return type;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getPassCounter() {
        return passCounter;
    }

    //    public Train getTrain() {
//        return train;
//    }
//
//    public void setTrain(Train train) {
//        this.train = train;
//    }

    @Override
    public String toString() {
        return type.toString() +
                " / current pass = " + passengers.size() +
                " / total pass = " + passCounter;
    }
}
