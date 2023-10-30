package deep_pills.controllers;

import deep_pills.dto.controllers.ResponseDTO;
import deep_pills.services.interfaces.EnumsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deep_pills/api/enums")
public class EnumsController {
    private final EnumsService enumsService;
    @GetMapping("/get/{enumName}")
    public ResponseEntity<ResponseDTO<List<String>>> getEnum(@PathVariable String enumName) throws Exception{
        return ResponseEntity.ok(new ResponseDTO<>(false, enumsService.getEnumValues(enumName)));
    }
}
