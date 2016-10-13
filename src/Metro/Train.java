package Metro;

import java.util.List;

/**
 * Created by Денис on 10/1/16.
 */
public class Train {
    private List<Carriage> train;

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
}
