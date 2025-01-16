package com.iishanto.server.hanlder.wrappers;

import com.google.gson.annotations.SerializedName;

public class DiagnosticsResult {

    @SerializedName("range")
    private Range range;

    @SerializedName("severity")
    private int severity;

    @SerializedName("message")
    private String message;

    public Range getRange() {
        return range;
    }

    public int getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public static class Range {
        @SerializedName("start")
        private Position start;

        @SerializedName("end")
        private Position end;

        public Position getStart() {
            return start;
        }

        public Position getEnd() {
            return end;
        }
    }

    public static class Position {
        @SerializedName("line")
        private int line;

        @SerializedName("character")
        private int character;

        public int getLine() {
            return line;
        }

        public int getCharacter() {
            return character;
        }
    }
}