package deep_pills.model.enums;

public enum BloodType {
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-");

    BloodType(String bloodType){
        this.bloodType = bloodType;
    }
    private String bloodType;
    public String getBloodType() { return bloodType; }
}
