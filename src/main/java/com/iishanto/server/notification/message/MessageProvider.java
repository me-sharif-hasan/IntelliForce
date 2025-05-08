package com.iishanto.server.notification.message;

import com.iishanto.common.Constants;
import com.iishanto.common.MessageType;

import java.io.File;

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
                        "rootUri": "%s",
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
        return initRequest.formatted(new File(uri).toURI(), root);
    }

    public String getDidOpenRequest(String file, String content) {
        String didOpenMessage = """
                {
                    "jsonrpc": "2.0",
                    "method": "textDocument/didOpen",
                    "params": {
                        "textDocument": {
                            "uri": "%s",
                            "languageId": "apex",
                            "version": %d,
                            "text": "%s"
                        }
                    }
                }""";

        return didOpenMessage.formatted(new File(file).toURI(), version++,content);
    }

    public String getCompletionMessage(String file,int line,int character){
        return """
                {
                    "jsonrpc": "2.0",
                    "id": %d,
                    "method": "textDocument/completion",
                    "params": {
                        "textDocument": {
                             "uri": "%s"
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
                }""".formatted(version++,new File(file).toURI(),line,character);
    }

    public String getDefinitionMessage(String file,int line,int character){
        return """
                {
                    "jsonrpc": "2.0",
                    "id": %s,
                    "method": "textDocument/definition",
                    "params": {
                        "textDocument": {
                             "uri": "%s"
                        },
                        "position": {
                            "line": %d,
                            "character": %d
                        }
                    }
                }""".formatted(MessageType.APEX_DEFINITION_MESSAGE,new File(file).toURI(),line,character);
    }

    public String getTypeDefinitionMessage(String file,int line,int character){
        return """
                {
                    "jsonrpc": "2.0",
                    "id": %s,
                    "method": "textDocument/typeDefinition",
                    "params": {
                        "textDocument": {
                             "uri": "%s"
                        },
                        "position": {
                            "line": %d,
                            "character": %d
                        }
                    }
                }""".formatted(MessageType.APEX_TYPE_DEFINITION,new File(file).toURI(),line,character);
    }

    public String getDidChangeRequest(String file, String content,int lastLine,int lastCharacter) {
        String didOpenMessage = """
                {
                    "jsonrpc": "2.0",
                    "method": "textDocument/didChange",
                    "params": {
                        "textDocument": {
                            "uri": "%s",
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

        return didOpenMessage.formatted(new File(file).toURI(), version++,content,lastLine,lastCharacter);
    }

    public String getDidCloseRequest(String file) {
        return """
                {
                    "jsonrpc": "2.0",
                    "method": "textDocument/didClose",
                    "params": {
                        "textDocument": {
                            "uri": "%s"
                        }
                    }
                }""".formatted(new File(file).toURI());
    }

    public String getPullDiagnosticsRequest(String file) {
        return """
                {
                  "jsonrpc": "2.0",
                  "method": "textDocument/diagnostic",
                  "params": {
                    "textDocument": {
                      "uri": "%s"
                    },
                    "identifier": "%s"
                  }
                }""".formatted(new File(file).toURI(),'$'+Math.random());
    }

    public String getDidSaveRequest(String file, String content) {
        return """
                {
                    "jsonrpc": "2.0",
                    "method": "textDocument/didSave",
                    "params": {
                        "textDocument": {
                            "uri": "%s",
                            "version": %d
                        }
                    }
                }""".formatted(new File(file).toURI(), version++);
    }
}
