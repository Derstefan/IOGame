package org.iogame.model.data;

public enum EBlueprint {
    GENESIS("Genesis", "First of its kind, this ship is still in service and a reliable work horse for all sorts of tasks");

    private final String id;
    private final String description;

    private EBlueprint(String id, String description){
        this.id = id;
        this.description = description;
    }

    // Getter

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
