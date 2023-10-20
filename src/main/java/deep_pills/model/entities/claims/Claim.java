package deep_pills.model.entities.claims;

import deep_pills.model.entities.notifications.EMail;
import deep_pills.model.enums.states.ClaimState;
import deep_pills.model.enums.types.ClaimType;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "claims")
public class Claim implements Serializable { //Entity finished
    @Id
    @Column(name = "claimId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long claimId;

    @Enumerated
    @Column(name = "claim_type")
    private ClaimType claimType;

    @Column(name = "claim_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date claimDate;

    @Column(name = "details")
    private String details;

    @Enumerated
    @Column(name = "claim_status")
    private ClaimState claimStatus;

    @OneToOne(mappedBy = "claim")
    private ClaimInfo claimInfo;

    @OneToMany(mappedBy = "claim")
    private List<Message> messages;

    @OneToMany(mappedBy = "claim")
    private List<EMail> emails;
}
