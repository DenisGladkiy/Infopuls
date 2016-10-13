package Lesson3;

/**
 * Created by Денис on 10/8/16.
 */
public class Factory {

    public static void main(String[] args) {
        Machine machine = new Machine();
        Worker worker = new Worker(machine);
        worker.addOneDetail();
        worker.addThreeDetail();
        worker.addFiveDetail();
        System.out.println(machine);
        Factory factory = new Factory();
        //Car car = factory.buildCar("Mersedes", "Black");
        //System.out.println(car);
        factory.buildCarWithBuilder();
    }

    /*public Car buildCar(String name, String color){
        Worker worker = new Worker();
        Car car = worker.buildNewCar(name);
        car = worker.addWheels(car);
        car = worker.addSeats(car);
        car = worker.addDoors(car);
        car = worker.paint(car, color);
        return car;
    }*/

    public void buildCarWithBuilder(){
        Car car;
//        Worker worker = new Worker();
//        car = worker.makeCarWithoutWheels("Opel");
//        System.out.println("Car without wheels  " + car);
//        car = worker.makeCarWithWheels("Mazda");
//        System.out.println("Car with wheels  " + car);

        CarMaster master = new CarMaster();
        Car.Builder builder = master.makeCarBuilder("Nissan");
        CarMaster master2 = new CarMaster();
        builder = master2.addDetails(builder, new Detail("body"), new Detail("wheels"), new Detail("seats"));
        CarMaster master3 = new CarMaster();
        car = master3.makeCar(builder, new Detail("doors"), "Red");
        System.out.println(car);
    }
}
