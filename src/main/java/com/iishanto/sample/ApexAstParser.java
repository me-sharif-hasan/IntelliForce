package com.iishanto.sample;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kotlin.Pair;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class ApexAstParser {
    private Gson gson;
    private ApexAstParser(){
        gson = new Gson();
    }
    private static ApexAstParser instance;
    public static ApexAstParser getInstance() {
        if (instance == null) {
            return new ApexAstParser();
        }
        return instance;
    }

    public void parse(String astString){
        JsonElement jsonElement = gson.fromJson(astString, JsonElement.class);
        JsonElement root=jsonElement.getAsJsonObject().get("apex.jorje.semantic.compiler.parser.ParserOutput");
        if(root!=null){
            JsonElement parseErrors=root.getAsJsonObject().get("parseErrors");
            JsonElement internalErrors=root.getAsJsonObject().get("internalErrors");
            if(parseErrors!=null&& !parseErrors.getAsJsonArray().isEmpty()||
                    internalErrors!=null && !internalErrors.getAsJsonArray().isEmpty() ){
                throw new RuntimeException("Parse error");
            }

            JsonElement unit=root.getAsJsonObject().get("unit");
            if(unit==null){
                throw new RuntimeException("No apex coding section found!");
            }
            //convert to map <string,JsonElement>
            Map<Integer, Integer> map = new TreeMap<>();
            processJsonElementRecursively(unit,map);
            System.out.println("Map: " + map);
        }
    }

    public static void main(String[] args) throws IOException {
       //Load the sample_ast.json from resource folder
        String astString = new String(ApexAstParser.class.getResourceAsStream("/json/sample_ast.json").readAllBytes());
        ApexAstParser parser = ApexAstParser.getInstance();
        parser.parse(astString);
    }

    private Pair<Integer,Integer> processJsonElementRecursively(JsonElement unit, Map<Integer,Integer> charBasedBlockMapOutput){
        int blockStart=Integer.MAX_VALUE;
        int blockEnd=Integer.MIN_VALUE;
        if(!unit.isJsonPrimitive()&&unit.isJsonObject()){
            for (Map.Entry<String, JsonElement> entry : unit.getAsJsonObject().entrySet()) {
                try{
                    String key = entry.getKey();
                    JsonElement value = entry.getValue();
                    if(value.isJsonPrimitive()) continue;
                    if(value.isJsonArray()){
                        for (JsonElement element : value.getAsJsonArray()) {
                            if (element.isJsonPrimitive()) continue;
                            Pair<Integer,Integer> range = processJsonElementRecursively(element,charBasedBlockMapOutput);
                            blockStart=Math.min(blockStart,range.getFirst());
                            blockEnd=Math.max(blockEnd,range.getSecond());
                        }
                    }else if(value.getAsJsonObject().get("value")!=null){
                        //leaf node
                        System.out.println("Leaf node: " + value.getAsJsonObject().get("value").getAsString() + " for unit: " + unit.getAsJsonObject().get("@class").getAsString() + " key: " + key);
                        JsonObject location = value.getAsJsonObject().get("loc").getAsJsonObject();
                        int start = location.get("startIndex").getAsInt();
                        int end = location.get("endIndex").getAsInt();
                        blockStart=Math.min(blockStart,start);
                        blockEnd=Math.max(blockEnd,end);
                    }else{
                        //recursive call
                        Pair<Integer,Integer> range = processJsonElementRecursively(value,charBasedBlockMapOutput);
                        blockStart=Math.min(blockStart,range.getFirst());
                        blockEnd=Math.max(blockEnd,range.getSecond());
                    }

                }catch (Exception e){
                    System.out.println("DUMDUM: " + e.getMessage());
                }
            }
        }
        if(blockStart!=Integer.MAX_VALUE && blockEnd!=Integer.MIN_VALUE){
            charBasedBlockMapOutput.put(blockStart,blockEnd);
        }
        System.out.println("Block start: " + blockStart + " Block end: " + blockEnd + " for unit: " + unit.getAsJsonObject().get("@class").getAsString());
        return new Pair<>(blockStart,blockEnd);
    }
}
