package deep_pills.controllers;

import deep_pills.dto.accounts.physician.InfoUpdatePhysicianDTO;
import deep_pills.dto.appointments.AppointmentServiceDTO;
import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.dto.logins.LoginDTO;
import deep_pills.dto.registrations.RegisterPhysicianDTO;
import deep_pills.dto.schedule.FreeDayRequestDTO;
import deep_pills.services.interfaces.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/physician")
public class PhysicianController {
    private final AccountUpdateService accountUpdateService;
    private final AppointmentService appointmentService;
    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;
    private final UserManagementService userManagementService;


    //Registration, Account Management and Authentication
    @PostMapping("/account/login")
    public ResponseEntity<ResponseDTO<String>> physicianLogin(@Valid @RequestBody LoginDTO loginDTO) throws Exception{
        authenticationService.login(loginDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Physician Logged in"));
    }
    @PostMapping("/account/register")
    public ResponseEntity<ResponseDTO<String>> physicianRegistration(@Valid @RequestBody RegisterPhysicianDTO registerPhysicianDTO) throws Exception{
        registrationService.registerPhysician(registerPhysicianDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Physician registration completed"));
    }

    @PutMapping("/account/info-update")
    public ResponseEntity<ResponseDTO<String>> patientInfoUpdate(@Valid @RequestBody InfoUpdatePhysicianDTO infoUpdatePhysicianDTO) throws Exception{
        accountUpdateService.updatePhysician(infoUpdatePhysicianDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Physician info update completed"));
    }

    @DeleteMapping("/account/delete-account/{physicianId}")
    public ResponseEntity<ResponseDTO<String>> deletePatientAccount(@PathVariable Long physicianId) throws Exception{
        userManagementService.disablePhysician(physicianId);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Physician account deleted"));
    }

    @PutMapping("/account/password-recovery/{physicianEmail}")
    public ResponseEntity<ResponseDTO<String>> patientPasswordRecovery(@PathVariable String physicianEmail) throws Exception{
        accountUpdateService.newPasswordRecoveryRequest(physicianEmail);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Physician password recovery request successful"));
    }

    //Appointments
    @PutMapping("/schedule/free-day-request")
    public ResponseEntity<ResponseDTO<String>> requestFreeDay(@Valid @RequestBody FreeDayRequestDTO freeDayRequestDTO) throws Exception{
        appointmentService.scheduleFreeDayForPhysician(freeDayRequestDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Free day for physician scheduled"));
    }

    @PutMapping("/appointments/service")
    public ResponseEntity<ResponseDTO<String>> serviceAppointment(@Valid @RequestBody AppointmentServiceDTO appointmentServiceDTO) throws Exception{
        appointmentService.serviceAppointment(appointmentServiceDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Appointment served successfully"));
    }
}
