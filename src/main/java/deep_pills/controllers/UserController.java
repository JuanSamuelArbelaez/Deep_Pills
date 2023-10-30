package deep_pills.controllers;

import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.services.interfaces.UserManagementService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/api/user")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserManagementService userManagementService;

    @DeleteMapping("/disable/{userId}")
    ResponseEntity<ResponseDTO<String>> disableUser(@PathVariable Long userId) throws Exception {
        userManagementService.disableUser(userId);
        return ResponseEntity.ok(new ResponseDTO<>(false, userId +" disabled successfully"));
    }

    @PostMapping("/enable/{userId}")
    public ResponseEntity<ResponseDTO<String>> enableUser(@PathVariable Long userId) throws Exception{
        userManagementService.enableUser(userId);
        return ResponseEntity.ok(new ResponseDTO<>(false, userId+" enabled successfully"));
    }
}
