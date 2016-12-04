package XML;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 12/3/16.
 */
public class Tariffs {
    List<Tariff> tariffs;

    public Tariffs() {
        tariffs = new ArrayList<>();
    }

    public Tariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public void addTariff(Tariff tariff){
        tariffs.add(tariff);
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    @Override
    public String toString() {
        return "Tariffs{" +
                "tariffs=" + tariffs +
                '}';
    }
}
