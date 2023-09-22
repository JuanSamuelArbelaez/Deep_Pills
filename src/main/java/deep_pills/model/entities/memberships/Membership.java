package deep_pills.model.entities.memberships;
import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.enums.states.MembershipState;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Membership")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_ID")
    private Long membershipId;

    @OneToOne
    @JoinColumn(name = "owner_Id")
    private Patient owner;

    @OneToMany(mappedBy = "beneficiaryMembership")
    private List<Patient> beneficiaries;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "policy_ID")
    private Policy policy;


    @Enumerated
    @JoinColumn(name = "state", referencedColumnName = "id_Membership_State")
    private MembershipState state;

    // Getters and setters
}
