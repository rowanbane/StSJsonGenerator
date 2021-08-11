package me.rowan;

public class SourceCardsJsonObject {
    private float ActMod1;
    private float ActMod2;
    private float ActMod3;
    private float ActMod4;
    private float BaseScore;
    private String CardName;
    private float ID;
    private float OneOfModifier;
    private float OneOfRule;

    public SourceCardsJsonObject(float actMod1, float actMod2, float actMod3, float actMod4, float baseScore, String cardName, float ID, float oneOfModifier, float oneOfRule) {
        ActMod1 = actMod1;
        ActMod2 = actMod2;
        ActMod3 = actMod3;
        ActMod4 = actMod4;
        BaseScore = baseScore;
        CardName = cardName;
        this.ID = ID;
        OneOfModifier = oneOfModifier;
        OneOfRule = oneOfRule;
    }

    // Getter Methods

    public float getActMod1() {
        return ActMod1;
    }

    public float getActMod2() {
        return ActMod2;
    }

    public float getActMod3() {
        return ActMod3;
    }

    public float getActMod4() {
        return ActMod4;
    }

    public float getBaseScore() {
        return BaseScore;
    }

    public String getCardName() {
        return CardName;
    }

    public float getID() {
        return ID;
    }

    public float getOneOfModifier() {
        return OneOfModifier;
    }

    public float getOneOfRule() {
        return OneOfRule;
    }

    // Setter Methods

    public void setActMod1(float ActMod1) {
        this.ActMod1 = ActMod1;
    }

    public void setActMod2(float ActMod2) {
        this.ActMod2 = ActMod2;
    }

    public void setActMod3(float ActMod3) {
        this.ActMod3 = ActMod3;
    }

    public void setActMod4(float ActMod4) {
        this.ActMod4 = ActMod4;
    }

    public void setBaseScore(float BaseScore) {
        this.BaseScore = BaseScore;
    }

    public void setCardName(String CardName) {
        this.CardName = CardName;
    }

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setOneOfModifier(float OneOfModifier) {
        this.OneOfModifier = OneOfModifier;
    }

    public void setOneOfRule(float OneOfRule) {
        this.OneOfRule = OneOfRule;
    }
}