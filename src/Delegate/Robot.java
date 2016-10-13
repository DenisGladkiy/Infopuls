package Delegate;

/**
 * Created by Денис on 9/24/16.
 */
public class Robot implements Clean, Construct, Manage {
    @Override
    public void CleanFactory() {
        System.out.println("Подметать. Красить");
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void construct() {
        System.out.println("Строить дом");
    }

    @Override
    public void manage() {
        System.out.println("Вести переговоры");
    }
}
