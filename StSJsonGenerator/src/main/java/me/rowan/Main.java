package me.rowan;


import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //Stores a list of objects modelled after all the Database tables
    private static List<SourceRelicsJsonObject> sourceRelicJson;
    private static List<SourceCardsJsonObject> sourceCardJson;
    private static List<SourceTagsJsonObject> sourceTagJson;
    private static List<SourceRelicsTagRelJsonObject> sourceRelicTagJson;
    private static List<SourceCardTagAffJsonObject> sourceCardTagAffJson;
    private static List<SourceCardTagProvJsonObject> sourceCardTagRelJson;


    public static void main(String[] args) {

        //Deserializes the JSON into code
        obtainJSONs();

        //Serializes the object lists into our game json for Relics
        createRelicJson();

        //Serializes the object lists into our game json for Card Evaluation
        createCardJson();

        //Serializes the object lists into our game json for Tags
        createTagsJson();;

    }

    public static void obtainJSONs() {


        obtainRelicJson();
        obtainCardJson();
        obtainTagJson();
        obtainRelicTagJson();
        obtainCardTagRelJson();
        obtainCardTagAffJson();



    }

    public static void createCardJson() {
        SourceTagsJsonObject[] arrSourceTags = sourceTagJson.toArray(new SourceTagsJsonObject[0]);

        List<OutputCardJson> listOutputCards = new ArrayList<OutputCardJson>();

        for (SourceCardsJsonObject cardEval : sourceCardJson
        ) {

            //Create attributes and fetch the easy ones
            String cardName = cardEval.getCardName();
            float baseScore = cardEval.getBaseScore();
            List<String> tagsProvided = new ArrayList<String>();
            List<String> tagsAffected = new ArrayList<String>();
            float[] actModifer = new float[4];
            actModifer[0] = cardEval.getActMod1();
            actModifer[1] = cardEval.getActMod2();
            actModifer[2] = cardEval.getActMod3();
            actModifer[3] = cardEval.getActMod4();
            List<tagModiferObject> synergyModifer = new ArrayList<tagModiferObject>();
            float oneOfRule = cardEval.getOneOfRule();
            float oneOfModifier = cardEval.getOneOfModifier();

            //Obtain tagsProvided
            for (SourceCardTagProvJsonObject tagProv: sourceCardTagRelJson
                 ) {

                if (tagProv.getCardID() == cardEval.getID())
                {
                    tagsProvided.add(fetchTagName(tagProv.getTagID(), arrSourceTags));
                }

            }

            //Obtain tagsAffected and synergyModifier
            for (SourceCardTagAffJsonObject tagAff: sourceCardTagAffJson
            ) {

                if (tagAff.getCardID() == cardEval.getID())
                {
                    String name = fetchTagName(tagAff.getTagID(), arrSourceTags);


                    //Add to the list of affected
                    tagsAffected.add(name);

                    //Add synergy mods
                    synergyModifer.add(new tagModiferObject(name, tagAff.getSynergyModifier()));


                }

            }

            //Create the Json object

            OutputCardJson cardJson = new OutputCardJson(cardName, baseScore, tagsProvided, tagsAffected, actModifer, synergyModifer, oneOfRule, oneOfModifier);


            //Add to the list
            listOutputCards.add(cardJson);

        }

        //Create the JSON
        OutputCardJson[] arrOutputCard = listOutputCards.toArray(new OutputCardJson[0]);

        Gson gson = new Gson();

        String jsonString = gson.toJson(arrOutputCard);

        System.out.println(jsonString);


    }

    public static void createRelicJson() {


        List<OutputRelicJson> listOutputRelic = new ArrayList<OutputRelicJson>();

        for (SourceRelicsJsonObject relic : sourceRelicJson
        ) {
            List<tagModiferObject> tagMod = new ArrayList<tagModiferObject>();

            for (SourceRelicsTagRelJsonObject tagRel : sourceRelicTagJson
            ) {

                if (relic.getID() == tagRel.getRelicID()) {

                    String tagString = "tagIDNotFound";
                    for (SourceTagsJsonObject tag : sourceTagJson
                    ) {
                        if (tag.getID() == tagRel.getTagID()) {
                            tagString = tag.getTagName();
                        }
                    }
                    tagModiferObject newTag = new tagModiferObject(tagString, tagRel.getModifier());

                    tagMod.add(newTag);


                }

            }

            listOutputRelic.add(new OutputRelicJson(tagMod, relic.getRelicName()));

        }

        OutputRelicJson[] arrOutputRelic = listOutputRelic.toArray(new OutputRelicJson[0]);

        Gson gson = new Gson();

        String jsonString = gson.toJson(arrOutputRelic);

        System.out.println(jsonString);

    }

    public static void createTagsJson() {

        List<OutputTagsJson> listOutputTags = new ArrayList<OutputTagsJson>();


        for (SourceTagsJsonObject tag: sourceTagJson
             ) {

            listOutputTags.add(new OutputTagsJson(tag.getTagName(), tag.getBaseValue()));

        }

        //Create the JSON
        OutputTagsJson[] arrOutputTags = listOutputTags.toArray(new OutputTagsJson[0]);

        Gson gson = new Gson();

        String jsonString = gson.toJson(arrOutputTags);

        System.out.println(jsonString);

    }

    public static void obtainRelicJson() {

        //Obtain the Relic Json
        sourceRelicJson = new ArrayList<SourceRelicsJsonObject>();

        File relicInput = new File("C:/Users/Owner/Desktop/Personal Repos/StSJsonGenerator/src/main/resources/jsons/Relics.json");

        JsonArray relicObjectArray = null;


        try {
            JsonElement relicElement = JsonParser.parseReader(new FileReader(relicInput));
            relicObjectArray = relicElement.getAsJsonArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (JsonElement relic : relicObjectArray
        ) {
            JsonObject relicJsonObject = relic.getAsJsonObject();

            int id = relicJsonObject.get("ID").getAsInt();

            String relicname = relicJsonObject.get("RelicName").getAsString();


            SourceRelicsJsonObject sourceRelic = new SourceRelicsJsonObject(id, relicname);
            sourceRelicJson.add(sourceRelic);

        }

        System.out.println(sourceRelicJson.get(0).getRelicName());

    }

    public static void obtainTagJson() {
        //Obtain the Tags Json
        sourceTagJson = new ArrayList<SourceTagsJsonObject>();

        File input = new File("C:/Users/Owner/Desktop/Personal Repos/StSJsonGenerator/src/main/resources/jsons/Tags.json");

        JsonArray ObjectArray = null;


        try {
            JsonElement Element = JsonParser.parseReader(new FileReader(input));
            ObjectArray = Element.getAsJsonArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (JsonElement obj : ObjectArray
        ) {
            JsonObject jsonObject = obj.getAsJsonObject();

            int id = jsonObject.get("ID").getAsInt();

            int baseValue = jsonObject.get("BaseValue").getAsInt();

            String tagName = jsonObject.get("TagName").getAsString();


            SourceTagsJsonObject source = new SourceTagsJsonObject(baseValue, id, tagName);
            sourceTagJson.add(source);

        }

        System.out.println(sourceTagJson.get(0).getID());
    }

    public static void obtainCardJson() {
        //Obtain the Card Json
        sourceCardJson = new ArrayList<SourceCardsJsonObject>();

        File input = new File("C:/Users/Owner/Desktop/Personal Repos/StSJsonGenerator/src/main/resources/jsons/Cards.json");

        JsonArray ObjectArray = null;


        try {
            JsonElement Element = JsonParser.parseReader(new FileReader(input));
            ObjectArray = Element.getAsJsonArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (JsonElement obj : ObjectArray
        ) {
            JsonObject jsonObject = obj.getAsJsonObject();

            int id = jsonObject.get("ID").getAsInt();

            String name = jsonObject.get("CardName").getAsString();

            int baseScore = jsonObject.get("BaseScore").getAsInt();

            int aMod1 = jsonObject.get("ActMod1").getAsInt();

            int aMod2 = jsonObject.get("ActMod2").getAsInt();

            int aMod3 = jsonObject.get("ActMod3").getAsInt();

            int aMod4 = jsonObject.get("ActMod4").getAsInt();

            int oneOfRule = 0;
            if (jsonObject.has("OneOfRule")) {
                if (jsonObject.get("OneOfRule") != null && !jsonObject.get("OneOfRule").isJsonNull() && !jsonObject.get("OneOfRule").toString().isEmpty()) {
                    oneOfRule = jsonObject.get("OneOfRule").getAsInt();
                }
            }


            float oneOfModifier = 0;
            if (jsonObject.has("OneOfModifier")) {
                if (jsonObject.get("OneOfModifier") != null && !jsonObject.get("OneOfModifier").isJsonNull() && !jsonObject.get("OneOfModifier").toString().isEmpty()) {
                    oneOfModifier = jsonObject.get("OneOfModifier").getAsFloat();
                }
            }


            SourceCardsJsonObject source = new SourceCardsJsonObject(aMod1, aMod2, aMod3, aMod4, baseScore, name, id, oneOfModifier, oneOfRule);
            sourceCardJson.add(source);

        }



        System.out.println(sourceCardJson.get(0).getCardName());
    }

    public static void obtainRelicTagJson() {
        //Obtain the Relic Tag Json
        sourceRelicTagJson = new ArrayList<SourceRelicsTagRelJsonObject>();

        File input = new File("C:/Users/Owner/Desktop/Personal Repos/StSJsonGenerator/src/main/resources/jsons/RelicTag_Provided_Rel.json");

        JsonArray ObjectArray = null;


        try {
            JsonElement Element = JsonParser.parseReader(new FileReader(input));
            ObjectArray = Element.getAsJsonArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (JsonElement obj : ObjectArray
        ) {
            JsonObject jsonObject = obj.getAsJsonObject();

            int id = jsonObject.get("ID").getAsInt();


            int relicID = jsonObject.get("RelicID").getAsInt();

            int tagID = jsonObject.get("TagID").getAsInt();

            int modifier = 0;
            if (jsonObject.has("Modifier")) {
                if (jsonObject.get("Modifier") != null && !jsonObject.get("Modifier").isJsonNull() && !jsonObject.get("Modifier").toString().isEmpty()) {
                    modifier = jsonObject.get("Modifier").getAsInt();
                }
            }

            SourceRelicsTagRelJsonObject source = new SourceRelicsTagRelJsonObject(id, modifier, relicID, tagID);
            sourceRelicTagJson.add(source);

        }

        System.out.println(sourceRelicTagJson.get(0).getModifier());
    }

    public static void obtainCardTagRelJson() {
        //Obtain the Card Tag Rel Json
        sourceCardTagRelJson = new ArrayList<SourceCardTagProvJsonObject>();

        File input = new File("C:/Users/Owner/Desktop/Personal Repos/StSJsonGenerator/src/main/resources/jsons/CardTag_Provided_Rel.json");

        JsonArray ObjectArray = null;


        try {
            JsonElement Element = JsonParser.parseReader(new FileReader(input));
            ObjectArray = Element.getAsJsonArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (JsonElement obj : ObjectArray
        ) {
            JsonObject jsonObject = obj.getAsJsonObject();

            int id = jsonObject.get("ID").getAsInt();


            int cardID = jsonObject.get("CardID").getAsInt();

            int tagID = jsonObject.get("TagID").getAsInt();

            int value = 0;
            if (jsonObject.has("Value")) {
                if (jsonObject.get("Value") != null && !jsonObject.get("Value").isJsonNull() && !jsonObject.get("Value").toString().isEmpty()) {
                    value = jsonObject.get("Value").getAsInt();
                }
            }

            SourceCardTagProvJsonObject source = new SourceCardTagProvJsonObject(cardID, id, tagID, value);
            sourceCardTagRelJson.add(source);

        }

        System.out.println(sourceCardTagRelJson.get(0).getValue());
    }

    public static void obtainCardTagAffJson() {
        //Obtain the Card Tag Rel Json
        sourceCardTagAffJson = new ArrayList<SourceCardTagAffJsonObject>();

        File input = new File("C:/Users/Owner/Desktop/Personal Repos/StSJsonGenerator/src/main/resources/jsons/CardTag_Affected_Rel.json");

        JsonArray ObjectArray = null;


        try {
            JsonElement Element = JsonParser.parseReader(new FileReader(input));
            ObjectArray = Element.getAsJsonArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (JsonElement obj : ObjectArray
        ) {
            JsonObject jsonObject = obj.getAsJsonObject();

            int id = jsonObject.get("ID").getAsInt();


            int cardID = jsonObject.get("CardID").getAsInt();

            int tagID = jsonObject.get("TagID").getAsInt();

            int modifier = 0;
            if (jsonObject.has("Modifier")) {
                if (jsonObject.get("Modifier") != null && !jsonObject.get("Modifier").isJsonNull() && !jsonObject.get("Modifier").toString().isEmpty()) {
                    modifier = jsonObject.get("Modifier").getAsInt();
                }
            }

            float synergyModifer = 0;
            if (jsonObject.has("SynergyModifier")) {
                if (jsonObject.get("SynergyModifier") != null && !jsonObject.get("SynergyModifier").isJsonNull() && !jsonObject.get("SynergyModifier").toString().isEmpty()) {
                    synergyModifer = jsonObject.get("SynergyModifier").getAsFloat();
                }
            }

            SourceCardTagAffJsonObject source = new SourceCardTagAffJsonObject(cardID, id, modifier, synergyModifer, tagID);
            sourceCardTagAffJson.add(source);

        }

        System.out.println(sourceCardTagAffJson.get(0).getSynergyModifier());
    }

    // Fetches for tag names
    public static String fetchTagName(float tagID, SourceTagsJsonObject[] arrSourceTags)
    {
        String tagString = "tagIDNotFound";
        for (SourceTagsJsonObject tag : arrSourceTags
        ) {
            if (tag.getID() == tagID) {
                tagString = tag.getTagName();
            }
        }

        return tagString;
    }
}
