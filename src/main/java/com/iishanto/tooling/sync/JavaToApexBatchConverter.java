package com.iishanto.tooling.sync;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class JavaToApexBatchConverter {
    public static void main(String[] args) {
        try {
            Path currentPath = Paths.get(".");
            try (Stream<Path> walk = Files.walk(currentPath)) {
                walk.filter(Files::isRegularFile)
                        .filter(p -> p.toString().toLowerCase().endsWith(".java"))
                        .filter(p -> !p.getFileName().toString().equals("JavaToApexBatchConverter.java"))
                        .forEach(path -> {
                            try {
                                System.out.println("Processing: " + path);
                                convertFile(path.toString());
                            } catch (IOException e) {
                                System.out.println("ERROR");
                                e.printStackTrace();
                            }
                        });
            }
            System.out.println("Conversion completed!");
        } catch (IOException e) {
            System.err.println("Error walking through directory: " + e.getMessage());
        }
    }

    private static void convertFile(String inputPath) throws IOException {
        String content = Files.readString(Path.of(inputPath));

        content = content.replaceAll("package\\s+[a-zA-Z0-9_.*]+;\\s*", "");

        content = content.replaceAll("import\\s+[a-zA-Z0-9_.*]+(\\s*\\.\\s*\\*\\s*)?;\\s*", "");

        content = convertQuotes(content);

        String outputPath = inputPath.substring(0, inputPath.lastIndexOf('.')) + ".cls";

        Files.writeString(Path.of(outputPath), content);
        System.out.println("Created: " + outputPath);
    }

    private static String convertQuotes(String content) {
        StringBuilder result = new StringBuilder();
        boolean inString = false;
        boolean inComment = false;
        boolean inLineComment = false;
        char[] chars = content.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char current = chars[i];

            // Handle comments
            if (!inString) {
                if (!inComment && !inLineComment && i < chars.length - 1) {
                    if (current == '/' && chars[i + 1] == '*') {
                        inComment = true;
                    } else if (current == '/' && chars[i + 1] == '/') {
                        inLineComment = true;
                    }
                } else if (inComment && i < chars.length - 1) {
                    if (current == '*' && chars[i + 1] == '/') {
                        inComment = false;
                    }
                } else if (inLineComment && current == '\n') {
                    inLineComment = false;
                }
            }

            // Handle strings
            if (!inComment && !inLineComment) {
                if (current == '"' && (i == 0 || chars[i - 1] != '\\')) {
                    inString = !inString;
                    result.append('\'');
                    continue;
                }
            }

            result.append(current);
        }

        return result.toString();
    }
}