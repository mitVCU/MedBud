package com.mittens.medbud.data;


/**
 * Created by mit on 9/9/17.
 */

public class User {
    String MedicationName;
    Integer MedicationQuantity;
    String MedicationTime;
    Integer MedicationFrequency;

    public Integer getMedicationFrequency() {
        return MedicationFrequency;
    }

    public void setMedicationFrequency(Integer medicationFrequency) {
        MedicationFrequency = medicationFrequency;
    }

    public String getMedicationName() {
        return MedicationName;
    }

    public void setMedicationName(String medicationName) {
        MedicationName = medicationName;
    }

    public Integer getMedicationQuantity() {
        return MedicationQuantity;
    }

    public void setMedicationQuantity(Integer medicationQuantity) {
        MedicationQuantity = medicationQuantity;
    }

    public String getMedicationTime() {
        return MedicationTime;
    }

    public void setMedicationTime(String medicationTime) {
        MedicationTime = medicationTime;
    }


}
