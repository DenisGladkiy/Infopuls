package List;


/**
 * Created by Денис on 10/22/16.
 */
public class Main {
    private MyArrayList<Worker> workers;
    private MyArrayList<Tool> tools;
    private MyArrayList<ChildTool> childTools;

    public static void main(String[] args) {
        Main main = new Main();
        main.workers = main.makeWorkerList();
        System.out.println("Workers = " + main.workers);
        main.tools = main.makeToolList();
        System.out.println("Tools = " + main.tools);
        main.childTools = main.makeChildToolList();
        System.out.println("ChildTools = " + main.childTools);
        Tool tool = main.tools.get(2);
        System.out.println("is Screwdriver the biggest = "+isBiggest(tool, main.tools));
        System.out.println("is childTools > tools = "+main.childTools.compareTo(main.tools));
        main.tools.addList(main.childTools);
        System.out.println("tools add childTools = " + main.tools);
        Object[] tools = new Object[]{new Tool("qwe", 2), new Tool("asd", 3)};
        MyArrayList<Object> objects = new MyArrayList<>(tools);
        objects.add(new Worker("Dima"));
        Object b= new Worker("hjkl");
        objects.add(b);
        objects.add(tool);
        System.out.println(objects);
    }

    private MyArrayList<Worker> makeWorkerList(){
        Worker worker1 = new Worker("Mike");
        Worker worker2 = new Worker("Tim");
        Worker worker3 = new Worker("Sasha");
        Worker[] wArr = new Worker[]{worker1, worker2, worker3};
        return new MyArrayList<>(wArr);
    }

    private MyArrayList<Tool> makeToolList(){
        Tool tool1 = new Tool("Hammer", 9);
        Tool tool2 = new Tool("Ax", 7);
        Tool tool3 = new Tool("Screwdriver", 10);
        Tool tool4 = new Tool("Saw", 8);
        Tool[] tArr = new Tool[]{tool1, tool2, tool3, tool4};
        return new MyArrayList<>(tArr);
    }

    private MyArrayList<ChildTool> makeChildToolList(){
        ChildTool tool1 = new ChildTool("Shovel", 20);
        ChildTool tool2 = new ChildTool("Pliers", 20);
        ChildTool[] tArr = new ChildTool[]{tool1, tool2};
        return new MyArrayList<>(tArr);
    }

    private static <T extends Comparable<T>> boolean isBiggest(T arg, MyArrayList<T> lst){
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
