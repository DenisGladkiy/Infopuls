package Metro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 10/1/16.
 */
public class Depot {
    private List<Carriage> headCarriages;
    private List<Carriage> midCarriages;

    public void makeCarriage(int headCarNumber, int midCarNumber){
        Carriage carrH = new Carriage(CarriageType.HEAD);
        Carriage carrM = new Carriage(CarriageType.MIDDLE);
        headCarriages = cloneCarr(carrH, headCarNumber);
        midCarriages = cloneCarr(carrM, midCarNumber);
    }

    private List<Carriage> cloneCarr(Carriage carr, int number){
        List<Carriage> carrs = new ArrayList<>();
        for(int i = 0; i < number; i++){
            Carriage newCarr = carr.clone();
            carrs.add(newCarr);
        }
        return carrs;
    }

    public List<Carriage> getHeadCarriages (){
        return headCarriages;
    }

    public List<Carriage> getMidCarriages (){
        return midCarriages;
    }


}
