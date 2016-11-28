package Metro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Денис on 10/14/16.
 */

@DatabaseTable(tableName = "driver")
public class Driver extends SuperClass {

    @DatabaseField(generatedId = true)
    private int driverId;

    @DatabaseField(canBeNull = false,useGetSet = true)
    private String name;

    private int experience = 50;

    public Driver(){}

    public Driver(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDriverId() { return driverId; }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getExperience() { return experience; }

    public void setExperience(int experience) { this.experience = experience; }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + "', " +
                "exp= " + experience + '\''+
                '}';
    }
}
