package Delegate;

/**
 * Created by Денис on 9/24/16.
 */
public class TopManager extends Manager implements Clean {

    public TopManager(String name) {
        super(name);
    }

    @Override
    public void CleanFactory() {
        System.out.println("Наблюдать");
    }
}
