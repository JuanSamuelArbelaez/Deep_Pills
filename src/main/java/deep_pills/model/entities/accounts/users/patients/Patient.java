package deep_pills.model.entities.accounts.users.patients;
import deep_pills.model.entities.accounts.users.User;
import deep_pills.model.entities.claims.Claim;
import deep_pills.model.entities.claims.Claim_Info;
import deep_pills.model.enums.Blood_Type;
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
    @Column(name = "date_Birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Enumerated
    @Column(name = "Blood_Type")
    private Blood_Type bloodType;

    @OneToMany(mappedBy = "patient")
    private List<Patient_Alergy> patientAlergies;

    @OneToMany
    private List<Claim> claims;

    // Additional patient-specific attributes and methods
}
