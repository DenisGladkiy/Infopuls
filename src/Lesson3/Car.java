package Lesson3;

import java.util.List;

/**
 * Created by Денис on 10/8/16.
 */
public class Car {
    private String name;
    private Detail body;
    private Detail wheels;
    private Detail seats;
    private Detail doors;
    private String color;

    /*public Car(String name, Detail body, Detail wheels, Detail seats, Detail doors, String color) {
        this.name = name;
        this.body = body;
        this.wheels = wheels;
        this.seats = seats;
        this.doors = doors;
        this.color = color;
    }*/

    /*public Car(String name, Detail body){
        this(name, body, null, null, null, "");
    }*/

    public Car(Builder builder){
        this.name = builder.carName;
        this.body = builder.body;
        this.wheels = builder.wheels;
        this.seats = builder.seats;
        this.doors = builder.doors;
        this.color = builder.color;
    }

    /*public void setName(String name) {
        this.name = name;
    }

    public void setBody(Detail body) {
        this.body = body;
    }

    public void setWheels(Detail wheels) {
        this.wheels = wheels;
    }

    public void setSeats(Detail seats) {
        this.seats = seats;
    }

    public void setDoors(Detail doors) {
        this.doors = doors;
    }

    public void setColor(String color) {
        this.color = color;
    }
*/
    @Override
    public String toString() {
        return "Car{" +name +", "+ body +", " + wheels +", " + seats +", " + doors +
                ", " + color +"}";
    }

    public static class Builder{
        private String carName;
        private Detail body;
        private Detail wheels;
        private Detail seats;
        private Detail doors;
        private String color;

        public Builder(String name) {
            carName = name;
        }

        public Builder buildBody(Detail body){
            this.body = body;
            return this;
        }

        public Builder addWheels(Detail wheels){
            this.wheels = wheels;
            return this;
        }

        public Builder addSeats(Detail seats){
            this.seats = seats;
            return this;
        }

        public Builder addDoors(Detail doors){
            this.doors = doors;
            return this;
        }

        public Builder paint(String color){
            this.color = color;
            return this;
        }

        public Car build(){
            if(null != this.wheels) {
                return new Car(this);
            }else{
                return null;
            }
        }

    }
}
