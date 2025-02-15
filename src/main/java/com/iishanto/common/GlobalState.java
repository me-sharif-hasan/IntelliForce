package com.iishanto.common;

public class GlobalState {
    private GlobalState() {
    }
    private static GlobalState instance;
    public static GlobalState getInstance(){
        if(instance==null){
            instance=new GlobalState();
        }
        return instance;
    }

    private String currentFilePath;

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    public void setCurrentFilePath(String currentFilePath) {
        this.currentFilePath = currentFilePath;
    }

    private boolean openState = false;

    public void setOpenState(boolean openState) {
        this.openState = openState;
    }
    public boolean getOpenState() {
        boolean current = this.openState;
        this.openState = false;
        return current;
    }
}
