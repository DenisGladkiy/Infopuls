package Metro;

import Metro.DataBase.DbConnector;
import Metro.DataBase.DbCreator;
import Metro.DataBase.ObjectWriter;
import Metro.DataBase.TableCreator;
import Metro.Utils.TrainFactory;
import Metro.Utils.Utility;

import java.sql.Connection;
import java.util.*;

/**
 * Created by Денис on 10/1/16.
 */
public class Metro {

    private Queue<Driver> drivers;
    private List<Train> trains;
    private Set<Passenger> passengers;
    private List<Line> lines;

    public static void main(String[] args) {
        DbConnector connector = new DbConnector();
        Connection connection = connector.connectDb("");
        DbCreator creator = new DbCreator();
        creator.createDb(connection, "metro");
        TableCreator tCreator = new TableCreator();
        tCreator.createTables();
        Metro metro = new Metro();
        metro.arrangeLines();
        System.out.println(metro.lines);
        Depot depot = metro.fillDepot(12, 50);
        metro.drivers = metro.hireDrivers();
        metro.trains = metro.makeTrains(6, depot);
        System.out.println(metro.trains);
        metro.receivePassengers();
        System.out.println("Passengers " + metro.passengers.size());
        metro.distributePassengers();
        metro.distributeTrains();
        System.out.println("Trains  " + metro.trains);
        System.out.println("Lines " + metro.lines);
        for(int i = 10; i > 0; i--){
            metro.move();
        }
    }

    private Depot fillDepot(int headCarrs, int midCarrs){
        Depot depot = new Depot();
        depot.makeCarriage(headCarrs, midCarrs);
        return depot;
    }

    private Queue<Driver> hireDrivers(){
        ObjectWriter<Driver, Integer> driverWriter = new ObjectWriter<>(Driver.class , Integer.class);
        Comparator<Driver> comparator = new Comparator<Driver>() {
            @Override
            public int compare(Driver d1, Driver d2) {
                if(d1.getExperience() > d2.getExperience()){
                    return 1;
                }else if(d1.getExperience() < d2.getExperience()){
                    return -1;
                }
                return 0;
            }
        };
        Queue<Driver> drivers = new PriorityQueue<>(comparator);
        Driver driver = new Driver("Alex");
        driverWriter.writeObject(driver);
        drivers.add(driver);
        driver = new Driver("Mike");
        driverWriter.writeObject(driver);
        drivers.add(driver);
        driver = new Driver("Nike");
        driverWriter.writeObject(driver);
        drivers.add(driver);
        driver = new Driver("Homer");
        driverWriter.writeObject(driver);
        drivers.add(driver);
        driver = new Driver("Bill");
        driverWriter.writeObject(driver);
        drivers.add(driver);
        driver = new Driver("John");
        driverWriter.writeObject(driver);
        drivers.add(driver);
        return drivers;
    }

    private List<Train> makeTrains(int trainNumber, Depot depot){
        ObjectWriter<Train, Integer> trainWriter = new ObjectWriter<>(Train.class , Integer.class);
        List<Train> trains = new ArrayList<>();
        TrainFactory trainFactory = new TrainFactory();
        for(int i = 0; i < trainNumber; i++){
            Train train = trainFactory.makeTrain(depot);
            //train.setDriver(drivers.get(i));
            if(!drivers.isEmpty()){
                train.setDriver(drivers.remove());
                trainWriter.writeObject(train);
            }else{
                train.setDriver(null);
            }
            trains.add(train);
        }
        return trains;
    }

    public void runTrains(){
        for(Train tr : trains){
            if(tr.getDriver() != null){
                tr.run();
                drivers.add(tr.getDriver());
            }
        }
    }

    public void swapDrivers(){
        for(Train tr : trains){
            if(!drivers.isEmpty()) {
                tr.setDriver(drivers.remove());
            }
        }
    }

    public void receivePassengers(){
        passengers = new HashSet<>();
        for(int i = 0; i < 200; i++){
            passengers.add(new Passenger(i));
        }
    }

    public void arrangeLines(){
        lines = new ArrayList<>();
        String[] names1 = {"First", "Second", "Third", "Forth"};
        String[] names2 = {"Fifth", "Sixth", "Seventh", "Eighth"};
        String[] names3 = {"Ninth", "Tenth", "Eleventh", "Twelfth"};
        lines.add(makeLine("Red", Arrays.asList(names1)));
        lines.add(makeLine("Blue", Arrays.asList(names2)));
        lines.add(makeLine("Green", Arrays.asList(names3)));
    }

    private Line makeLine(String lineName, List<String> names){
        List<Station> stations = new ArrayList<>();
        for(String s : names){
            stations.add(new Station(s));
        }
        return new Line(lineName, stations);
    }

    private void distributePassengers(){
        Utility utility = new Utility();
        Random rnd = new Random();
        for(Line line : lines){
            for(Station station : line.getStations()){
                station.setPassengers(utility.getSubsetOfPassengers(passengers, rnd.nextInt(16)));
            }
        }
    }

    private void distributeTrains(){
        int trainNumber = trains.size();
        int lineNumber = lines.size();
        int trainPerLine = trainNumber / lineNumber;

        for(Line line : lines){
            List<Train> trainsForLine = new ArrayList<>();
            for(int i = 0;i < trainPerLine;i++){
                if(trains.size() == 1){
                    trainsForLine.add(trains.remove(0));
                }else {
                    trainsForLine.add(trains.remove(i));
                }
            }
            line.setTrains(trainsForLine);
        }
    }

    private void move(){
        for(Line line : lines){
            List<Train> trains = line.getTrains();
            List<Station> stations = line.getStations();
            int iterations = trains.size() + trains.size();
            for(int i = 0; i < iterations; i++) {
                int j = i;
                for (Train train : trains) {
                    train.run();
                    if(j < 0){
                        train.stop(null);
                    }else {
                        train.stop(stations.get(j));
                    }
                    train.run();
                    j--;
                }
                //Collections.rotate(stations,-1);
                System.out.println("\n");
            }
        }
    }

//    private Set<Passenger> getSubsetOfPassengers(Set<Passenger> passengers, int number){
//        Set<Passenger> pass = new HashSet<>();
//        Passenger tempPass;
//        for(int i = 0; i < number; i++){
//            tempPass = passengers.iterator().next();
//            pass.add(tempPass);
//            passengers.remove(tempPass);
//        }
//        return pass;
//    }
}
