package src.main.java.org.iogame.model.data;

import org.iogame.model.data.EResource;
import java.util.HashMap;


public enum EModule {
    BODY1("name_BODY1", "description_BODY1", "body"), BODY2("name_BODY2", "description_BODY2", "body"),
    SPECIAL1("name_SPECIAL1", "description_SPECIAL1", "special"),
    CARGO("Cargo module", "Can hold ressources", "special"),
    WEAPON1("name_WEAPON1", "description_WEAPON1", "weapon"),
    RAPIDRAY("Rapid Ray", "Shoots a range of particular rapid rays, overtaking even light!", "weapon"),
    DEFENSE1("name_DEFENSE1", "description_DEFENSE1", "defense"),
    TURTLESHIELD("Turtle Shield", "Old but gold!", "defense")
    ;

    private final String name;
    private final String description;
    private final String type;


    private EModule(String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    // GETTER
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
