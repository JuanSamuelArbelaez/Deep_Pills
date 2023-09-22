package deep_pills.model.entities.passwordRecovery;

import deep_pills.model.entities.accounts.users.User;
import deep_pills.model.entities.notifications.EMail;
import deep_pills.model.enums.states.PasswordRecoveryRequestState;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="passwordRecoveryRequest")
public class PasswordRecoveryRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "passwordRecoveryRequestId")
    private long passwordRecoveryRequestId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(name = "dateTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Enumerated
    @Column(name = "state")
    private PasswordRecoveryRequestState passwordRecoveryRequestState;

    @OneToMany(mappedBy = "passwordRecoveryRequest")
    private List<EMail> emails;
}
