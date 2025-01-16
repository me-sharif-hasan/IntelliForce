package com.iishanto.server.notification.message;

public class MessageProvider {
    private static Integer version=0;

    public String getInitRequest(String root, String uri) {
        String initRequest = """
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
        return initRequest.formatted(uri, root);
    }

    public String getDidOpenRequest(String file, String content) {
        String didOpenMessage = """
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

        return didOpenMessage.formatted(file, version++,content);
    }

    public String getCompletionMessage(String file,int line,int character){
        return """
                {
                    "jsonrpc": "2.0",
                    "id": %d,
                    "method": "textDocument/completion",
                    "params": {
                        "textDocument": {
                             "uri": "file:///%s"
                        },
                        "position": {
                            "line": %d,
                            "character": %d
                        },
                        "context": {
                            "triggerKind": 1,
                            "triggerCharacter": "."
                        }
                    }
                }""".formatted(version++,file,line,character);
    }
}
