package com.iishanto.server.notification.message;

import com.iishanto.common.Constants;
import com.iishanto.common.MessageType;

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
                                    "dynamicRegistration": true,
                                    "interFileDependencies": true,
                                    "workspaceDiagnostics": true
                                }
                            },
                            "referencesProvider": true,
                            "implementationProvider": true,
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

    public String getDefinitionMessage(String file,int line,int character){
        return """
                {
                    "jsonrpc": "2.0",
                    "id": %s,
                    "method": "textDocument/definition",
                    "params": {
                        "textDocument": {
                             "uri": "file:///%s"
                        },
                        "position": {
                            "line": %d,
                            "character": %d
                        }
                    }
                }""".formatted(MessageType.APEX_DEFINITION_MESSAGE,file,line,character);
    }

    public String getTypeDefinitionMessage(String file,int line,int character){
        return """
                {
                    "jsonrpc": "2.0",
                    "id": %s,
                    "method": "textDocument/typeDefinition",
                    "params": {
                        "textDocument": {
                             "uri": "file:///%s"
                        },
                        "position": {
                            "line": %d,
                            "character": %d
                        }
                    }
                }""".formatted(MessageType.APEX_TYPE_DEFINITION,file,line,character);
    }

    public String getDidChangeRequest(String file, String content,int lastLine,int lastCharacter) {
        String didOpenMessage = """
                {
                    "jsonrpc": "2.0",
                    "method": "textDocument/didChange",
                    "params": {
                        "textDocument": {
                            "uri": "file:///%s",
                            "version": %d
                        },
                        "contentChanges": [
                            {
                                "text": "%s",
                                "range": {
                                      "start": { "line": 0, "character": 0 },
                                      "end": { "line": %d, "character": %d }
                                }
                            }
                        ]
                    }
                }""";

        return didOpenMessage.formatted(file, version++,content,lastLine,lastCharacter);
    }

    public String getDidCloseRequest(String file) {
        return """
                {
                    "jsonrpc": "2.0",
                    "method": "textDocument/didClose",
                    "params": {
                        "textDocument": {
                            "uri": "file:///%s"
                        }
                    }
                }""".formatted(file);
    }

    public String getPullDiagnosticsRequest(String file) {
        return """
                {
                  "jsonrpc": "2.0",
                  "method": "textDocument/diagnostic",
                  "params": {
                    "textDocument": {
                      "uri": "file:///%s"
                    },
                    "identifier": "%s"
                  }
                }""".formatted(file,'$'+Math.random());
    }

    public String getDidSaveRequest(String file, String content) {
        return """
                {
                    "jsonrpc": "2.0",
                    "method": "textDocument/didSave",
                    "params": {
                        "textDocument": {
                            "uri": "file:///%s",
                            "version": %d
                        }
                    }
                }""".formatted(file, version++);
    }
}
