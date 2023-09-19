package deep_pills.model.entities.memberships;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.enums.Membership_State;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Membership")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_ID")
    private Long membershipId;

    @OneToOne
    @JoinColumn(name = "patient_ID")
    private Patient patient;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "policy_ID")
    private Policy policy;

    @ManyToOne
    @JoinColumn(name = "state", referencedColumnName = "id_Membership_State")
    private Membership_State state;

    // Getters and setters
}
