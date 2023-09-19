package deep_pills.dto;

import deep_pills.model.entities.accounts.Account;
import deep_pills.model.enums.Email_Status;

public record EMailDTO() {
    private static Account origin;
    private static Account destination;
    private static String message;
    private static String topic;
    private static Email_Status Status;
}
