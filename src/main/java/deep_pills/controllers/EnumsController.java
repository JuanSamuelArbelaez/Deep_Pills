package deep_pills.controllers;

import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.services.interfaces.EnumsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/api/enums")
public class EnumsController {
    private final EnumsService enumsService;
    @GetMapping("/get/{enumName}")
    public ResponseEntity<ResponseDTO<String>> getEnum(@PathVariable String enumName) throws Exception{
        enumsService.getEnumValues(enumName);
        return ResponseEntity.ok(new ResponseDTO<>(false, "Enum recovery successful"));
    }
}
