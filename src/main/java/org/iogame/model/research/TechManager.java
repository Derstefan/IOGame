package org.iogame.model.research;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TechManager {
    // Tech Conditions: (This should come in Main or Game)
    private final List<List<ETech>> techCondition = Arrays.asList(
            /*Conditions for T1*/   Arrays.asList(ETech.T1, ETech.T3),
            /*Conditions for T2*/   Arrays.asList(ETech.T3),
            /*Conditions for T3*/   Arrays.asList());
    public Boolean[][] dependence = new Boolean[ETech.values().length][ETech.values().length];
    private List<ETech> developedTech = new ArrayList<>();
    public Boolean[] techList = new Boolean[ETech.values().length];
    /* public void initializeDependence() {
         for (int i = 0; i < ETech.values().length; i++)
         { for (int j = 0; j < ETech.values().length; j++)
         { dependence[i][j] = false; }}
     }*/
    // Sets dependence matrix
    public void setDependence() {
        for (int i = 0; i < ETech.values().length; i++) {
            for (int j = 0; j < ETech.values().length; j++) {
                dependence[i][j] = false;
            }
        }
        for (int i = 0; i < ETech.values().length; i++) {
            for (int j = 0; j < techCondition.get(i).size(); j++) {
                dependence[i][techCondition.get(i).get(j).ordinal()] = true;
            }
        }
    }
    public void initializeTechList() {
        for (int i = 0; i < ETech.values().length; i++) {
            techList[i] = false;
        }
    }
    public boolean canDevelop(ETech tech) {
        for (int i = 0; i < ETech.values().length; i++) {
            if (dependence[tech.ordinal()][i] && !techList[i]) {
                return false;
            }
        }
        return true;
    }
    public void develop(ETech tech) {
        if (canDevelop(tech)) {
            techList[tech.ordinal()] = true;
            developedTech.add(tech); // check if this will be used
        }
    }
    // ALternative "canDevelop" and "develop" using ETech list.
    public boolean canDevelopB(ETech tech) {
        if (developedTech.containsAll(techCondition.get(tech.ordinal()))) {
            return true;
        }
        return false;
    }
    public void developB(ETech tech) {
        if (canDevelopB(tech)) {
            techList[tech.ordinal()] = true;
            developedTech.add(tech); // check if this will be used
        }
    }



//    public static void main(String[] args) {
//
//        TechManager t = new TechManager();
//        //t.initializeDependence();
//        t.setDependence();
//        t.initializeTechList();
//        System.out.print("Player Tech's List: ");
//        for(int i = 0; i < ETech.values().length; i++) {
//            System.out.print(t.techList[i] + ", ");
//        }
//        System.out.println();
//        for (int i = 0; i < ETech.values().length; i++) {
//            System.out.println("Can I develop tech T" + i + "? " + t.canDevelop(ETech.values()[i]));
//        }
//        t.develop(ETech.T3);
//        System.out.println();
//        System.out.print("Player Tech's List: ");
//        for (int i = 0; i < ETech.values().length; i++) {
//            System.out.print(t.techList[i] + ", ");
//        }
//System.out.println();
////        for (int i = 0; i < t.developedTech.size(); i++) {
////            System.out.println(t.developedTech.get(i)+"hola");
////        }
//        for (int i = 0; i < ETech.values().length; i++) {
//            System.out.println("Can I now develop tech T" + i + "? " + t.canDevelop(ETech.values()[i]));
//        }
//        System.out.println();
//        System.out.println("Dependence Matrix");
//        for (int i = 0; i < ETech.values().length; i++) {
//            for (int j = 0; j < ETech.values().length; j++) {
//                System.out.print(t.dependence[i][j] + " | ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println("Conditions");
//        for (int i = 0; i < ETech.values().length; i++) {
//            System.out.print("Conditions for " + ETech.values()[i] + ": ");
//            for (int j = 0; j < t.techCondition.get(i).size(); j++) {
//                System.out.print(t.techCondition.get(i).get(j) + " | ");
//            }
//            System.out.println();
//        }
//    }

    public List<ETech> getdevelopedTech() {
        return developedTech;
    }

    public void setdevelopedTech(List<ETech> developedTech) {
        this.developedTech = developedTech;
    }
}
