package com.iishanto.tooling.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
import java.util.ArrayList;
import java.util.List;

public class JsonExtractor {
    public static String extractJson(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        String trimmed = input.trim();
        List<String> candidates = new ArrayList<>();
        int start = -1;
        int braceCount = 0;
        int bracketCount = 0;
        boolean inString = false;
        char stringDelimiter = '\0';

        // Step 1: Find all potential JSON substrings
        for (int i = 0; i < trimmed.length(); i++) {
            char c = trimmed.charAt(i);

            if (inString) {
                if (c == stringDelimiter && trimmed.charAt(i - 1) != '\\') {
                    inString = false;
                }
                continue;
            }

            if (c == '"' || c == '\'') {
                inString = true;
                stringDelimiter = c;
                continue;
            }

            if (c == '{' || c == '[') {
                if (start == -1) {
                    start = i;
                }
                if (c == '{') braceCount++;
                else bracketCount++;
            } else if (c == '}' || c == ']') {
                if (c == '}') braceCount--;
                else bracketCount--;
                if (braceCount == 0 && bracketCount == 0 && start != -1) {
                    String candidate = trimmed.substring(start, i + 1);
                    candidates.add(candidate);
                    start = -1; // Reset to find the next potential JSON
                }
            }
        }

        // Step 2 & 3: Validate and select the largest valid JSON
        String largestValidJson = null;
        int maxLength = -1;

        for (String candidate : candidates) {
            try {
                JsonElement jsonElement = JsonParser.parseString(candidate);
                if (jsonElement.isJsonObject() || jsonElement.isJsonArray()) {
                    if (candidate.length() > maxLength) {
                        largestValidJson = candidate;
                        maxLength = candidate.length();
                    }
                }
            } catch (JsonParseException e) {
                // Skip invalid JSON
            }
        }

        return largestValidJson;
    }
}