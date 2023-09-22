package deep_pills.model.entities.schedule;

import deep_pills.model.entities.appointments.Appointment;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "physicianAppointmentSchedule")
public class PhysicianAppointmentSchedule {
    @Id
    @Column(name = "physicianAppointmentScheduleId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long physicianAppontimentScheduleId;

    @OneToOne
    @JoinColumn(name = "appointment")
    private Appointment appointment;
}
