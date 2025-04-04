package com.iishanto.tooling.sync;

import com.iishanto.tooling.network.response.FieldMetadata;
import com.iishanto.tooling.network.response.SObjectDescribeResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SObjectDescribeToApexClassConverterWorker {
    /**
     * Converts an SObjectDescribeResponse into an Apex class string representation.
     * @param describe The SObjectDescribeResponse containing SObject metadata.
     * @return A string representing the Apex class, or null if input is invalid.
     */
    public static String createApexClass(SObjectDescribeResponse describe) {
        if (describe == null || describe.getName() == null || describe.getFields() == null) {
            return null;
        }

        StringBuilder apexClass = new StringBuilder();

        apexClass.append("global class ").append(describe.getName()).append(" {\n");

        for (FieldMetadata field : describe.getFields()) {
            String apexType = mapSalesforceTypeToApexType(field.getType());
            if (apexType == null) {
                continue;
            }
            if(field.getType().equals("reference")){
                String relationShipName=field.getRelationshipName();
                if(relationShipName!=null){
                    String parentClass="SObject";
                    if(field.getReferenceTo().size()==1){
                        parentClass=field.getReferenceTo().get(0);
                    }
                    apexClass.append("    global ")
                            .append(parentClass)
                            .append(" ")
                            .append(relationShipName)
                            .append(";\n");
                }
            }
            apexClass.append("    global ")
                    .append(apexType)
                    .append(" ")
                    .append(field.getName())
                    .append(";\n");
        }
        apexClass.append("}\n");
        return apexClass.toString();
    }

    /**
     * Maps Salesforce field types to Apex data types.
     * @param salesforceType The Salesforce field type (e.g., "string", "datetime").
     * @return The corresponding Apex type, or null if unmapped.
     */
    private static String mapSalesforceTypeToApexType(String salesforceType) {
        if (salesforceType == null) {
            return null;
        }

        switch (salesforceType.toLowerCase()) {
            case "string":
            case "textarea":
            case "url":
            case "email":
            case "phone":
            case "picklist":
            case "multipicklist":
                return "String";
            case "boolean":
                return "Boolean";
            case "int":
            case "integer":
                return "Integer";
            case "double":
            case "currency":
            case "percent":
                return "Double";
            case "date":
                return "Date";
            case "datetime":
                return "DateTime";
            case "id":
            case "reference":
                return "Id";
            case "recordtype":
                return "RecordType";
            default:
                // Handle unsupported types (e.g., "anytype", "location") by skipping
                return null;
        }
    }
}