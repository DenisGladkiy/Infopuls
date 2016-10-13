package Delegate;

/**
 * Created by Денис on 9/24/16.
 */
public class Worker extends Employee implements Clean, Construct {
    Manage delegate = null;
    Tools tool;

    public Worker(String name) {
        super(name);
    }

    public Worker(String name, int salary){
        super(name, salary);
    }

    public Worker(String name, int salary, Tools tool){
        super(name, salary);
        this.tool = tool;
    }

    @Override
    public void CleanFactory() {
        System.out.println("Подметать");
    }

    @Override
    public void construct() {
        System.out.println("Строить дом");
        System.out.println(tool);
    }

    public void delegate(){
        if(delegate != null)
        delegate.manage();
    }

}
