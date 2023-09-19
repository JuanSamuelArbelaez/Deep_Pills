package deep_pills.model.entities.appointments;
import jakarta.persistence.*;
@Entity
@Table(name = "appointment_symptoms")
public class Appointment_Symptoms extends Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "appointment_id")
    private Long appointmentId;
    @Column(name = "symptom_id")
    private Long symptomId;
    public Appointment_Symptoms() {
    }
    public Appointment_Symptoms(Long appointmentId, Long symptomId) {
        this.appointmentId = appointmentId;
        this.symptomId = symptomId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
    public Long getSymptomId() {
        return symptomId;
    }
    public void setSymptomId(Long symptomId) {
        this.symptomId = symptomId;
    }
}