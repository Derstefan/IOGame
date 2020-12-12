package model.ship.modules;

import model.enums.EResource;

import java.util.HashMap;

public class BodyMod extends Module{
    private double agility;
    private final int slot;

    public BodyMod(String name, String description, HashMap<EResource, Double> costs, double duration) {
        super(name, description, costs, duration);
    }
}
