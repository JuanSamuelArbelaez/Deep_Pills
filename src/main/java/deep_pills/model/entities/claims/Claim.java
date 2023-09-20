package deep_pills.model.entities.claims;
import deep_pills.model.enums.Claim_State;
import deep_pills.model.enums.Claim_Type;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "claims")

public class Claim implements Serializable {
    @Id
    @Column(name = "claimId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    @ManyToOne
    @Enumerated
    @Column(name = "claim_type")
    private Claim_Type claimType;
    @Column(name = "claim_date")
    private Date claimDate;

    @Column(name = "details")
    private String details;

    @ManyToOne
    @Enumerated
    @Column(name = "claim_status")
    private Claim_State claimStatus;

    /*  Class rework

        Getters, Setters and Constructors defined by Annotations above Class declaration
        Unnecesary columns/attributes removed
        Required columns/attributes added
     */
}
