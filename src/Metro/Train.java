package Metro;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by Денис on 10/1/16.
 */
@DatabaseTable(tableName = "train")
public class Train extends SuperClass {

    int[] experience = {-2,-1,0,1,2};

    @DatabaseField(generatedId = true)
    private int train_id;

//    @ForeignCollectionField()
//    private Collection<Carriage> train;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Carriage carr1;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Carriage carr2;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Carriage carr3;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Carriage carr4;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Carriage carr5;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Driver driver;


    public Train(){}

    private Train(Builder builder){
        carr1 = builder.train.get(0);
        carr2 = builder.train.get(1);
        carr3 = builder.train.get(2);
        carr4 = builder.train.get(3);
        carr5 = builder.train.get(4);
        //train = builder.train;
    }

    public void run(){
        Random rnd = new Random();
        int exp = experience[rnd.nextInt(4)];
        int currentExp = driver.getExperience();
        driver.setExperience(currentExp + exp);
    }

    public void stop(Station station){
        if(station != null) {
            System.out.println(station.getName() + "  " + driver);
        }else{
            System.out.println("no station");
        }
    }

    public Collection<Carriage> getTrain() {
        List<Carriage> train = new ArrayList<>();
        train.add(0, carr1);
        train.add(1, carr2);
        train.add(2, carr3);
        train.add(3, carr4);
        train.add(4, carr5);
        return train;
    }

    public void setTrain(List<Carriage> train) {
        carr1 = train.get(0);
        carr2 = train.get(1);
        carr3 = train.get(2);
        carr4 = train.get(3);
        carr5 = train.get(4);
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

    @Override
    public String toString() {
        return "Train{" +
                "train_id=" + train_id +
                ", carr1=" + carr1 +
                ", carr2=" + carr2 +
                ", carr3=" + carr3 +
                ", carr4=" + carr4 +
                ", carr5=" + carr5 +
                ", driver=" + driver +
                '}' + "\n";
    }
}
