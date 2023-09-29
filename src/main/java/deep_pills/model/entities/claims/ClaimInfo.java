package deep_pills.model.entities.claims;

import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.Admin;
import deep_pills.model.entities.appointments.Appointment;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimInfo implements Serializable {
    @Id
    @Column(name = "claimInfoId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long claimInfoId;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "adminId")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointment;

    @OneToOne
    @JoinColumn(name = "claimId")
    private Claim claim;
}
