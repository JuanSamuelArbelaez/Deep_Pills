package deep_pills.model.entities.symptoms_treatment_diagnostics;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
public class TreatmentPlan {
    @Id
    @Column(name = "treatmentPlanId")
    private Long treatmentPlanId;
}
