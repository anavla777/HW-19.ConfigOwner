package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    private static final SelenideElement
            deleteButton = $("#delete-record-undefined"),
            closeModalButton = $("#closeSmallModal-ok"),
            listArea = $(".profile-wrapper"),
            bookRow = $(".rt-tr -odd"),
            linkToBook = $(".see-book-Git Pocket Guide");

    @Step("Navigate to /profile")
    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    @Step("Delete book from the list")
    public ProfilePage deleteBook() {
        deleteButton.scrollIntoView(true).click();
        return this;
    }

    @Step("Confirm deletion")
    public ProfilePage confirmDeletion() {
        closeModalButton.click();
        return this;
    }

    @Step("Check that deleted books are not present in the list")
    public void verifyDeletion() {
        bookRow.shouldNot(exist);
        linkToBook.shouldNot(exist);
        listArea.shouldHave(text("No rows found"));
    }
}
