package tests;

import helpers.WithLogin;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

@DisplayName("Demoqa tests")
public class DemoqaTests extends TestBase {
    final ApiSteps apiSteps = new ApiSteps();
    final ProfilePage profilePage = new ProfilePage();

    @Test
    @DisplayName("Delete book from book list")
    @Owner("Ananenkov Vladislav")
    @WithLogin
    void deleteBookTest() {
        apiSteps.addListOfBook();
        profilePage.openPage()
                .deleteBook()
                .confirmDeletion()
                .verifyDeletion();
    }
}
