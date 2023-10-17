package deep_pills.model.entities.accounts.users.physicians;

import deep_pills.model.entities.accounts.users.User;
import deep_pills.model.entities.schedule.FreeDay;
import deep_pills.model.entities.schedule.PhysicianAppointmentSchedule;
import deep_pills.model.entities.schedule.Shift;
import deep_pills.model.entities.symptomsTreatmentDiagnosis.TreatmentPlan;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Physician")
public class Physician extends User {
    @ManyToOne
    @JoinColumn(name = "shift")
    private Shift shift;
    @OneToMany(mappedBy = "physician")
    private List<PhysicianSpecialization> physicianSpecialization;

    @OneToMany(mappedBy = "physician")
    private List<TreatmentPlan> treatmentPlanList;

    @OneToMany(mappedBy = "physician")
    private List<PhysicianAppointmentSchedule> physicianAppointmentScheduleList;

    @OneToOne(mappedBy = "physician")
    private PhysicianRegistration physicianRegistration;

    @OneToMany(mappedBy = "physician")
    private List<FreeDay> freeDays;
}
