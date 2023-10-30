package deep_pills.services.interfaces;

import jakarta.validation.constraints.NotNull;

public interface UserManagementService {
    void disableUser(@NotNull Long userId) throws Exception;
    void enableUser(@NotNull Long userId) throws Exception;
}





