package deep_pills.model.entities.schedule;


import deep_pills.model.enums.states.ScheduleState;
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
@Table(name = "schedule")
public class Schedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "scheduleId")
    private Long scheduleId;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "startTime")
    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Column(name = "endTime")
    @Temporal(TemporalType.TIME)
    private Date endTime;

    @Enumerated
    @Column(name = "state")
    private ScheduleState scheduleState;

    @OneToMany(mappedBy = "schedule")
    private List<PhysicianAppointmentSchedule> physicianAppointmentScheduleList;
}
