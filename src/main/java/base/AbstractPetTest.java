package base;

import assertions.PetAssertions;
import controllers.PetController;

public abstract class AbstractPetTest {
    public PetController petController;
    public PetAssertions petAssertions;

    {
        petController = new PetController();
        petAssertions = new PetAssertions();
    }
}
