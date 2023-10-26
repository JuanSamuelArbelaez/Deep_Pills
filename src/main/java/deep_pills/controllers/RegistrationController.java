package deep_pills.controllers;

import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.dto.registrations.RegisterPatientDTO;
import deep_pills.services.interfaces.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/api/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @PutMapping("/register-patient")
    public ResponseEntity<ResponseDTO<String>> patientRegistration(@Valid @RequestBody RegisterPatientDTO registerPatientDTO) throws Exception{
        registrationService.registerPatient(registerPatientDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Patient registration completed"));
    }

}
