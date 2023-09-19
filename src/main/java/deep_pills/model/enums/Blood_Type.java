package deep_pills.model.enums;

import jakarta.persistence.Entity;

@Entity
public enum Blood_Type {
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-");

    Blood_Type(String bloodType){
        this.bloodType = bloodType;
    }
    private String bloodType;
    public String getBloodType() { return bloodType; }
}
