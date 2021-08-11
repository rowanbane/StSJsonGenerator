package me.rowan;

public class SourceCardTagProvJsonObject {
    private float CardID;
    private float ID;
    private float TagID;
    private float Value;

    public SourceCardTagProvJsonObject(float cardID, float ID, float tagID, float value) {
        CardID = cardID;
        this.ID = ID;
        TagID = tagID;
        Value = value;
    }

    // Getter Methods

    public float getCardID() {
        return CardID;
    }

    public float getID() {
        return ID;
    }

    public float getTagID() {
        return TagID;
    }

    public float getValue() {
        return Value;
    }

    // Setter Methods

    public void setCardID(float CardID) {
        this.CardID = CardID;
    }

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setTagID(float TagID) {
        this.TagID = TagID;
    }

    public void setValue(float Value) {
        this.Value = Value;
    }
}