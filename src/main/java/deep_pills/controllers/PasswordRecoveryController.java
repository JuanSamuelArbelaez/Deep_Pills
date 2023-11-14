package deep_pills.controllers;

import deep_pills.dto.accounts.PasswordRecoveryDTO;
import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.services.interfaces.AccountUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/api/password-recovery")
public class PasswordRecoveryController {
    private final AccountUpdateService accountUpdateService;

    @PutMapping("/{email}")
    public ResponseEntity<ResponseDTO<String>> userPasswordRecovery(@PathVariable String email) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, "Password recovery request successful: "+accountUpdateService.newPasswordRecoveryRequest(email)));
    }

    @PostMapping("/accept")
    public ResponseEntity<ResponseDTO<String>> userPasswordRecovery(@Valid @RequestBody PasswordRecoveryDTO passwordRecoveryDTO) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, "Password recovery: "+ accountUpdateService.acceptPasswordRecoveryCode(passwordRecoveryDTO)));
    }
}
