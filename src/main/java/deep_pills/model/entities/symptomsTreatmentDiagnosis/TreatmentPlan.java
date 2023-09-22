package deep_pills.model.entities.symptomsTreatmentDiagnosis;

import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.entities.appointments.Appointment;
import deep_pills.model.enums.lists.Diagnosis;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "treatmentPlan")
public class TreatmentPlan implements Serializable {
    @Id
    @Column(name = "treatmentPlanId")
    private Long treatmentPlanId;

    @ManyToOne
    @JoinColumn(name = "physician")
    private Physician physician;

    @ManyToOne
    @JoinColumn(name = "appointment")
    private Appointment appointment;

    @Enumerated
    @Column(name = "diagnosis")
    private Diagnosis diagnosis;

    @OneToOne
    @JoinColumn(name = "treatment")
    private Treatment treatment;
}
