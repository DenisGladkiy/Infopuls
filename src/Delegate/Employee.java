package Delegate;

/**
 * Created by Денис on 9/24/16.
 */
public class Employee implements Clean, Comparable<Employee> {
    private String name;
    private int salary;

    public Employee(String name){
        this.name = name;
    }

    public Employee(String name, int salary){
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void CleanFactory() {
    }

    @Override
    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Employee employee) {
        if(this.salary == employee.getSalary()){
            return 0;
        }else if(this.salary > employee.getSalary()){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
