package deep_pills.model.entities.appointments;

import deep_pills.model.entities.symptoms_treatment_diagnostics.Symptom;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointmentSymptoms")
public class AppointmentSymptoms implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointmentSymptomId")
    private long appointmentSymptomId;

    @ManyToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "symptomId")
    private Symptom symptom;
}