package Metro.Utils;

import Metro.Driver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 12/21/16.
 */
public class DriversContainer {

    private List<Driver> drivers = new ArrayList<>();

    public List<Driver> getDrivers() {
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
        driver = new Driver("John");
        drivers.add(driver);
        driver = new Driver("Philip");
        drivers.add(driver);
        driver = new Driver("Dan");
        drivers.add(driver);
        driver = new Driver("Jack");
        drivers.add(driver);
        return drivers;
    }
}
