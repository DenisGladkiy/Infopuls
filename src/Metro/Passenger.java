package Metro;

/**
 * Created by Денис on 11/9/16.
 */
public class Passenger extends SuperClass {

    int passengerId;

    public Passenger(){}

    public Passenger(int passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public String toString() {
        return "passengerId=" + passengerId;
    }
}
