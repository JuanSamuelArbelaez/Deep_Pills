package deep_pills.model.entities.accounts.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "User_Registration")
public class User_Registration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_ID")
    private Long registerId;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
}
