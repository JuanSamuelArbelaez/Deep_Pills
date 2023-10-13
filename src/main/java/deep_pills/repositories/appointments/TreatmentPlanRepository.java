package deep_pills.repositories.appointments;

import deep_pills.model.entities.symptomsTreatmentDiagnosis.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {
}
