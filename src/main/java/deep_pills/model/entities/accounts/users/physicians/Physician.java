package deep_pills.model.entities.accounts.users.physicians;

import deep_pills.model.entities.accounts.users.User;
import deep_pills.model.entities.schedule.PhysicianAppointmentSchedule;
import deep_pills.model.entities.symptoms_treatment_diagnostics.TreatmentPlan;
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
    @OneToMany(mappedBy = "physician")
    private PhysicianSpecialization physicianSpecialization;

    @OneToMany(mappedBy = "physician")
    private List<TreatmentPlan> treatmentPlanList;

    @OneToMany(mappedBy = "physician")
    private List<PhysicianAppointmentSchedule> physicianAppointmentScheduleList;

    @OneToOne(mappedBy = "physician")
    private PhysicianRegistration physicianRegistration;
}
