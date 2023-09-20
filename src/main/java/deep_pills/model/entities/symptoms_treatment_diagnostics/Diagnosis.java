package deep_pills.model.entities.symptoms_treatment_diagnostics;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diagnosis")
public class Diagnosis {
    @Id
    @Column(name = "diagnosisId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long symptomId;
    @Column(name = "diagnosisName")
    private String diagnosisName;
    @Column(name = "diagnosisDescription")
    private String diagnosisDescription;
}
