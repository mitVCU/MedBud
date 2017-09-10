package com.mittens.medbud.data;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by mit on 9/9/17.
 */

@DynamoDBTable(tableName = "MedicationInformation")
public class Mapper {
    String MedicationName;
    String MedicationQuantity;
    String MedicationTime;
    String MedicationFrequency;

    @DynamoDBAttribute(attributeName = "MedicationFrequency")
    public String getMedicationFrequency() {
        return MedicationFrequency;
    }

    public void setMedicationFrequency(String medicationFrequency) {
        MedicationFrequency = medicationFrequency;
    }

    @DynamoDBHashKey(attributeName = "MedicationName")
    public String getMedicationName() {
        return MedicationName;
    }

    public void setMedicationName(String medicationName) {
        MedicationName = medicationName;
    }

    @DynamoDBAttribute(attributeName = "MedicationQuantity")
    public String getMedicationQuantity() {
        return MedicationQuantity;
    }

    public void setMedicationQuantity(String medicationQuantity) {
        MedicationQuantity = medicationQuantity;
    }

    @DynamoDBAttribute(attributeName = "MedicationTime")
    public String getMedicationTime() {
        return MedicationTime;
    }

    public void setMedicationTime(String medicationTime) {
        MedicationTime = medicationTime;
    }


}
