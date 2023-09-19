package deep_pills.model.entities.accounts.users.patients;
import deep_pills.model.entities.accounts.users.User;
import deep_pills.model.enums.Blood_Type;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Patient")
public class Patient extends User{
    @Column(name = "date_Birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "bloodType", referencedColumnName = "id_BloodType")
    private Blood_Type bloodType;


    // Additional patient-specific attributes and methods
}
