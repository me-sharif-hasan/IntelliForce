package com.iishanto.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;

public final class Utility {
    public static boolean isValidExecutable(String cliPath) {
        String[] validExecutables = {"sf", "sf.cmd", "sf.exe"};
        for (String executable : validExecutables) {
            if (cliPath.endsWith(executable)) {
                return true;
            }
        }
        return false;
    }


    public static String combineExecutable(String cliPath){
        String containingFolder="";
        if (cliPath != null && !cliPath.isBlank()) {
            char splitChars;
            if(cliPath.contains("\\")){
                splitChars='\\';
            }else{
                splitChars='/';
            }
            if(Utility.isValidExecutable(cliPath)){
                int idx=cliPath.lastIndexOf(splitChars);
                containingFolder=cliPath.substring(0,idx+1);
            }else{
                containingFolder=cliPath;
            }
            if(!containingFolder.endsWith(String.valueOf(splitChars))){
                containingFolder+=splitChars;
            }
            containingFolder+="sf";
            return containingFolder;
        }else{
            return null;
        }
    }

    public static String getSfCliRootFromPath() {
        try {
            CompletableFuture<String> path=new CompletableFuture<>();
            String command = System.getProperty("os.name").toLowerCase().contains("win") ? "where sf" : "which sf";
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String cliPath = reader.readLine();
            System.out.println("CLI PATH: "+cliPath);
            String containingFolder=combineExecutable(cliPath);
            System.out.println("CONTAINING FOLDER: "+containingFolder);
            return containingFolder;
        } catch (Exception e) {
            Logger.log(e);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Utility.getSfCliRootFromPath());;
    }
}
