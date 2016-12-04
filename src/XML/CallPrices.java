package XML;

/**
 * Created by Денис on 12/3/16.
 */
public class CallPrices {
    double inside;
    double outside;
    double landline;

    public double getInside() {
        return inside;
    }

    public void setInside(double inside) {
        if(inside <= 100) {
            this.inside = inside;
        }
    }

    public double getOutside() {
        return outside;
    }

    public void setOutside(double outside) {
        if(inside <= 100) {
            this.outside = outside;
        }
    }

    public double getLandline() {
        return landline;
    }

    public void setLandline(double landline) {
        if(inside <= 100) {
            this.landline = landline;
        }
    }

    @Override
    public String toString() {
        return "CallPrices{" +
                "inside=" + inside +
                ", outside=" + outside +
                ", landline=" + landline +
                '}' + "\n";
    }
}
