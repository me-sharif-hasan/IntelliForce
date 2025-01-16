package com.iishanto.server.notification;

import com.iishanto.common.Configs;
import com.iishanto.server.ApexLanguageServerDefinition;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.notification.message.MessageProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NotificationHub {
    private ApexLanguageServerDefinition apexLanguageServerDefinition;
    private final MessageProvider messageProvider;
    private static NotificationHub instance;
    private Map<String,LspResponseListener> listenerRegistry=new HashMap<String,LspResponseListener>();
    private NotificationHub() throws IOException {
        apexLanguageServerDefinition=ApexLanguageServerDefinition.getInstance();
        apexLanguageServerDefinition.start(listenerRegistry);
        messageProvider=new MessageProvider();
    }

    public void initialize() throws IOException {
        System.out.println("Initializing NotificationHub");
        String initMessage = messageProvider.getInitRequest(Configs.getInstance().getProjectRoot(), Configs.getInstance().getProjectRoot());
        apexLanguageServerDefinition.submitNotification(initMessage);

        String scanRequest= """
                {
                  "jsonrpc": "2.0",
                  "id": 10,
                  "method": "workspace/symbol",
                  "params": {
                    "query": ""
                  }
                }""";
        apexLanguageServerDefinition.submitNotification(scanRequest);
    }

    public void didOpen(String file, String content, LspResponseListener lspResponseListener) throws IOException {
        if(Configs.getInstance().getProjectRoot()==null) return;
        String didOpenMessage=messageProvider.getDidOpenRequest(file,content);
        listenerRegistry.put(lspResponseListener.getTargetMethod(), lspResponseListener);
        apexLanguageServerDefinition.submitNotification(didOpenMessage);
    }

    public static NotificationHub getInstance() throws IOException {
        if(instance==null){
            instance=new NotificationHub();
        }
        return instance;
    }
}
