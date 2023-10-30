package deep_pills.controllers;

import deep_pills.dto.accounts.physician.InfoUpdatePhysicianDTO;
import deep_pills.dto.appointments.*;
import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.dto.schedule.FreeDayRequestDTO;
import deep_pills.services.interfaces.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/api/physician")
@SecurityRequirement(name = "bearerAuth")
public class PhysicianController {
    private final AccountUpdateService accountUpdateService;
    private final AppointmentService appointmentService;


    //Info Update
    @PostMapping("/account/info-update")
    public ResponseEntity<ResponseDTO<String>> patientInfoUpdate(@Valid @RequestBody InfoUpdatePhysicianDTO infoUpdatePhysicianDTO) throws Exception{
        accountUpdateService.updatePhysician(infoUpdatePhysicianDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Physician info update completed"));
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

    @GetMapping ("/appointments/list-all/{physicianPersonalId}")
    public ResponseEntity<ResponseDTO<List<AppointmentGenericDTO>>> allAppointmentsByPhysicianId(@PathVariable String physicianPersonalId) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(true, appointmentService.allAppointmentsByPhysicianId(physicianPersonalId)));
    }

    @GetMapping("/appointments/list-upcoming/{physicianPersonalId}")
    public ResponseEntity<ResponseDTO<List<AppointmentGenericDTO>>> upcomingAppointmentsByPhysicianId(@PathVariable String physicianPersonalId) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(true, appointmentService.upcomingAppointmentsByPhysicianId(physicianPersonalId)));
    }

    @GetMapping("/appointments/list-past/{physicianPersonalId}")
    public ResponseEntity<ResponseDTO<List<AppointmentGenericDTO>>> pastAppointmentsByPhysicianId(@PathVariable String physicianPersonalId) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(true, appointmentService.pastAppointmentsByPhysicianId(physicianPersonalId)));
    }

    @GetMapping("/appointments/list-date")
    public ResponseEntity<ResponseDTO<List<AppointmentGenericDTO>>> dateSpecificAppointmentsByPhysicianId(@Valid @RequestBody AppointmentDatePhysicianSearchDTO appointmentDatePhysicianSearchDTO) throws Exception {
        return ResponseEntity.ok(new ResponseDTO<>(true, appointmentService.dateSpecificAppointmentsByPhysicianId(appointmentDatePhysicianSearchDTO)));
    }
}
