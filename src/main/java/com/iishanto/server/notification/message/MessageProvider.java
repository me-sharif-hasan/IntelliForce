package com.iishanto.server.notification.message;

public class MessageProvider {
    private String initRequest = """
            {
                "jsonrpc": "2.0",
                "id": 1,
                "method": "initialize",
                "params": {
                    "processId": null,
                    "rootUri": "file:///%s",
                    "rootPath": "%s",
                    "capabilities": {
                        "textDocument": {
                            "synchronization": {
                                "dynamicRegistration": true,
                                "willSave": true,
                                "willSaveWaitUntil": true,
                                "didSave": true
                            },
                            "completion": {
                                          "dynamicRegistration": true,
                                          "completionItem": {
                                            "snippetSupport": true,
                                            "commitCharactersSupport": true
                                          }
                                        }
                        },
                        "workspace": {
                            "applyEdit": true,
                            "workspaceEdit": {
                                "documentChanges": true
                            }
                        }
                    }
                }
            }
            """;

    public String getInitRequest(String root,String uri) {
        return initRequest.formatted(uri,root);
    }
}
