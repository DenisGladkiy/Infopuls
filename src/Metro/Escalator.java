package Metro;

import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by Денис on 12/10/16.
 */
public class Escalator extends Thread {
    List<Passenger> passengers;
    List<Passenger> passengersSet;
    private int id;

    public Escalator(List<Passenger> passengers, List<Passenger> passengersSet) {
        this.passengers = passengers;
        this.passengersSet = passengersSet;
        Random random = new Random();
        id = random.nextInt(1000);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Passenger passenger = null;
                synchronized (passengers) {
                    while(passengers.isEmpty()) {
                        passengers.wait();
                    }
                    passenger = passengers.remove(0);
                }
                passengersSet.add(passenger);
                //System.out.println("escalator  " + id + " " + passenger);
                //System.out.println("station  " + passengersSet.size());
                //Thread.sleep(500);
            } catch (InterruptedException e){
            e.printStackTrace();
            }
        }
    }
}
