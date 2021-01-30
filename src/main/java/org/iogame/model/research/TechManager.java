package org.iogame.model.research;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TechManager {


    private static final HashMap<ETech, List<ETech>> techConditions = new HashMap<>();


    private static final long[] researchLevels = {22, 42, 95, 201, 400, 1000, 1600, 2000, 3045, 4002, 5500, 7000, 8900, 10040, 12042};

    private List<ETech> developedTechs = new ArrayList<>();
    private List<ETech> availableTechs = new ArrayList<>();

    private int researchPoints = 1;
    private double exp = 0;
    private int lvl = 1;

    public TechManager() {
        initiateConditions();
        updateAvailableTechs();
    }

    public void addExp(double exp) {
        this.exp += exp;

        while (this.exp > researchLevels[lvl - 1]) {
            lvlUp();
        }

    }

    public void lvlUp() {
        lvl++;
        researchPoints++;
    }

    public void useResearchPoint() {
        researchPoints--;
    }

    private void initiateConditions() {
        // Test
        techConditions.put(ETech.T1, Arrays.asList());
        techConditions.put(ETech.T2, Arrays.asList(ETech.T1));
        techConditions.put(ETech.T3, Arrays.asList(ETech.T1, ETech.T2));
    }


    private void updateAvailableTechs() {
        for (ETech tech : techConditions.keySet()) {
            if (canDevelop(tech)) {
                availableTechs.add(tech);
            }
        }
    }


    public boolean canDevelop(ETech tech) {
        if (developedTechs.containsAll(techConditions.get(tech)) &&
                !developedTechs.contains(tech) && researchPoints > 0) {
            return true;
        }
        return false;
    }

    public void develop(ETech tech) {
        if (canDevelop(tech)) {
            developedTechs.add(tech);
            researchPoints--;
            updateAvailableTechs();
        }
    }


    public List<ETech> getAvailableTechs() {
        updateAvailableTechs();
        return availableTechs;
    }


    public List<ETech> getdevelopedTech() {
        return developedTechs;
    }
}
