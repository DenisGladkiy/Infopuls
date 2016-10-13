package Metro;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Денис on 10/6/16.
 */
public class TrainFactory {

    public Train makeTrain(Depot depot){
        List<Carriage> headCarrs = depot.getHeadCarriages();
        List<Carriage> midCarrs = depot.getMidCarriages();
        List<Carriage> trainCarrs = new LinkedList<>();
        Carriage carriage;
        if(headCarrs.size() > 1){
            carriage = headCarrs.remove(0);
            trainCarrs.add(carriage);
        }else{
            return null;
        }
        if(midCarrs.size() > 2){
            for(int i = 0; i < 3; i++) {
                carriage = midCarrs.remove(0);
                trainCarrs.add(carriage);
            }
        }else {
            return null;
        }
        carriage = headCarrs.remove(0);
        trainCarrs.add(carriage);
        //Train train = new Train(trainCarrs);
        Train train = new Train.Builder().buildTrain(trainCarrs).build();
        return train;
    }
}
