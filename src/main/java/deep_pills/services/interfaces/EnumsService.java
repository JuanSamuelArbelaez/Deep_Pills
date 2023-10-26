package deep_pills.services.interfaces;

import java.util.List;

public interface EnumsService {

    List<String> getEnumValues(String enumName) throws Exception;
}
