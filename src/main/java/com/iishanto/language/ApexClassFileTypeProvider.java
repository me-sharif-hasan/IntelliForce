package com.iishanto.language;

import com.iishanto.language.sf.apex.filetypes.ApexClassFileType;

public class ApexClassFileTypeProvider extends ApexClassFileType {
    public static ApexClassFileType INSTANCE = new ApexClassFileTypeProvider();

    public ApexClassFileTypeProvider(){
        try{
//            NotificationHub.getInstance().initialize();
        }catch (Exception e){
            System.out.println("error - iishanto"+e.getLocalizedMessage());
        }
    }
}
