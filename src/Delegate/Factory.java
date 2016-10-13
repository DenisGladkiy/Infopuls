package Delegate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Денис on 9/24/16.
 */
public class Factory {
    private static List<Clean> subbotnik;


    public static void main(String[] args) {

        subbotnik = new ArrayList<>();
        Worker worker1 = new Worker("Vova");
        Worker worker2 = new Worker("Vasya");
        Manager manager = new Manager("Petya");
        TopManager topManager = new TopManager("Valdemar");

        subbotnik.add(worker1);
        subbotnik.add(worker2);
        subbotnik.add(manager);
        subbotnik.add(topManager);

        for(Clean c:subbotnik){
            System.out.println(c.getName());
            c.CleanFactory();
        }

        manager.setDelegate(worker1);
        worker1.delegate();



        Robot robot = new Robot();

        robot.CleanFactory();
        robot.construct();
        robot.manage();

        Worker workerWithTool = new Worker("Alex", 1000, Construct.Tools.HAMMER);
        workerWithTool.construct();

        Worker worker3 = new Worker("Gena", 5000);
        Manager manager1 = new Manager("Ivan", 10000);
        Worker worker4 = new Worker("Sasha", 6000);

        List<Comparable> employees = new ArrayList<>();

        employees.add(worker3);
        employees.add(manager1);
        employees.add(worker4);
        employees.add(workerWithTool);

        Collections.sort(employees);

        System.out.println(employees);


    }


}
