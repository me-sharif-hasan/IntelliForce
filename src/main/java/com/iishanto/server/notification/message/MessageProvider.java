package com.iishanto.server.notification.message;

public class MessageProvider {
    private static Integer version=0;
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
                            },
                            "hover": {
                                "dynamicRegistration": true
                            },
                            "diagnostic": {
                                "dynamicRegistration": true
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
            }""";

    private String didOpenMessage = """
            {
                "jsonrpc": "2.0",
                "method": "textDocument/didOpen",
                "params": {
                    "textDocument": {
                        "uri": "file:///%s",
                        "languageId": "apex",
                        "version": %d,
                        "text": "%s"
                    }
                }
            }""";

    public String getInitRequest(String root, String uri) {
        return initRequest.formatted(uri, root);
    }

    public String getDidOpenRequest(String file, String content) {
        return didOpenMessage.formatted(file, version++,content);
    }

//    public String setInitRequest(String root,String uri) {}
}
