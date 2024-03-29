package org.iogame.model.research;

import java.util.ArrayList;
import java.util.List;

public class TechManager {


    private static final long[] researchLevels = {22, 42, 95, 201, 400, 1000, 1600, 2000, 3045, 4002, 5500, 7000, 8900, 10040, 12042};

    private List<ETech> developedTechs = new ArrayList<>();
    private List<ETech> availableTechs = new ArrayList<>();

    private int researchPoints = 1;

    private double exp = 0;
    private int lvl = 1;


    public TechManager() {
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

    private void updateAvailableTechs() {
        for (ETech tech : ETech.values()) {
            if (canDevelop(tech)) {
                availableTechs.add(tech);
            }
        }
    }


    public boolean canDevelop(ETech tech) {
        if (developedTechs.containsAll(tech.getTechReqs()) &&
                !developedTechs.contains(tech) && researchPoints > 0) {
            return true;
        }
        return false;
    }

    public boolean develop(ETech tech) {
        if (canDevelop(tech)) {

            developedTechs.add(tech);
            researchPoints--;
            updateAvailableTechs();
            return true;
        }
        return false;
    }


    public List<ETech> getAvailableTechs() {
        updateAvailableTechs();
        return availableTechs;
    }


    public List<ETech> getdevelopedTech() {
        return developedTechs;
    }


    public void setResearchPoints(int researchPoints) {
        this.researchPoints = researchPoints;
    }
}
