package deep_pills.model.entities.memberships;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Policy")
public class Policy implements Serializable { //Policy finished
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_ID")
    private Long policyId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "maxPatients")
    private Integer maxPatients;

    @Column(name = "maxAppointments")
    private Integer maxAppointments;

    @Column(name = "cost")
    private double cost;

    @OneToMany(mappedBy = "policy")
    private List<Membership> memberships;
}
