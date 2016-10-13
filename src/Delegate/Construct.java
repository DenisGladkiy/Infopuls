package Delegate;

/**
 * Created by Денис on 9/24/16.
 */
public interface Construct {
    public void construct();

    public enum Tools {
        HAMMER, AX, WRENCH;
    }

    public enum Materials {
        BRICK, NAIL;
    }
}
