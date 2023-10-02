package deep_pills.model.entities.accounts.users.patients;
import deep_pills.model.enums.lists.Allergy;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PatientAllergy")
public class PatientAllergy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patientAllergyId")
    private long patientAllergyId;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "allergyId")
    private Allergy allergy;
}
