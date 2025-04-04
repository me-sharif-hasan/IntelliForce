package com.iishanto.ide.annotator;

import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class ApexMissingTypeAggregator{
    private static ApexMissingTypeAggregator instance;
    private final Queue<String> queue=new LinkedList<>();
    private final ReentrantLock lock=new ReentrantLock();
    private ApexMissingTypeAggregator(){
    }
    public void queue(String typeName) {
        queue.add(typeName);
        fetchMissingTypes();
    }

    synchronized private void fetchMissingTypes() {
        lock.lock();
        if(!queue.isEmpty()){
            String typeName=queue.poll();
            System.out.println("Type Name: "+typeName);
        }
        lock.unlock();
    }

    public static ApexMissingTypeAggregator getInstance() {
        if(instance==null){
            instance=new ApexMissingTypeAggregator();
        }
        return instance;
    }
}
