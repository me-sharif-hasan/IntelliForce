package com.iishanto.listeners;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.iishanto.common.Logger;
import com.iishanto.server.hanlder.LspResponseListener;
import com.intellij.codeInsight.lookup.LookupElementBuilder;

import java.util.ArrayList;
import java.util.List;

public class AutoCompletionSuggestionCallback extends LspResponseListener {


    public List<LookupElementBuilder> getSuggestions() {
        List<LookupElementBuilder> suggestions = new ArrayList<>();
        try {
            JsonObject jsonObject = this.get();
            if (!jsonObject.get("result").getAsJsonObject().get("items").getAsJsonArray().isEmpty()) {
                JsonArray items = jsonObject.get("result").getAsJsonObject().get("items").getAsJsonArray();
                for (JsonElement item : items) {
                    try {
                        String label = item.getAsJsonObject().get("label").getAsString();
                        LookupElementBuilder lookupElementBuilder = LookupElementBuilder.create(label);
                        suggestions.add(lookupElementBuilder);
                    } catch (Throwable e) {
                        Logger.log(e);
                    }
                }
            }
        } catch (Exception e) {
            Logger.log(e);
        }
        return suggestions;
    }


    @Override
    public String getTargetMethod() {
        return "textDocument/completion";
    }

    @Override
    public boolean isMatching(JsonObject jsonObject) {
        try {
            return jsonObject.get("result").getAsJsonObject().get("isIncomplete").getAsString() != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isCompletableFuture() {
        return true;
    }
}
