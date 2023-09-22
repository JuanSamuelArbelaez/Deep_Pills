package deep_pills.model.entities.memberships;

import deep_pills.model.enums.states.ChargeState;
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
@Table(name = "membershipCharge")
public class MembershipCharge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "membershipChargeId")
    private Long membershipChargeId;

    @Column(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Enumerated
    @Column(name = "state")
    private ChargeState chargeState;

    @ManyToOne
    @JoinColumn(name = "membership")
    private Membership membership;

    @OneToMany(mappedBy = "membershipCharge")
    private List<MembershipPayment> membershipPaymentList;
}
