package com.iishanto.server.notification;

import com.google.gson.JsonObject;
import com.iishanto.common.Configs;
import com.iishanto.server.ApexLanguageServerDefinition;
import com.iishanto.server.hanlder.LspResponseListener;
import com.iishanto.server.notification.message.MessageProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class NotificationHub extends LspResponseListener{
    private final ApexLanguageServerDefinition apexLanguageServerDefinition;
    private final MessageProvider messageProvider;
    private static NotificationHub instance;
    private final Map<String,LspResponseListener> listenerRegistry=new HashMap<>();

    boolean isLocked=true;

    private NotificationHub() throws IOException {
        listenerRegistry.put(getTargetMethod(),this);
        apexLanguageServerDefinition=ApexLanguageServerDefinition.getInstance();
        apexLanguageServerDefinition.start(listenerRegistry);
        messageProvider=new MessageProvider();
    }

    public void initialize() throws IOException {
        System.out.println("Initializing NotificationHub");
        String initMessage = messageProvider.getInitRequest(Configs.getInstance().getProjectRoot(), Configs.getInstance().getProjectRoot());
        apexLanguageServerDefinition.submitNotification(initMessage);
        isLocked=false;
    }

    public void didOpen(String file, String content, LspResponseListener lspResponseListener) throws IOException {
        if(Configs.getInstance().getProjectRoot()==null||isLocked) return;
        String didOpenMessage=messageProvider.getDidOpenRequest(file,content);
        listenerRegistry.put(lspResponseListener.getTargetMethod(), lspResponseListener);
        apexLanguageServerDefinition.submitNotification(didOpenMessage);
    }

    public void completion(String path,int line,int character,LspResponseListener lspResponseListener) throws IOException {
        if(Configs.getInstance().getProjectRoot()==null||isLocked) return;
        listenerRegistry.put(lspResponseListener.getTargetMethod(),lspResponseListener);
        apexLanguageServerDefinition.submitNotification(
                messageProvider.getCompletionMessage(path,line,character)
        );
    }


    public void definition(String path,int line,int character,LspResponseListener lspResponseListener) throws IOException {
        if(Configs.getInstance().getProjectRoot()==null||isLocked) return;
        if(lspResponseListener!=null){
            listenerRegistry.put("definition",lspResponseListener);
        }
        apexLanguageServerDefinition.submitNotification(
                messageProvider.getDefinitionMessage(path,line,character)
        );
    }

    public void typeDefinition(String path,int line,int character,LspResponseListener lspResponseListener) throws IOException {
        if(Configs.getInstance().getProjectRoot()==null||isLocked) return;
        listenerRegistry.put(lspResponseListener.getTargetMethod(),lspResponseListener);
        apexLanguageServerDefinition.submitNotification(
                messageProvider.getTypeDefinitionMessage(path,line,character)
        );
    }

    public static NotificationHub getInstance() throws IOException {
        if(instance==null){
            instance=new NotificationHub();
        }
        return instance;
    }



    @Override
    public void listen(JsonObject jsonObject) {
        try{
            System.out.println("indexer - done - iishanto");
            isLocked=false;
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
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public String getTargetMethod() {
        return "indexer/done";
    }
}
