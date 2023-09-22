package deep_pills.model.entities.memberships;

import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.enums.states.MembershipState;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Membership")
public class Membership implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membershipID")
    private Long membershipId;

    @OneToOne
    @JoinColumn(name = "ownerId")
    private Patient owner;

    @OneToMany(mappedBy = "beneficiaryMembership")
    private List<Patient> beneficiaries;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "policyId")
    private Policy policy;

    @Enumerated
    @Column(name = "state")
    private MembershipState state;

    @OneToMany(mappedBy = "membership")
    private List<MembershipCharge> membershipCharges;
}
