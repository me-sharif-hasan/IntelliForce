package com.iishanto.server.hanlder.wrappers;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.util.List;

public class CompletionWrapper {

    private String jsonrpc;
    private int id;
    private Result result;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static CompletionWrapper getFromJsonObject(JsonObject jsonObject) {
        return new Gson().fromJson(jsonObject, CompletionWrapper.class);
    }

    public static class Result {
        private boolean isIncomplete;
        private List<CompletionItem> items;

        public boolean isIncomplete() {
            return isIncomplete;
        }

        public void setIncomplete(boolean incomplete) {
            isIncomplete = incomplete;
        }

        public List<CompletionItem> getItems() {
            return items;
        }

        public void setItems(List<CompletionItem> items) {
            this.items = items;
        }
    }

    public static class CompletionItem {
        private String label;
        private int kind;
        private String detail;
        private String sortText;
        private String insertText;
        private int insertTextFormat;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getKind() {
            return kind;
        }

        public void setKind(int kind) {
            this.kind = kind;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getSortText() {
            return sortText;
        }

        public void setSortText(String sortText) {
            this.sortText = sortText;
        }

        public String getInsertText() {
            return insertText;
        }

        public void setInsertText(String insertText) {
            this.insertText = insertText;
        }

        public int getInsertTextFormat() {
            return insertTextFormat;
        }

        public void setInsertTextFormat(int insertTextFormat) {
            this.insertTextFormat = insertTextFormat;
        }
    }
}
