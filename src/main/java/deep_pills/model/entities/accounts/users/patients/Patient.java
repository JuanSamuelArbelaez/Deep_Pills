package deep_pills.model.entities.accounts.users.patients;
import deep_pills.model.entities.accounts.users.User;
import deep_pills.model.entities.claims.ClaimInfo;
import deep_pills.model.entities.memberships.Membership;
import deep_pills.model.enums.BloodType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Patient")
public class Patient extends User{
    @Column(name = "dateBirth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated
    @Column(name = "BloodType")
    private BloodType bloodType;

    @OneToMany(mappedBy = "patient")
    private List<PatientAlergy> patientAlergies;

    @OneToMany(mappedBy = "patient")
    private List<ClaimInfo> claimInfo;

    @OneToOne(mappedBy = "owner")
    private Membership ownedMembership;

    @ManyToOne
    private Membership beneficiaryMembership;



    // Additional patient-specific attributes and methods
}
