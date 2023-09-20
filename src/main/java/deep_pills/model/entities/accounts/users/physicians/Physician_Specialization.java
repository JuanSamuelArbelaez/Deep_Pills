package deep_pills.model.entities.accounts.users.physicians;

import deep_pills.model.enums.Specialization;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Physician_Specialization")
public class Physician_Specialization {
    @Id
    @ManyToOne
    @JoinColumn(name = "physician_ID")
    private Physician physician;

    @Id
    @ManyToOne
    @JoinColumn(name = "specialization_ID")
    private Specialization specialization;
}
