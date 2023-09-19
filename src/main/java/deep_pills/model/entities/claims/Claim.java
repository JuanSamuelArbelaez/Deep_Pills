package deep_pills.model.entities.claims;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "claims")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "claim_id")
    private String claimId;
    @Column(name = "claim_type")
    private String claimType;
    @Column(name = "claim_date")
    private Date claimDate;
    @Column(name = "claim_amount")
    private Double claimAmount;
    @Column(name = "claim_status")
    private String claimStatus;
    public Claim() {
    }
    public Claim(String claimId, String claimType, Date claimDate, Double claimAmount, String claimStatus) {
        this.claimId = claimId;
        this.claimType = claimType;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.claimStatus = claimStatus;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getClaimId() {
        return claimId;
    }
    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }
    public String getClaimType() {
        return claimType;
    }
    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }
    public Date getClaimDate() {
        return claimDate;
    }
    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }
    public Double getClaimAmount() {
        return claimAmount;
    }
    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }
    public String getClaimStatus() {
        return claimStatus;
    }
    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }
}
