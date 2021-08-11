package me.rowan;

public class SourceCardTagAffJsonObject {
    private float CardID;
    private float ID;
    private float Modifier;
    private float SynergyModifier;
    private float TagID;

    public SourceCardTagAffJsonObject(float cardID, float ID, float modifier, float synergyModifier, float tagID) {
        CardID = cardID;
        this.ID = ID;
        Modifier = modifier;
        SynergyModifier = synergyModifier;
        TagID = tagID;
    }

    // Getter Methods

    public float getCardID() {
        return CardID;
    }

    public float getID() {
        return ID;
    }

    public float getModifier() {
        return Modifier;
    }

    public float getSynergyModifier() {
        return SynergyModifier;
    }

    public float getTagID() {
        return TagID;
    }

    // Setter Methods

    public void setCardID(float CardID) {
        this.CardID = CardID;
    }

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setModifier(float Modifier) {
        this.Modifier = Modifier;
    }

    public void setSynergyModifier(float SynergyModifier) {
        this.SynergyModifier = SynergyModifier;
    }

    public void setTagID(float TagID) {
        this.TagID = TagID;
    }
}