package deep_pills.model.enums.lists;

public enum Diagnosis {
    Hypertension("High_blood_pressure"),
    Diabetes("High_blood_sugar"),
    Asthma("Chronic_respiratory_condition"),
    Migraine("Severe_headache"),
    Arthritis("Joint_inflammation"),
    Bronchitis("Respiratory_infection"),
    Osteoporosis("Weak_bones"),
    Hypothyroidism("Underactive_thyroid"),
    Hyperthyroidism("Overactive_thyroid"),
    Anemia("Low_red_blood_cells"),
    Coronary_Artery_Disease("Blocked_heart_arteries"),
    Gastroesophageal_Reflux_Disease("Acid_reflux"),
    Chronic_Obstructive_Pulmonary_Disease("Lung_disease"),
    Alzheimers_Disease("Neurodegenerative_condition"),
    Parkinsons_Disease("Movement_disorder"),
    Multiple_Sclerosis("Autoimmune_condition"),
    Bipolar_Disorder("Mood_swings"),
    Schizophrenia("Psychotic_disorder"),
    Depression("Mood_disorder"),
    Anxiety_Disorder("Excessive_worry"),
    Obsessive_Compulsive_Disorder("OCD"),
    Post_Traumatic_Stress_Disorder("PTSD"),
    Autism_Spectrum_Disorder("Neurodevelopmental_disorder"),
    Attention_Deficit_Hyperactivity_Disorder("ADHD"),
    Irritable_Bowel_Syndrome("Digestive_disorder"),
    Crohns_Disease("Inflammatory_bowel_disease"),
    Ulcerative_Colitis("Colon_inflammation"),
    Celiac_Disease("Gluten_intolerance"),
    Endometriosis("Painful_condition"),
    Polycystic_Ovary_Syndrome("Hormonal_disorder"),
    Fibromyalgia("Chronic_pain_condition"),
    Ovarian_Cancer("Gynecological_cancer"),
    Breast_Cancer("Malignant_breast_growth"),
    Lung_Cancer("Pulmonary_malignancy"),
    Prostate_Cancer("Male_reproductive_cancer"),
    Leukemia("Blood_cancer"),
    Melanoma("Skin_cancer"),
    Rheumatoid_Arthritis("Autoimmune_joint_condition"),
    Osteoarthritis("Degenerative_joint_condition"),
    Gout("Joint_inflammation_due_to_crystals"),
    Kidney_Stones("Renal_calculi"),
    Gallstones("Biliary_calculi"),
    Pneumonia("Lung_infection"),
    Tuberculosis("TB"),
    Influenza("Flu"),
    Common_Cold("Upper_respiratory_infection"),
    Hepatitis("Liver_inflammation"),
    Cirrhosis("Liver_scarring"),
    Pancreatitis("Pancreas_inflammation"),
    Appendicitis("Appendix_inflammation"),
    Cholecystitis("Gallbladder_inflammation"),
    Diverticulitis("Colon_inflammation"),
    Peptic_Ulcer_Disease("Gastric_ulcer"),
    Ovarian_Cyst("Fluid-filled_ovarian_mass"),
    Testicular_Cancer("Male_reproductive_cancer"),
    Bladder_Infection("Cystitis"),
    Uterine_Fibroids("Noncancerous_uterine_growths"),
    Hemorrhoids("Swollen_rectal_veins"),
    Varicose_Veins("Enlarged_veins"),
    Deep_Vein_Thrombosis("DVT"),
    Atrial_Fibrillation("Irregular_heart_rhythm"),
    Myocardial_Infarction("Heart_attack"),
    Congestive_Heart_Failure("CHF"),
    Atherosclerosis("Artery_hardening"),
    Stroke("Cerebrovascular_accident"),
    Gastrointestinal_Bleeding("GI_bleed"),
    Eczema("Skin_inflammation"),
    Psoriasis("Chronic_skin_condition"),
    Rosacea("Facial_redness"),
    Acne("Skin_pimples"),
    Cellulitis("Skin_infection"),
    Lupus("Autoimmune_condition"),
    Scleroderma("Connective_tissue_disease"),
    Sjgrens_Syndrome("Autoimmune_condition"),
    Raynauds_Disease("Blood_vessel_spasms"),
    Peripheral_Neuropathy("Nerve_damage"),
    Gastrointestinal_Reflux_Disease("GERD"),
    Anorexia_Nervosa("Eating_disorder"),
    Bulimia_Nervosa("Eating_disorder"),
    Aplastic_Anemia("Bone_marrow_failure"),
    Thalassemia("Hemoglobin_disorder"),
    Hemophilia("Blood_clotting_disorder"),
    Von_Willebrand_Disease("Bleeding_disorder"),
    Osteogenesis_Imperfecta("Brittle_bone_disease"),
    Marfan_Syndrome("Connective_tissue_disorder"),
    Turner_Syndrome("Chromosomal_disorder"),
    Down_Syndrome("Chromosomal_disorder"),
    Cystic_Fibrosis("Lung_and_digestive_disorder"),
    Muscular_Dystrophy("Muscle_weakening"),
    Huntington_s_Disease("Neurodegenerative_condition"),
    Amyotrophic_Lateral_Sclerosis("ALS"),
    Charcot_Marie_Tooth_Disease("Neuromuscular_disorder"),
    Tourette_Syndrome("Neurological_disorder"),
    Epilepsy("Seizure_disorder"),
    Narcolepsy("Sleep_disorder"),
    Sleep_Apnea("Breathing_interruption_during_sleep"),
    Chronic_Kidney_Disease("Renal_impairment"),
    Glomerulonephritis("Kidney_inflammation"),
    Nephrotic_Syndrome("Kidney_disorder"),
    Hematuria("Blood_in_urine");

    private String description;

    Diagnosis(String description) {this.description = description;}

    String getDescription(){return this.description;}
}