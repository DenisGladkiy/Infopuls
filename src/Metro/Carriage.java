package Metro;

import Metro.Utils.Utility;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.*;
import java.util.concurrent.CountDownLatch;

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

    private List<Passenger> passengers = new ArrayList<>();

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

    public List<Passenger> addPassengers(List<Passenger> p){
        int totalPass = passengers.size() + p.size();
        if(totalPass > sitsNumber){
            return p;
        }else{
            passengers.addAll(p);
            passCounter += p.size();
            return new ArrayList<>();
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

    public void exchangePassengers(Station station, CountDownLatch finish){
        Random random = new Random();
        List<Passenger> exitPassengers = new ArrayList<>();
        Utility utility = new Utility();
        List<Passenger> stationPassengers = station.getPassengers();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int number = stationPassengers.size() / 5;
                if (number == 0) {
                    number = 1;
                }
                List<Passenger> redundantPass = null;
                int carrPassNumber = passengers.size();
                if (carrPassNumber > 0) {
                    exitPassengers.addAll(utility.getSubsetOfPassengers(passengers, random.nextInt(carrPassNumber + 1)));
                }
                synchronized (stationPassengers) {
                    if (stationPassengers.size() > 0) {
                        if (stationPassengers.size() == 2) {
                            redundantPass = addPassengers(utility.getSubsetOfPassengers(stationPassengers, 2));
                            stationPassengers.addAll(redundantPass);
                        } else {
                            int rand = random.nextInt(number + 1);
                            redundantPass = addPassengers(utility.getSubsetOfPassengers(stationPassengers, rand));
                            stationPassengers.addAll(redundantPass);
                        }
                    }
                }
                //System.out.println("Wagon " + getId() + " passengers " + passengers.size());
                exitPassengers.clear();
                finish.countDown();
            }
        }).start();
    }

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

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getPassCounter() {
        return passCounter;
    }

    @Override
    public String toString() {
        return type.toString() +
                " / current pass = " + passengers.size() +
                " / total pass = " + passCounter;
    }
}
