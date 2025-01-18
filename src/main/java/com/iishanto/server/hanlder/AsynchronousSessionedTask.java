package com.iishanto.server.hanlder;

import com.intellij.codeInsight.lookup.LookupElementBuilder;

import java.util.List;

public interface AsynchronousSessionedTask {
    public void completeTask(int sessionId, List<LookupElementBuilder> lookupElementBuilders);
}
