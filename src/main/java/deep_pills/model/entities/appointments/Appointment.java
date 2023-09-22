package deep_pills.model.entities.appointments;

import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.enums.states.AppointmentState;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Appointment")
public class Appointment {
    @Id
    @Column(name = "appointmentId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;

    @Column(name = "location")
    private String location;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "detailedReasons")
    private String detailedReasons;

    @Column(name = "doctorsNotes")
    private String doctorsNotes;

    @Enumerated
    @Column(name = "state")
    private AppointmentState appointmentState;
}
