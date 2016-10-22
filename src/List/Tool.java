package List;

import java.util.Objects;

/**
 * Created by Денис on 10/22/16.
 */
public class Tool implements Comparable<Tool> {
    private String name;
    private int size;

    public Tool(){}

    public Tool(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return " " + name + " " + size;
    }


    @Override
    public int compareTo(Tool tool) {
        if(size > tool.getSize()){
           return 1;
        }else if(size < tool.getSize()){
            return -1;
        }else {
            return 0;
        }
    }
}
