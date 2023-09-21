package deep_pills.model.entities.appointments;
import deep_pills.model.entities.accounts.users.patients.Patient;
import jakarta.persistence.*;

import lombok.*;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Appointment")
public class Appointment {
    @Id
    @Column(name = "appointment_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentId;

    @ManyToOne
    @Column(name = "patient_Id")
    private Patient patient;

}
