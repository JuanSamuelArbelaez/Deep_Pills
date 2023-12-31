package deep_pills.model.entities.schedule;

import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.enums.states.ShiftState;
import deep_pills.model.enums.types.ShiftType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shift")
public class Shift {
    @Id
    @Column(name = "shiftTypeId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shiftId;

    @Column(name = "startTime")
    @Temporal(TemporalType.TIME)
    private Time startTime;

    @Column(name = "endTime")
    @Temporal(TemporalType.TIME)
    private Time endTime;

    @Column(name = "days")
    private String days; //Format: Monday Tuesday Wednesday Friday

    @Enumerated
    @Column(name = "shiftType")
    private ShiftType shiftType;

    @OneToMany(mappedBy = "shift")
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "shift")
    private List<Physician> physicianList;

    @Enumerated
    @Column(name = "state")
    private ShiftState state;

}
