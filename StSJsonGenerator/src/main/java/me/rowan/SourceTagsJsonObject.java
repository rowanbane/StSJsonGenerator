package me.rowan;

public class SourceTagsJsonObject {
    private float BaseValue;
    private float ID;
    private String TagName;

    public SourceTagsJsonObject(float baseValue, float ID, String tagName) {
        BaseValue = baseValue;
        this.ID = ID;
        TagName = tagName;
    }


    // Getter Methods

    public float getBaseValue() {
        return BaseValue;
    }

    public float getID() {
        return ID;
    }

    public String getTagName() {
        return TagName;
    }

    // Setter Methods

    public void setBaseValue(float BaseValue) {
        this.BaseValue = BaseValue;
    }

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setTagName(String TagName) {
        this.TagName = TagName;
    }
}