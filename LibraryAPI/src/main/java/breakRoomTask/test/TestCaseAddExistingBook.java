package breakRoomTask.test;

import org.testng.annotations.Test;

public class TestCaseAddExistingBook extends AllMethodsForLibraryAPI{

    @Test
    public void addExistingBookAndGetErrorMessage() {
        validateAddDuplicateBookAndGetMessage();
    }
}
