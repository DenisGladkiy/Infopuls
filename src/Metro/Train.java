package Metro;

import Metro.Utils.Utility;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.*;

/**
 * Created by Денис on 10/1/16.
 */
@DatabaseTable(tableName = "train")
public class Train extends SuperClass implements Runnable {

    int[] experience = {-1,0,1,2,3};

    @DatabaseField(generatedId = true)
    private int train_id;

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

    private Line line;

    public Train(){}

    private Train(Builder builder){
        carr1 = builder.train.get(0);
        carr2 = builder.train.get(1);
        carr3 = builder.train.get(2);
        carr4 = builder.train.get(3);
        carr5 = builder.train.get(4);
        //train = builder.train;
    }

    @Override
    public void run() {
        List<Station> stations = new ArrayList<>(line.getStations());
        Station station = stations.get(0);
        int i = 0;
        while(true) {
            while(station.isSegmentBusy()){
                trySleep(10);
            }
            station.setSegmentBusy(true);
            runTrain();
            trySleep(500);
            station.setSegmentBusy(false);
            while(station.isBusy()){
                trySleep(10);
            }
            station.setBusy(true);
            stopTrain(station);
            station.setBusy(false);
            Collections.rotate(stations, -1);
            station = stations.get(0);
            i++;
            if(i == 10){
                try {
                    swapDrivers();
                    i = 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void runTrain(){
        Random rnd = new Random();
        int exp = experience[rnd.nextInt(5)];
        int currentExp = driver.getExperience();
        driver.setExperience(currentExp + exp);
    }

    public void stopTrain(Station station){
        //if(this.getLine().getName().equals("Red"))
        System.out.println("station  "+station.getName() + ", train " + this.getTrainId()+ " " +this.getDriver());
        Random random = new Random();
        Utility utility = new Utility();
        List<Passenger> carrPass = null;
        int carrPassNumber;
        Collection<Carriage> carriages = getTrain();
        List<Passenger> stationPassengers = station.getPassengers();
        synchronized (stationPassengers) {
            List<Passenger> redundantPass = null;
            List<Passenger> trainPassengers = new ArrayList<>();
            int number = stationPassengers.size() / 5;
            if (number == 0) {
                number = 1;
            }
            for (Carriage car : carriages) {
                carrPass = car.getPassengers();
                carrPassNumber = carrPass.size();
                if (carrPassNumber > 0) {
                    trainPassengers.addAll(utility.getSubsetOfPassengers(carrPass, random.nextInt(carrPassNumber + 1)));
                }
                if (stationPassengers.size() > 0) {
                    if (stationPassengers.size() == 2) {
                        redundantPass = car.addPassengers(utility.getSubsetOfPassengers(stationPassengers, 2));
                        stationPassengers.addAll(redundantPass);
                    } else {
                        int rand = random.nextInt(number + 1);
                        redundantPass = car.addPassengers(utility.getSubsetOfPassengers(stationPassengers, rand));
                        stationPassengers.addAll(redundantPass);
                    }
                } else {
                    break;
                }
            }
            trainPassengers.clear();
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

    public int getTrainId(){
        return train_id;
    }

    public int getTotalPassengers(){
        int total = 0;
        Collection<Carriage> carrs = getTrain();
        for(Carriage c : carrs){
            total += c.getPassCounter();
        }
        return total;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Carriage getCarr1() {
        return carr1;
    }

    public Carriage getCarr2() {
        return carr2;
    }

    public Carriage getCarr3() {
        return carr3;
    }

    public Carriage getCarr4() {
        return carr4;
    }

    public Carriage getCarr5() {
        return carr5;
    }

    @Override
    public String toString() {
        return "Train{" + line.getName() + " "+
                "train_id=" + train_id + "\n" +
                ", carr1=" + carr1 + "\n" +
                ", carr2=" + carr2 + "\n" +
                ", carr3=" + carr3 + "\n" +
                ", carr4=" + carr4 + "\n" +
                ", carr5=" + carr5 + "\n" +
                ", driver=" + driver +
                '}' + "\n";
    }

    private void trySleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void swapDrivers() throws InterruptedException {
        synchronized (Metro.reserveDrivers){
            Metro.reserveDrivers.put(getDriver());
            Metro.reserveDrivers.notifyAll();
            int benchSize = Metro.reserveDrivers.size();
            while(Metro.reserveDrivers.size() == benchSize) {
                Metro.reserveDrivers.wait();
            }
            setDriver(Metro.reserveDrivers.take());
            System.out.println("res drivers " + this.getTrainId()+ "  " + Metro.reserveDrivers);
        }
    }
}
