package deep_pills.model.entities.accounts.users;

import deep_pills.model.entities.notifications.EMail;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserRegistration")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserRegistration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "registerId")
    private Long registerId;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToMany(mappedBy = "userRegistration")
    private List<EMail> emails;
}
