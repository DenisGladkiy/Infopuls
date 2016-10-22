package List;

/**
 * Created by Денис on 10/22/16.
 */
public class Worker implements Comparable<Worker> {
    private String name;
    private byte age;
    private Tool tool;

    public Worker(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    @Override
    public String toString() {
        return " " + name + " ";
    }

    @Override
    public int compareTo(Worker w) {
        if(age > w.getAge()){
            return 1;
        }else if(age < w.getAge()){
            return -1;
        }else {
            return 0;
        }
    }
}
