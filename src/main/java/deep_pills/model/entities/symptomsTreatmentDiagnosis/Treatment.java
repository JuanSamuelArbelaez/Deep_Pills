package deep_pills.model.entities.symptomsTreatmentDiagnosis;
import deep_pills.model.enums.states.TreatmentState;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "treatment")
public class Treatment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "treatmentId")
    private Long treatmentId;

    @Column(name = "treatment")
    private String treatment;

    @Enumerated
    @Column(name = "state")
    private TreatmentState treatmentState;

    @OneToOne(mappedBy = "treatment")
    private TreatmentPlan treatmentPlan;
}
