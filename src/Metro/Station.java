package Metro;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Денис on 11/9/16.
 */
public class Station extends SuperClass {

    private Set<Passenger> passengers = new HashSet<>();

    private String name;

    public Station(){}

    public Station(String name){
        this.name = name;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' + " " +
                "passengers number " + passengers.size() + '}';
    }
}
