package deep_pills;

import deep_pills.services.interfaces.UserListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserListingTest {
    @Autowired UserListingService userListingService;
}
