package deep_pills.model.entities.claims;

import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.accounts.Admin;
import deep_pills.model.entities.appointments.Appointment;
import jakarta.persistence.*;

@Entity
public class ClaimInfo {
    @Id
    @Column(name = "claim_Info_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long claim_Info_Id;

    @ManyToOne
    @Column(name = "patient_Id")
    private Patient patient;

    @ManyToOne
    @Column(name = "admin_Id")
    private Admin admin;

    @ManyToOne
    @Column(name = "appointment_Id")
    private Appointment appointment;
}
