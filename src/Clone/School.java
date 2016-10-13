package Clone;

/**
 * Created by Денис on 10/1/16.
 */
public class School {

    public static void main(String[] args) {

        Worker experiencedWorker = new Worker("Viktor", "University", "Work Experience", "Physics theory");
        Worker newWorker = new Worker("Dmitriy", "", "", "");

        System.out.println("experiencedWorker "+experiencedWorker);
        System.out.println("newWorker "+newWorker);

        newWorker.learn(experiencedWorker);

        System.out.println("\n");
        System.out.println("experiencedWorker "+experiencedWorker);
        System.out.println("newWorker "+newWorker);

    }
}
