package deep_pills.model.enums;

import jakarta.persistence.Entity;

@Entity
public enum Allergy{
    Latex_Allergy("Allergic reaction to latex gloves"),
    Penicillin_Allergy("Hypersensitivity to penicillin antibiotics"),
    Sulfa_Allergy("Allergic reaction to sulfonamide medications"),
    Aspirin_Allergy("Allergic reaction to aspirin or salicylates"),
    Codeine_Allergy("Allergic reaction to codeine pain medication"),
    Morphine_Allergy("Hypersensitivity to morphine-based medications"),
    Anesthesia_Allergy("Allergic reaction to anesthesia agents"),
    Iodine_Allergy("Allergic reaction to iodine contrast dyes"),
    Shellfish_Allergy("Allergy to shellfish, such as shrimp or crab"),
    Milk_Allergy("Allergic reaction to milk and dairy products"),
    Egg_Allergy("Hypersensitivity to eggs and egg-containing foods"),
    Wheat_Allergy("Allergic reaction to wheat products"),
    Soy_Allergy("Allergic reaction to soy and soy-based foods"),
    Peanut_Allergy("Allergic reaction to peanuts and peanut products"),
    Tree_Nut_Allergy("Allergic reaction to tree nuts like almonds or walnuts"),
    Fish_Allergy("Allergic reaction to fish, such as salmon or tuna"),
    Sesame_Allergy("Allergic reaction to sesame seeds and oil"),
    Mold_Allergy("Allergy to mold spores"),
    Dust_Mite_Allergy("Allergic reaction to dust mites"),
    Pollen_Allergy("Allergic reaction to pollen from trees and plants"),
    Cat_Dander_Allergy("Allergic reaction to cat dander"),
    Dog_Dander_Allergy("Allergic reaction to dog dander"),
    Bee_Sting_Allergy("Allergic reaction to bee stings"),
    Wasp_Sting_Allergy("Allergic reaction to wasp stings"),
    Fire_Ant_Sting_Allergy("Allergic reaction to fire ant stings"),
    Mosquito_Allergy("Allergic reaction to mosquito bites"),
    Tick_Allergy("Allergic reaction to tick bites"),
    Grass_Allergy("Allergic reaction to grass pollen"),
    Ragweed_Allergy("Allergic reaction to ragweed pollen"),
    Cockroach_Allergy("Allergic reaction to cockroach allergens"),
    Hay_Fever("Seasonal allergic rhinitis"),
    Asthma_Allergy("Allergy-induced asthma"),
    Eczema_Allergy("Allergy-related eczema or dermatitis"),
    Contact_Dermatitis("Allergic contact dermatitis"),
    Drug_Rash_Allergy("Allergic reaction causing drug-induced rash"),
    Food_Additive_Allergy("Allergy to food additives and preservatives"),
    Nickel_Allergy("Allergy to nickel in jewelry and metal objects"),
    Latex_Glove_Allergy("Allergic reaction to latex gloves"),
    Radiation_Allergy("Allergic reaction to radiation therapy"),
    Chemotherapy_Allergy("Allergic reaction to chemotherapy drugs"),
    Ivory_Allergy("Allergic reaction to ivory dust or handling"),
    Plastic_Allergy("Allergic reaction to plastics or synthetic materials"),
    Rubber_Allergy("Allergic reaction to rubber products"),
    Blood_Transfusion_Allergy("Allergic reaction to blood transfusions"),
    Suture_Allergy("Allergic reaction to surgical sutures"),
    Dental_Anesthesia_Allergy("Allergic reaction to dental anesthesia"),
    Metal_Implant_Allergy("Allergic reaction to metal implants"),
    Chemical_Sensitivity("Hypersensitivity to various chemicals"),
    Vaccine_Allergy("Allergic reaction to vaccines or vaccine components"),
    Radiographic_Dye_Allergy("Allergic reaction to radiographic contrast dyes");
    private final String description;

    Allergy(String description) {
        this.description = description;
    }

    // Getter method to access the description
    public String getDescription() {
        return description;
    }
}
