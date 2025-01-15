package com.iishanto.server.notification;

import com.iishanto.server.ApexLanguageServerDefinition;
import com.iishanto.server.notification.message.MessageProvider;

import java.io.IOException;

public class NotificationHub {
    private ApexLanguageServerDefinition apexLanguageServerDefinition;
    private final MessageProvider messageProvider;
    private static NotificationHub instance;
    private NotificationHub() throws IOException {
        apexLanguageServerDefinition=ApexLanguageServerDefinition.getInstance();
        apexLanguageServerDefinition.start();
        messageProvider=new MessageProvider();
    }

    public void initialize() throws IOException {
        String initMessage = messageProvider.getInitRequest("C:/Users/BJIT/Desktop/SFDX/SF_PDF-master/","C:/Users/BJIT/Desktop/SFDX/SF_PDF-master/");
        apexLanguageServerDefinition.submitNotification(initMessage);
    }

    public static NotificationHub getInstance() throws IOException {
        if(instance==null){
            instance=new NotificationHub();
        }
        return instance;
    }
}
