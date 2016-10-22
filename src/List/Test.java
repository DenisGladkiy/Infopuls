package List;

/**
 * Created by Денис on 10/21/16.
 */
public class Test {
    static MyList<String> list = new MyList<>();
    static MyList<Worker> workerList = new MyList<>();
    static MyList<Tool> toolList = new MyList<>();
    static Tool hammer;
    static Tool ax;
    static Tool saw;

    public static void main(String[] args) {
        testMyList();
        testWorkerList();
        MyArrayList<Tool> tools = new MyArrayList<>(makeToolArr());
        tools.add(new Tool("screwdriver", 3));
        System.out.println(tools);
        toolList = new MyList<>(makeToolArr());
        System.out.println(isBiggest(hammer, toolList));
        MyList<ChildTool> toolList2 = getList();
        System.out.println(toolList.compareTo(toolList2));
        toolList.addList(toolList2);
        System.out.println(toolList);
    }

    public static void testWorkerList(){
        workerList = new MyList<>();
        Worker worker1 = new Worker("Oleg");
        Worker worker2 = new Worker("John");
        Worker worker3 = new Worker("Bill");
        workerList.add(worker1);
        workerList.add(worker2);
        System.out.println(workerList);
        workerList.add(1, worker3);
        System.out.println(workerList);
    }

    public static Tool[] makeToolArr(){
        hammer = new Tool("hammer", 10);
        ax = new Tool("ax", 5);
        saw = new Tool("saw", 8);
        return new Tool[]{hammer, ax, saw};
    }

    private static void testMyList(){
        System.out.println(list.size());
        list.add("first");
        list.add("second");
        list.add("third");
        System.out.println(list);
        System.out.println("size = "+list.size());
        list.add(2, "fourth");
        System.out.println(list);
        System.out.println("size = "+list.size());
        System.out.println(list.indexOf("second"));
        System.out.println(list.remove(2));
        System.out.println(list);
        System.out.println("size = "+list.size());
        System.out.println(list.get(2));
        list.clear();
        System.out.println(list);
        System.out.println("size = "+list.size());
        list = new MyList<>(new String[]{"one", "two", "three"});
        System.out.println(list);
    }

    private static MyList getList(){
        ChildTool tool1 = new ChildTool("tool1", 11);
        ChildTool tool2 = new ChildTool("tool2", 13);
        ChildTool tool3 = new ChildTool("tool3", 15);
        ChildTool tool4 = new ChildTool("tool4", 9);
        ChildTool[] tArr = new ChildTool[]{tool1, tool2, tool3, tool4};
        return new MyList<>(tArr);
    }

    private static <T extends Comparable> boolean isBiggest(T arg, MyList<T> lst){
        if(lst.contains(arg)){
            for(int i = 0; i < lst.size(); i++){
                if(arg.compareTo(lst.get(i)) < 0){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
}
