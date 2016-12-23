package Metro;

import Metro.DataBase.DbConnector;
import Metro.DataBase.DbCreator;
import Metro.DataBase.ObjectWriter;
import Metro.DataBase.TableCreator;
import Metro.Utils.DriversContainer;
import Metro.Utils.Reporter;
import Metro.Utils.TrainFactory;
import Metro.Utils.Utility;

import java.sql.Connection;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Денис on 10/1/16.
 */
public class Metro {

    private List<Driver> drivers;
    public static volatile BlockingQueue<Driver> reserveDrivers;
    private List<Train> trains;
    private volatile List<Passenger> passengers;
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
        Depot depot = metro.fillDepot(12, 50);
        metro.hireDrivers();
        metro.trains = metro.makeTrains(6, depot);
        metro.receivePassengers();
        metro.distributePassengers();
        metro.distributeTrains();
        metro.move();
        Reporter reporter = new Reporter(metro.lines);
        reporter.report();
    }

    private Depot fillDepot(int headCarrs, int midCarrs){
        Depot depot = new Depot();
        depot.makeCarriage(headCarrs, midCarrs);
        return depot;
    }

    private void hireDrivers(){
        //ObjectWriter<Driver, Integer> driverWriter = new ObjectWriter<>(Driver.class , Integer.class);
        DriversContainer container = new DriversContainer();
        drivers = container.getDrivers();
    }

    private List<Train> makeTrains(int trainNumber, Depot depot){
        ObjectWriter<Train, Integer> trainWriter = new ObjectWriter<>(Train.class , Integer.class);
        List<Train> trains = new ArrayList<>();
        TrainFactory trainFactory = new TrainFactory();
        for(int i = 0; i < trainNumber; i++){
            Train train = trainFactory.makeTrain(depot);
            if(!drivers.isEmpty()){
                train.setDriver(drivers.remove(0));
                trainWriter.writeObject(train);
            }else{
                train.setDriver(null);
            }
            trains.add(train);
        }
        reserveDrivers();
        return trains;
    }

    public static void swapDrivers(Train train) throws InterruptedException {
        synchronized (reserveDrivers){
            if(reserveDrivers.isEmpty()){
                reserveDrivers.add(train.getDriver());
                reserveDrivers.notifyAll();
                while(reserveDrivers.size() < 2){
                    reserveDrivers.wait();
                }
                System.out.println("res driver 1 " + reserveDrivers);
                train.setDriver(reserveDrivers.take());
            }else {
                reserveDrivers.add(train.getDriver());
                reserveDrivers.notifyAll();
                System.out.println("res driver 2 " + reserveDrivers);
                Driver d = reserveDrivers.take();
                train.setDriver(d);
            }
        }
    }

    public void receivePassengers(){
        //List<Passenger> passengersList = new ArrayList<>();
        passengers = new ArrayList<>();
        new Thread(new Runnable() {
            int i = 0;
            @Override
            public void run() {
                while(i < 200){
                    i++;
                    synchronized (passengers) {
                        passengers.add(new Passenger(i));
                        passengers.notifyAll();
                    }
                    //System.out.println(i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
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
        for(Line line : lines){
            for(Station station : line.getStations()){
                Escalator escalator = new Escalator(passengers, station.getPassengers());
                escalator.start();
            }
        }
    }

    private void distributeTrains(){
        int trainNumber = trains.size();
        int lineNumber = lines.size();
        int trainPerLine = trainNumber / lineNumber;
        Iterator iterator = lines.iterator();
        Line line = (Line)iterator.next();
        int i = 0;
        for(Train train : trains){
            train.setLine(line);
            i++;
            if(i == trainPerLine && iterator.hasNext()){
                line = (Line)iterator.next();
                i = 0;
            }
        }
    }

    private void move(){
        List<Thread> threads = new ArrayList<>();
        for(Train train : trains){
            Thread thread = new Thread(train);
            threads.add(thread);
            thread.start();
        }
        for(Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void reserveDrivers(){
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
        reserveDrivers = new PriorityBlockingQueue<>(5, comparator);
        if(!drivers.isEmpty()){
            for(Driver d : drivers){
                reserveDrivers.add(d);
            }
        }
        drivers.clear();
    }
}
