package Metro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 10/1/16.
 */
public class Metro {

    //Depot depot;
    //List<Driver> drivers;
    private List<Train> trains;

    public static void main(String[] args) {
        Metro metro = new Metro();
        Depot depot = metro.fillDepot(10, 50);
        List<Driver> drivers = metro.hireDrivers();
        metro.trains = metro.makeTrains(5, depot, drivers);
        System.out.println(metro.trains);
        metro.printTrains(metro.trains);
    }

    private Depot fillDepot(int headCarrs, int midCarrs){
        Depot depot = new Depot();
        depot.makeCarriage(headCarrs, midCarrs);
        return depot;
    }

    private List<Driver> hireDrivers(){
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

    private List<Train> makeTrains(int trainNumber, Depot depot, List<Driver> drivers){
        List<Train> trains = new ArrayList<>();
        TrainFactory trainFactory = new TrainFactory();
        for(int i = 0; i < trainNumber; i++){
            Train train = trainFactory.makeTrain(depot);
            train.setDriver(drivers.get(i));
            trains.add(train);
        }
        return trains;
    }

    private void printTrains(List<Train> t){
        try(FileOutputStream outputStream = new FileOutputStream(new File("Metro.dat"));
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(streamWriter);) {
            for(Train train : t){
                printTrain(writer, train);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }

    private void printTrain(BufferedWriter bw, Train t) throws IOException {
        bw.write(t.getTrain().toString() + " | " + t.getDriver());
        bw.newLine();
    }
}
