package Metro;

import java.util.List;

/**
 * Created by Денис on 11/9/16.
 */
public class Line extends SuperClass {

    private String name;

    private List<Station> stations;

    private List<Train> trains;

    public Line(){}

    public Line(String name, List<Station> stations){
        this.name = name;
        this.stations = stations;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "Line{" +
                "name='" + name + '\'' +
                ", stations=" + stations +
                '}';
    }
}
