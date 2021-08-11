package me.rowan;

public class SourceRelicsTagRelJsonObject {
    private float ID;
    private float Modifier;
    private float RelicID;
    private float TagID;

    public SourceRelicsTagRelJsonObject(float ID, float modifier, float relicID, float tagID) {
        this.ID = ID;
        Modifier = modifier;
        RelicID = relicID;
        TagID = tagID;
    }

    // Getter Methods

    public float getID() {
        return ID;
    }

    public float getModifier() {
        return Modifier;
    }

    public float getRelicID() {
        return RelicID;
    }

    public float getTagID() {
        return TagID;
    }

    // Setter Methods

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setModifier(float Modifier) {
        this.Modifier = Modifier;
    }

    public void setRelicID(float RelicID) {
        this.RelicID = RelicID;
    }

    public void setTagID(float TagID) {
        this.TagID = TagID;
    }
}