package deep_pills.model.entities.schedule;

import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.enums.states.FreeDayStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FreeDay {
    @Id
    @Column(name = "freeDayId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long freeDayId;

    @ManyToOne
    @JoinColumn(name = "physician")
    Physician physician;

    @ManyToOne
    @JoinColumn(name = "schedule")
    Schedule schedule;

    @Enumerated()
    @Column(name = "freeDayStatus")
    FreeDayStatus freeDayStatus;
}
