package deep_pills.model.entities.claims;

import deep_pills.model.enums.Claim_State;
import deep_pills.model.enums.Claim_Type;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    @Enumerated
    @Column(name = "claim_type")
    private Claim_Type claimType;

    @Column(name = "claim_date")
    private Date claimDate;

    @Column(name = "details")
    private String details;

    @Enumerated
    @Column(name = "claim_status")
    private Claim_State claimStatus;

    @OneToOne(mappedBy = "claim")
    private ClaimInfo claimInfo;

    @OneToMany(mappedBy = "claim")
    private List<Message> messages;
}
