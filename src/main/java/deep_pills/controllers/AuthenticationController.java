package deep_pills.controllers;

import deep_pills.dto.authentications.TokenDTO;
import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.dto.logins.LoginDTO;
import deep_pills.services.interfaces.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<String>> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception{
        TokenDTO tokenDTO = authenticationService.login(loginDTO);
        return ResponseEntity.ok(new ResponseDTO<>(false, tokenDTO.token()));
    }
}
