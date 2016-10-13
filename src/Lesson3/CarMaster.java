package Lesson3;

/**
 * Created by Денис on 10/8/16.
 */
public class CarMaster {

    public Car.Builder makeCarBuilder(String name){
        return new Car.Builder(name);
    }

    public Car.Builder addDetails(Car.Builder builder, Detail body, Detail wheels, Detail seats){
        return builder.buildBody(body).addWheels(wheels).addSeats(seats);
    }

    public Car makeCar(Car.Builder builder, Detail doors, String color){
        Car car = builder.addDoors(doors).paint(color).build();
        return car;
    }

}
