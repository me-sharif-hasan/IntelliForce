package com.iishanto.apex.annotator;

import com.iishanto.apex.psi.*;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

public class ApexTypeMismatchAnnotator implements Annotator {
    public ApexTypeMismatchAnnotator(){
        System.out.println("instance is created");
    }
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        PsiElement type=null;
        if(element instanceof ApexMethodDeclaration methodDeclaration){
            PsiElement id=methodDeclaration.getIdentifier();
            PsiElement typeName=methodDeclaration.getClassInterfaceOrPrimitiveType();
            System.out.println("Method:"+id.getText()+"; "+(typeName==null?"null":typeName.getText()));
        }else if(element instanceof ApexFieldDeclarator fieldDeclarator){
            PsiElement id=fieldDeclarator.getVariableDefinitionList().get(0).getIdentifier();
            PsiElement typeName=fieldDeclarator.getClassInterfaceOrPrimitiveType();
            System.out.println("Field:"+id.getText()+"; "+ typeName.getText());
        }else if(element instanceof ApexLocalVariableDeclarator apexLocalVariableDeclarator){
            AtomicInteger i= new AtomicInteger();
            apexLocalVariableDeclarator.getVariableDefinitionList().forEach(apexVariableDefinition -> {
                System.out.println("Variable: "+i+" "+apexVariableDefinition.getIdentifier().getText());
                i.getAndIncrement();
            });
        }
//            System.out.println("Arguments:"+element.getText()+"; "+element.getClass().getName());
//            List <ApexClassDeclaration> classes = ApexPsiUtilities.getApexClassDeclarations(element.getProject());
//            for(ApexClassDeclaration cls:classes){
//                System.out.println("Class:"+cls.getName());
//                ApexClassBody classBody=cls.getClassBody();
//                for(PsiElement elm:classBody.getChildren()){
//                    System.out.println("Element:"+elm.getText());
//                }
//            }
//        if(element instanceof ApexArguments arguments){
//            //type of expression
//            for(ApexExpression expression:arguments.getExpressionList().getExpressionList()){
//                System.out.println("Expression:"+expression.getText()+' '+expression.getClass().getName());
//            }
//
//        }
    }
}