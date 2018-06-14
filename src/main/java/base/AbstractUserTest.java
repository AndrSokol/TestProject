package base;

import assertions.UserAssertions;
import controllers.UserController;
import org.testng.annotations.BeforeClass;

public abstract class AbstractUserTest {
    public UserController userController;
    public UserAssertions userAssertions;

   {
        userController = new UserController();
        userAssertions = new UserAssertions();
   }
}
