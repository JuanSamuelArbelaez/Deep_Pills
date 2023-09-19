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
    @JoinColumn(name = "diagnosisId")
    @ManyToOne()
    private Diagnosis diagnosis;

    @Id
    @JoinColumn(name = "SymtomId")
    @ManyToOne
    private Symptom Symtom;
}
