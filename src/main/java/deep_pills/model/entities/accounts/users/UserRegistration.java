package deep_pills.model.entities.accounts.users;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserRegistration")
public class UserRegistration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registerId")
    private Long registerId;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
