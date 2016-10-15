package Metro;

import java.util.List;

/**
 * Created by Денис on 10/1/16.
 */
public class Train {
    private List<Carriage> train;
    private Driver driver;
    private int trainId;

//    public Train (List<Carriage> train){
//        this.train = train;
//    }

    private Train(Builder builder){
        this.train = builder.train;
    }

    public void run(){}

    public void stop(){}

    public List<Carriage> getTrain() {
        return train;
    }

    public void setTrain(List<Carriage> train) {
        this.train = train;
    }

    public static class Builder{
        private List<Carriage> train;

        public Builder buildTrain(List<Carriage> train){
            this.train = train;
            return this;
        }

        public Train build(){
            if(this.train.size() == 5) {
                if(this.train.get(0).getType() == CarriageType.HEAD
                        && this.train.get(4).getType() == CarriageType.HEAD) {
                    return new Train(this);
                }else{
                    return null;
                }
            }else{
                return null;
            }
        }
    }

    public void setDriver(Driver driver){
        this.driver = driver;
    }

    public Driver getDriver(){ return driver; }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    @Override
    public String toString() {
        return "Train{" +
                "train=" + train +
                ", driver=" + driver +
                '}';
    }
}
