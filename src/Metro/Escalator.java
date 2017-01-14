package Metro;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by Денис on 12/10/16.
 */
public class Escalator extends Thread {
    List<Passenger> passengers;
    List<Passenger> stationPassengers;
    public int id;

    public Escalator(List<Passenger> passengers, List<Passenger> stationPassengers) {
        this.passengers = passengers;
        this.stationPassengers = stationPassengers;
        Random random = new Random();
        id = random.nextInt(1000);
    }

    @Override
    public void run() {
        Passenger passenger = null;
        while(true) {
            try {
                synchronized (passengers) {
                    while(passengers.isEmpty()) {
                        passengers.wait();
                    }
                    passenger = passengers.remove(0);
                }
                stationPassengers.add(passenger);
                Thread.sleep(100);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
