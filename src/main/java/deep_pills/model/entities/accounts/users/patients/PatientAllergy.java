package deep_pills.model.entities.accounts.users.patients;
import deep_pills.model.enums.lists.Allergy;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PatientAllergy")
public class PatientAllergy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patientAllergyId")
    private long patient_allergy_Id;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "allergyId")
    private Allergy allergy;
}
