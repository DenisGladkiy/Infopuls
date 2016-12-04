package XML;

/**
 * Created by Денис on 12/3/16.
 */
public class Parameters {
    int favoriteNumbers;
    double activationPrice;
    int countFrom;

    public int getFavoriteNumbers() {
        return favoriteNumbers;
    }

    public void setFavoriteNumbers(int favoriteNumbers) {
        if(favoriteNumbers <= 10) {
            this.favoriteNumbers = favoriteNumbers;
        }
    }

    public double getActivationPrice() {
        return activationPrice;
    }

    public void setActivationPrice(double activationPrice) {
        if (activationPrice <= 100){
            this.activationPrice = activationPrice;
        }
    }

    public int getCountFrom() {
        return countFrom;
    }

    public void setCountFrom(int countFrom) {
        if(countFrom == 12 || countFrom == 60) {
            this.countFrom = countFrom;
        }
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "favoriteNumbers=" + favoriteNumbers +
                ", activationPrice=" + activationPrice +
                ", countFrom=" + countFrom +
                '}' + "\n";
    }
}
