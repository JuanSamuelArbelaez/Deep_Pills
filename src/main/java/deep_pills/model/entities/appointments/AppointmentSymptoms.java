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
@Table(name = "appointment_symptoms")
public class AppointmentSymptoms implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_symptom_Id")
    private long appointment_symptom_Id;

    @ManyToOne
    @Column(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne
    @Column(name = "symptom_id")
    private Symptom symptom;
}