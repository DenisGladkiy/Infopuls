package Metro.Utils;

import Metro.Passenger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Денис on 11/29/16.
 */
public class Utility {

    public List<Passenger> getSubsetOfPassengers(List<Passenger> passengers, int number){
        List<Passenger> pass = new ArrayList<>();
        Passenger tempPass;
        synchronized (passengers) {
            for (int i = 0; i < number; i++) {
                tempPass = passengers.remove(0);
                pass.add(tempPass);
            }
        }
        return pass;
    }
}
