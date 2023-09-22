package deep_pills.model.entities.schedule;

import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.entities.appointments.Appointment;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "physicianAppointmentSchedule")
public class PhysicianAppointmentSchedule implements Serializable{
    @Id
    @Column(name = "physicianAppointmentScheduleId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long physicianAppontimentScheduleId;

    @OneToOne
    @JoinColumn(name = "appointment")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "schedule")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "physician")
    private Physician physician;
}
