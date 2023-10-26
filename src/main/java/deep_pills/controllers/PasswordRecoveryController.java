package deep_pills.controllers;

import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.services.interfaces.AccountUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/api/password-recovery")
public class PasswordRecoveryController {
    private final AccountUpdateService accountUpdateService;

    @PutMapping("/account/password-recovery/{email}")
    public ResponseEntity<ResponseDTO<String>> patientPasswordRecovery(@PathVariable String email) throws Exception{
        accountUpdateService.newPasswordRecoveryRequest(email);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Password recovery request successful"));
    }
}
