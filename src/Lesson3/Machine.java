package Lesson3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 10/8/16.
 */
public class Machine {
    private List<Detail> details;

    public Machine(){
        details = new ArrayList<>();
    }

    public void addDetails(Detail... details){
        for(Detail d : details){
            this.details.add(d);

        }
    }

    @Override
    public String toString() {
        return "Machine{" +
                "details=" + details +
                '}';
    }
}
