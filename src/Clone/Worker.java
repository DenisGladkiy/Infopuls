package Clone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Денис on 10/1/16.
 */
public class Worker {
    private String name;
    private static List<Detail> details;
    private GeneralKnowledge knowledge;

    public Worker(){}

    public Worker(String name, String genKnow, String genExp, String phys){
        this.name = name;
        knowledge = new GeneralKnowledge(genKnow, genExp, phys);
    }


    public static void main(String[] args) {
        Worker worker = new Worker();
        details = new ArrayList();
        Detail detail1 = new Detail();
        details.add(detail1);
        Detail detail2 = worker.makeNewDetail(detail1);
        details.add(detail2);
        Detail detail3 = worker.makeNewDetail(detail1);
        details.add(detail3);
        Detail detail4 = new Detail(detail1, 0);
        details.add(detail4);
        Detail detail5 = new Detail(detail1, 0);
        details.add(detail5);

        for(int i = 0; i < 100; i++){
            Detail detail = worker.makeNewDetail(detail1);
            details.add(detail);
        }

        for(Detail d : details){
            System.out.println(d);
        }
    }

    private Detail makeNewDetail(Detail detail){
        return detail.clone();
    }

    public void learn(Worker worker){
        this.knowledge.education = worker.knowledge.getEducation();
        this.knowledge.experience = worker.knowledge.getExperience();
        this.knowledge.physics.physicKnowledge = worker.knowledge.physics.getPhysicKnowledge();
    }

    private class GeneralKnowledge{
        private String education;
        private String experience;
        private Physics physics;

        public GeneralKnowledge(String education, String experience, String physics){
            this.education = education;
            this.experience = experience;
            this.physics = new Physics(physics);
        }

        public String getEducation(){ return this.education; }
        public String getExperience(){ return this.experience; }
        public void setEducation(String education){this.education = education; }
        public void setExperience(String experience){this.experience = experience; }
        public Physics getPhysics() { return physics; }
        public void setPhysics(Physics physics) { this.physics = physics; }

        private class Physics{
            private String physicKnowledge;

            public Physics(String s){  this.physicKnowledge = s;}

            public String getPhysicKnowledge(){ return this.physicKnowledge; }
            public void setPhysicKnowledge(String physicKnowledge){ this.physicKnowledge = physicKnowledge; }

            @Override
            public String toString() {
                return physicKnowledge + ", Physics hashCode ="+hashCode();
            }
        }

        @Override
        public String toString() {
            return education + ", " + experience +
                    ", " + physics+" }" +" GenKnow hashCode = "+hashCode();
        }
    }

    @Override
    public String toString() {
        return  "name= " + name + ",{ knowledge=" + knowledge +" hashcode ="+ hashCode()+ '}';
    }
}
