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
    Integer MedicationQuantity;
    String MedicationTime;
    String MedicationFrequency;
    Boolean TakenToday;
    Integer TimesTaken;
    String lastTime;

    @DynamoDBHashKey(attributeName = "MedicationName")
    public String getMedicationName() {
        return MedicationName;
    }

    public void setMedicationName(String medicationName) {
        MedicationName = medicationName;
    }

    @DynamoDBAttribute(attributeName = "MedicationQuantity")
    public Integer getMedicationQuantity() {
        return MedicationQuantity;
    }

    public void setMedicationQuantity(Integer medicationQuantity) {
        MedicationQuantity = medicationQuantity;
    }

    @DynamoDBAttribute(attributeName = "MedicationTime")
    public String getMedicationTime() {
        return MedicationTime;
    }

    public void setMedicationTime(String medicationTime) {
        MedicationTime = medicationTime;
    }

    @DynamoDBAttribute(attributeName = "MedicationFrequency")
    public String getMedicationFrequency() {
        return MedicationFrequency;
    }

    public void setMedicationFrequency(String medicationFrequency) {
        MedicationFrequency = medicationFrequency;
    }

    @DynamoDBAttribute(attributeName = "TakenToday")
    public Boolean getTakenToday() {
        return TakenToday;
    }

    public void setTakenToday(Boolean takenToday) {
        TakenToday = takenToday;
    }

    @DynamoDBAttribute(attributeName = "TimesTaken")
    public Integer getTimesTaken() {
        return TimesTaken;
    }

    public void setTimesTaken(Integer timesTaken) {
        TimesTaken = timesTaken;
    }

    @DynamoDBAttribute(attributeName = "lastTime")
    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }
}
