package Metro.Utils;

import Metro.Passenger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Денис on 11/29/16.
 */
public class Utility {

    public Set<Passenger> getSubsetOfPassengers(Set<Passenger> passengers, int number){
        Set<Passenger> pass = new HashSet<>();
        Passenger tempPass;
        for(int i = 0; i < number; i++){
            tempPass = passengers.iterator().next();
            pass.add(tempPass);
            passengers.remove(tempPass);
        }
        return pass;
    }
}
