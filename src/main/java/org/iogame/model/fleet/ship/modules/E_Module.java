package model.ship.modules;

import model.enums.EResource;

import java.util.HashMap;


public enum E_Module {
    BODY1("name_BODY1", "description_BODY1"), BODY2("name_BODY2", "description_BODY2"),
    WEAPON1("name_WEAPON1", "description_WEAPON1");

    private final String name;
    private final String description;
    private final HashMap<EResource, Double> costs;
    private final int slot;


    private E_Module(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
