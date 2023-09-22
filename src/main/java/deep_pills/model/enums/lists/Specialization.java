package deep_pills.model.enums.lists;

import jakarta.persistence.*;
import lombok.*;

public enum Specialization {
    Internal_Medicine("Diagnosis and treatment of adult diseases"),
    Pediatrics("Medical care for infants, children, and adolescents"),
    Cardiology("Diagnosis and treatment of heart diseases"),
    Dermatology("Treatment of skin disorders"),
    Neurology("Diagnosis and treatment of neurological disorders"),
    Oncology("Study and treatment of cancer"),
    Orthopedics("Treatment of musculoskeletal conditions"),
    Gastroenterology("Diagnosis and treatment of digestive system disorders"),
    Endocrinology("Study of hormones and endocrine system disorders"),
    Rheumatology("Diagnosis and treatment of autoimmune diseases and musculoskeletal disorders"),
    Nephrology("Diagnosis and treatment of kidney diseases"),
    Urology("Diagnosis and treatment of urinary system disorders"),
    Gynecology("Medical care for female reproductive system health"),
    Obstetrics("Care for pregnant women and childbirth"),
    Ophthalmology("Diagnosis and treatment of eye diseases"),
    Otolaryngology("Treatment of ear, nose, and throat disorders"),
    Psychiatry("Diagnosis and treatment of mental illnesses"),
    Psychology("Study of behavior and mental processes"),
    Anesthesiology("Administration of anesthesia during surgeries"),
    Radiology("Medical imaging and interpretation"),
    Emergency_Medicine("Treatment of acute medical conditions"),
    Infectious_Disease("Diagnosis and treatment of infectious diseases"),
    Allergy_and_Immunology("Diagnosis and treatment of allergies and immune system disorders"),
    Pulmonology("Diagnosis and treatment of respiratory diseases"),
    Hematology("Study and treatment of blood disorders"),
    Physical_Therapy("Rehabilitation and physical health improvement"),
    Occupational_Therapy("Therapeutic interventions for daily life skills"),
    Dentistry("Oral health care and dental treatments"),
    Neurosurgery("Surgical treatment of neurological conditions"),
    Cardiothoracic_Surgery("Surgical treatment of heart and chest conditions"),
    Plastic_Surgery("Cosmetic and reconstructive surgical procedures"),
    Dermatologic_Surgery("Surgical treatment of skin conditions"),
    Orthodontics("Correction of teeth and jaw alignment"),
    Periodontics("Treatment of gum diseases"),
    Endodontics("Root canal treatment and dental pulp disorders"),
    Prosthodontics("Restoration and replacement of missing teeth"),
    Oral_and_Maxillofacial_Surgery("Surgical treatment of face, jaw, and mouth conditions"),
    Geriatrics("Medical care for the elderly population"),
    Pain_Management("Management of chronic pain conditions"),
    Sports_Medicine("Treatment of sports-related injuries and performance enhancement"),
    Nuclear_Medicine("Use of radioactive substances for diagnosis and treatment"),
    Genetics("Study of genes and hereditary conditions"),
    Forensic_Medicine("Application of medical knowledge in legal cases"),
    Sleep_Medicine("Diagnosis and treatment of sleep disorders"),
    Travel_Medicine("Healthcare for travelers and prevention of travel-related illnesses"),
    Aerospace_Medicine("Medical care related to space exploration and aviation"),
    Integrative_Medicine("Combining conventional and alternative medicine approaches"),
    Telemedicine("Remote healthcare and consultations using technology"),
    Palliative_Medicine("Specialized care for patients with serious illnesses"),
    Adolescent_Medicine("Medical care for adolescents and young adults");

    private final String description;

    Specialization(String description) { this.description = description; }
    public String getDescription() {return description;}
}
