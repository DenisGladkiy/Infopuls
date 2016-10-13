package Delegate;

/**
 * Created by Денис on 9/24/16.
 */
public class Manager extends Employee implements Clean, Manage {
    Construct delegate;

    public Manager(String name) {
        super(name);
    }

    public Manager(String name, int salary){
        super(name, salary);
    }

    @Override
    public void CleanFactory() {
        System.out.println("Руководить");
    }

    @Override
    public void manage() {
        System.out.println("Вести переговоры");
    }

    public void setDelegate(Worker worker){
        worker.delegate = this;
    }

}
