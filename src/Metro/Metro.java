package Metro;

import Metro.DataBase.DbConnector;
import Metro.DataBase.DbCreator;
import Metro.DataBase.ObjectWriter;
import Metro.DataBase.TableCreator;

import java.io.*;
import java.sql.Connection;
import java.util.*;

/**
 * Created by Денис on 10/1/16.
 */
public class Metro {

    //Depot depot;
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
        Depot depot = metro.fillDepot(10, 50);
        metro.drivers = metro.hireDrivers();
        metro.trains = metro.makeTrains(5, depot);
        System.out.println(metro.trains);
        metro.receivePassengers();
        System.out.println("Passengers " + metro.passengers.size());
        metro.distributePassengers();
        System.out.println("Passengers " + metro.passengers.size());
        System.out.println("Lines " + metro.lines);
        //metro.printTrains(metro.trains);
        for(int i = 10; i > 0; i--){
            metro.runTrains();
            metro.swapDrivers();
            System.out.println(metro.trains);
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
        //driver = new Driver("Bill");
        //driverWriter.writeObject(driver);
        //drivers.add(driver);
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
        for(int i = 0; i < 100; i++){
            passengers.add(new Passenger(i));
        }
    }

    public void arrangeLines(){
        lines = new ArrayList<>();
        String[] names = {"First", "Second", "Third", "Forth", "Fifth", "Sixth", "Seventh", "Eighth", "Ninth"};
        List<String> stationNames = new ArrayList(Arrays.asList(names));
        lines.add(makeLine("Red", stationNames.subList(0,3)));
        lines.add(makeLine("Blue", stationNames.subList(3,6)));
        lines.add(makeLine("Green", stationNames.subList(6,9)));
    }

    public Line makeLine(String lineName, List<String> names){
        List<Station> stations = new ArrayList<>();
        for(String s : names){
            stations.add(new Station(s));
        }
        return new Line(lineName, stations);
    }

    public void distributePassengers(){
        Random rnd = new Random();
        for(Line line : lines){
            for(Station station : line.getStations()){
                station.setPassengers(getSubsetOfPassengers(rnd.nextInt(15)));
            }
        }
    }

    private Set<Passenger> getSubsetOfPassengers(int number){
        Set<Passenger> pass = new HashSet<>();
        Passenger tempPass;
        for(int i = 0; i < number; i++){
            tempPass = passengers.iterator().next();
            pass.add(tempPass);
            passengers.remove(tempPass);
        }
        return pass;
    }

//    private void printTrains(List<Train> t){
//        try(FileOutputStream outputStream = new FileOutputStream(new File("Metro.dat"));
//            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
//            BufferedWriter writer = new BufferedWriter(streamWriter);) {
//            for(Train train : t){
//                printTrain(writer, train);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } ;
//    }

    //    private void printTrain(BufferedWriter bw, Train t) throws IOException {
//        bw.write(t.getTrain().toString() + " | " + t.getDriver());
//        bw.newLine();
//    }
//



}
