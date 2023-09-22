package deep_pills.model.entities.appointments;

import deep_pills.model.entities.accounts.users.patients.Patient;
import deep_pills.model.entities.claims.ClaimInfo;
import deep_pills.model.entities.notifications.EMail;
import deep_pills.model.entities.schedule.PhysicianAppointmentSchedule;
import deep_pills.model.entities.symptoms_treatment_diagnostics.TreatmentPlan;
import deep_pills.model.enums.states.AppointmentState;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;


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

    @OneToMany(mappedBy = "appointment")
    private List<ClaimInfo> claimInfoList;

    @OneToMany(mappedBy = "appointment")
    private List<TreatmentPlan> treatmentPlanList;

    @OneToMany(mappedBy = "appointment")
    private List<EMail> emails;

    @OneToOne(mappedBy = "appointment")
    private PhysicianAppointmentSchedule physicianAppointmentSchedule;

    @OneToMany(mappedBy = "appointment")
    private List<AppointmentSymptoms> appointmentSymptoms;
}
