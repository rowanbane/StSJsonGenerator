package me.rowan;

public class SourceRelicsJsonObject {
    private float ID;
    private String RelicName;

    public SourceRelicsJsonObject(float ID, String relicName) {
        this.ID = ID;
        RelicName = relicName;
    }

// Getter Methods

    public float getID() {
        return ID;
    }

    public String getRelicName() {
        return RelicName;
    }

    // Setter Methods

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setRelicName(String RelicName) {
        this.RelicName = RelicName;
    }
}