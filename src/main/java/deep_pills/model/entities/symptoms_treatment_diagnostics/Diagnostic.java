package deep_pills.model.entities.symptoms_treatment_diagnostics;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "diagnostics")
public class Diagnostic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "diagnostic_id")
    private String diagnosticId;
    @Column(name = "diagnostic_type")
    private String diagnosticType;
    @Column(name = "diagnostic_date")
    private Date diagnosticDate;
    @Column(name = "diagnostic_result")
    private String diagnosticResult;
    @Column(name = "diagnostic_notes")
    private String diagnosticNotes;
    public Diagnostic() {
    }
    public Diagnostic(String diagnosticId, String diagnosticType, Date diagnosticDate, String diagnosticResult, String diagnosticNotes) {
        this.diagnosticId = diagnosticId;
        this.diagnosticType = diagnosticType;
        this.diagnosticDate = diagnosticDate;
        this.diagnosticResult = diagnosticResult;
        this.diagnosticNotes = diagnosticNotes;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDiagnosticId() {
        return diagnosticId;
    }
    public void setDiagnosticId(String diagnosticId) {
        this.diagnosticId = diagnosticId;
    }
    public String getDiagnosticType() {
        return diagnosticType;
    }
    public void setDiagnosticType(String diagnosticType) {
        this.diagnosticType = diagnosticType;
    }
    public Date getDiagnosticDate() {
        return diagnosticDate;
    }
    public void setDiagnosticDate(Date diagnosticDate) {
        this.diagnosticDate = diagnosticDate;
    }
    public String getDiagnosticResult() {
        return diagnosticResult;
    }
    public void setDiagnosticResult(String diagnosticResult) {
        this.diagnosticResult = diagnosticResult;
    }
    public String getDiagnosticNotes() {
        return diagnosticNotes;
    }
    public void setDiagnosticNotes(String diagnosticNotes) {
        this.diagnosticNotes = diagnosticNotes;
    }
}
