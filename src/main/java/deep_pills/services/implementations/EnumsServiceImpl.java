package deep_pills.services.implementations;

import deep_pills.model.enums.lists.*;
import deep_pills.model.enums.states.*;
import deep_pills.model.enums.types.*;
import deep_pills.services.interfaces.EnumsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnumsServiceImpl implements EnumsService {
    @Override
    public List<String> getEnumValues(String enumName) throws Exception {
        return switch (enumName) {
            case "Allergy" -> Arrays.stream(Allergy.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "BloodType" -> Arrays.stream(BloodType.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "City" -> Arrays.stream(City.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "Diagnosis" -> Arrays.stream(Diagnosis.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "EPS" -> Arrays.stream(EPS.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "Specialization" -> Arrays.stream(Specialization.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "Symptom" -> Arrays.stream(Symptom.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "AccountState" -> Arrays.stream(AccountState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "AppointmentState" -> Arrays.stream(AppointmentState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "ChargeState" -> Arrays.stream(ChargeState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "ClaimState" -> Arrays.stream(ClaimState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "EmailState" -> Arrays.stream(EmailState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "FreeDayStatus" -> Arrays.stream(FreeDayStatus.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "MembershipState" -> Arrays.stream(MembershipState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "PasswordRecoveryRequestState" -> Arrays.stream(PasswordRecoveryRequestState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "PaymentState" -> Arrays.stream(PaymentState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "PolicyState" -> Arrays.stream(PolicyState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "ScheduleState" -> Arrays.stream(ScheduleState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "TreatmentState" -> Arrays.stream(TreatmentState.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "ClaimType" -> Arrays.stream(ClaimType.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "EMailType" -> Arrays.stream(EMailType.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "MessageType" -> Arrays.stream(MessageType.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            case "ShiftType" -> Arrays.stream(ShiftType.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            default -> throw new IllegalArgumentException("Enum not found: " + enumName);
        };
    }
}
