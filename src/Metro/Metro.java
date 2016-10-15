package Metro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 10/1/16.
 */
public class Metro {

    //Depot depot;
    //List<Driver> drivers;

    public static void main(String[] args) {
        Metro metro = new Metro();
        Depot depot = metro.fillDepot(10, 50);
        List<Driver> drivers = metro.hireDrivers();
        List<Train> trains = metro.makeTrains(5, depot, drivers);
        System.out.println(trains);
    }

    public Depot fillDepot(int headCarrs, int midCarrs){
        Depot depot = new Depot();
        depot.makeCarriage(headCarrs, midCarrs);
        return depot;
    }

    public List<Driver> hireDrivers(){
        List<Driver> drivers = new ArrayList<>();
        Driver driver = new Driver("Alex");
        drivers.add(driver);
        driver = new Driver("Mike");
        drivers.add(driver);
        driver = new Driver("Nike");
        drivers.add(driver);
        driver = new Driver("Homer");
        drivers.add(driver);
        driver = new Driver("Bill");
        drivers.add(driver);
        return drivers;
    }

    public List<Train> makeTrains(int trainNumber, Depot depot, List<Driver> drivers){
        List<Train> trains = new ArrayList<>();
        TrainFactory trainFactory = new TrainFactory();
        for(int i = 0; i < trainNumber; i++){
            Train train = trainFactory.makeTrain(depot);
            train.setDriver(drivers.get(i));
            trains.add(train);
        }
        return trains;
    }
}
