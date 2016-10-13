package Lesson3;

import Clone.School;

/**
 * Created by Денис on 10/8/16.
 */
public class Worker {

    private Machine machine;

    public Worker(){}

    public Worker(Machine machine){
        this.machine = machine;
    }

    public void addOneDetail(){
        Detail detail = new Detail("detail 1");
        machine.addDetails(detail);
    }

    public void addThreeDetail(){
        Detail detail1 = new Detail("detail 1");
        Detail detail2 = new Detail("detail 2");
        Detail detail3 = new Detail("detail 3");
        machine.addDetails(detail1, detail2, detail3);
    }

    public void addFiveDetail(){
        Detail detail1 = new Detail("detail 1");
        Detail detail2 = new Detail("detail 2");
        Detail detail3 = new Detail("detail 3");
        Detail detail4 = new Detail("detail 4");
        Detail detail5 = new Detail("detail 5");
        machine.addDetails(detail1, detail2, detail3, detail4, detail5);
    }

//    public Car buildNewCar(String name){
//        Car car = new Car.Builder(name).buildBody(new Detail("body")).addWheels(new Detail("wheels")).build();
//        return car;
//    }

    /*public Car addWheels(Car car){
        car.setWheels(new Detail("Wheels"));
        return car;
    }

    public Car addSeats(Car car){
        car.setSeats(new Detail("Seats"));
        return car;
    }

    public Car addDoors(Car car){
        car.setDoors(new Detail("Doors"));
        return car;
    }

    public Car paint(Car car, String color){
        car.setColor(color);
        return car;
    }*/

    /*public Car makeCarWithoutWheels(String name){
        Car car = new Car.Builder(name).buildBody(new Detail("Body")).build();
        return car;
    }

    public Car makeCarWithWheels(String name){
        Car car = new Car.Builder(name).buildBody(new Detail("Body")).
                addWheels(new Detail("Wheels")).build();
        return car;
    }*/

}
