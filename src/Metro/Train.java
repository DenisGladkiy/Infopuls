package Metro;

import Metro.Utils.Utility;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.*;

/**
 * Created by Денис on 10/1/16.
 */
@DatabaseTable(tableName = "train")
public class Train extends SuperClass {

    int[] experience = {-2,-1,0,1,2};

    @DatabaseField(generatedId = true)
    private int train_id;

//    @ForeignCollectionField()
//    private Collection<Carriage> train;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Carriage carr1;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Carriage carr2;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Carriage carr3;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Carriage carr4;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Carriage carr5;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Driver driver;


    public Train(){}

    private Train(Builder builder){
        carr1 = builder.train.get(0);
        carr2 = builder.train.get(1);
        carr3 = builder.train.get(2);
        carr4 = builder.train.get(3);
        carr5 = builder.train.get(4);
        //train = builder.train;
    }

    public void run(){
        Random rnd = new Random();
        int exp = experience[rnd.nextInt(5)];
        int currentExp = driver.getExperience();
        driver.setExperience(currentExp + exp);
    }

    public void stop(Station station){
        Random random = new Random();
        Utility utility = new Utility();
        Set<Passenger> carrPass = null;
        int carrPassNumber;
        if(station != null) {
            Collection<Carriage> carriages = getTrain();
            Set<Passenger> stationPassengers = station.getPassengers();
            Set<Passenger> redundantPass = null;
            System.out.println(station.getName() + "Station pass before = " + stationPassengers.size());
            Set<Passenger> trainPassengers = new HashSet<>();
            int number = stationPassengers.size() / 5;
            if(number == 0){
                number = 1;
            }
            for(Carriage car : carriages){
                carrPass = car.getPassengers();
                carrPassNumber = carrPass.size();
                if(carrPassNumber > 0) {
                    trainPassengers.addAll(utility.getSubsetOfPassengers(carrPass, random.nextInt(carrPassNumber + 1)));
                }
                if(stationPassengers.size() > 0){
                    if(stationPassengers.size() == 2){
                        redundantPass = car.addPassengers(utility.getSubsetOfPassengers(stationPassengers, 2));
                        stationPassengers.addAll(redundantPass);
                    }else {
                        int rand = random.nextInt(number + 1);
                        redundantPass = car.addPassengers(utility.getSubsetOfPassengers(stationPassengers, rand));
                        stationPassengers.addAll(redundantPass);
                    }
                }else{
                    break;
                }
            }
            stationPassengers.addAll(trainPassengers);
            System.out.println("Station pass after = " + stationPassengers.size());
            System.out.println(station.getName() + "  " + this.toString());
        }else{
            System.out.println("no station");
        }
    }

    public Collection<Carriage> getTrain() {
        List<Carriage> train = new ArrayList<>();
        train.add(0, carr1);
        train.add(1, carr2);
        train.add(2, carr3);
        train.add(3, carr4);
        train.add(4, carr5);
        return train;
    }

    public void setTrain(List<Carriage> train) {
        carr1 = train.get(0);
        carr2 = train.get(1);
        carr3 = train.get(2);
        carr4 = train.get(3);
        carr5 = train.get(4);
    }

    public static class Builder{
        private List<Carriage> train;

        public Builder buildTrain(List<Carriage> train){
            this.train = train;
            return this;
        }

        public Train build(){
            if(this.train.size() == 5) {
                if(this.train.get(0).getType() == CarriageType.HEAD
                        && this.train.get(4).getType() == CarriageType.HEAD) {
                    return new Train(this);
                }else{
                    return null;
                }
            }else{
                return null;
            }
        }
    }

    public void setDriver(Driver driver){
        this.driver = driver;
    }

    public Driver getDriver(){ return driver; }

    @Override
    public String toString() {
        return "Train{" +
                "train_id=" + train_id +
                ", carr1=" + carr1 +
                ", carr2=" + carr2 +
                ", carr3=" + carr3 +
                ", carr4=" + carr4 +
                ", carr5=" + carr5 +
                ", driver=" + driver +
                '}' + "\n";
    }
}
