package Metro;

import java.util.*;

/**
 * Created by Денис on 11/9/16.
 */
public class Station extends SuperClass {

    private List<Passenger> passengers = new ArrayList<>();

    private String name;

    private boolean isBusy = false;

    private boolean segmentBusy = false;

    public Station(){}

    public Station(String name){
        this.name = name;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void addPassenger(Passenger passenger){
        passengers.add(passenger);
    }

    public String getName() {
        return name;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void setSegmentBusy(boolean segment){ segmentBusy = segment; }

    public boolean isBusy(){
        return isBusy;
    }

    public boolean isSegmentBusy(){ return segmentBusy; }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' + " " +
                "passengers number " + passengers.size() + '}' + "\n";
    }
}
