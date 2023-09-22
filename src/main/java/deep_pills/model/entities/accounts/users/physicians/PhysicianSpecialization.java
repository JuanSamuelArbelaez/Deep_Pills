package deep_pills.model.entities.accounts.users.physicians;

import deep_pills.model.enums.lists.Specialization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "PhysicianSpecialization")
public class PhysicianSpecialization {
    @Id
    @Column(name = "physicianSpecializationId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long physicianSpecializationId;

    @ManyToOne
    @JoinColumn(name = "physicianID")
    private Physician physician;

    @Enumerated
    @Column(name = "specializationID")
    private Specialization specialization;
}
