package deep_pills.model.entities.memberships;

import deep_pills.model.enums.states.PaymentState;
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
@Table(name = "membershipPayment")
public class MembershipPayment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "membershipPaymentId")
    private Long membershipPaymentId;

    @ManyToOne
    @JoinColumn(name = "membershipChargeId")
    private MembershipCharge membershipCharge;

    @Column(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Column(name = "amount")
    private double amount;

    @Column(name = "concept")
    private String concept;

    @Enumerated
    @Column(name = "state")
    private PaymentState paymentState;
}
