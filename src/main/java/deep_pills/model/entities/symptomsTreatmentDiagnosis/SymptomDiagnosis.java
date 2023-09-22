package deep_pills.model.entities.symptomsTreatmentDiagnosis;

import deep_pills.model.enums.lists.Diagnosis;
import deep_pills.model.enums.lists.Symptom;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SymptomDiagnosis")
public class SymptomDiagnosis implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "symptomDiagnosisId")
    private long symptomDiagnosisId;

    @Enumerated
    @Column(name = "diagnosis")
    private Diagnosis diagnosis;

    @Enumerated
    @Column(name = "Symptom")
    private Symptom Symptom;
}
