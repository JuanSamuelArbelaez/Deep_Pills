package deep_pills.model.entities.symptoms_treatment_diagnostics;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Symptom_Diagnosis")
public class Symptom_Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "symptom_diagnosis_Id")
    private long symptom_diagnosis_id;

    @Column(name = "diagnosisId")
    @ManyToOne()
    private Diagnosis diagnosis;


    @Column(name = "SymtomId")
    @ManyToOne
    private Symptom Symptom;
}
