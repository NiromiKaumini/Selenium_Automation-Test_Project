package com.library.automation.tests;

import com.library.automation.base.BaseTest;
import com.library.automation.pages.LibraryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidationTest extends BaseTest {

    @Test
    public void addBookWithoutRequiredDetails_shouldShowValidationError() {
        LibraryPage libraryPage = new LibraryPage(driver);

        libraryPage.addBook("", "", "", "");

        Assert.assertEquals(libraryPage.getMessageText(), "Please fill all book details.");
    }

    @Test
    public void borrowUnknownBook_shouldShowBookNotFoundError() {
        LibraryPage libraryPage = new LibraryPage(driver);

        libraryPage.borrowBook("B999", "Niromi");

        Assert.assertEquals(libraryPage.getMessageText(), "Book not found.");
    }

    @Test
    public void returnAvailableBook_shouldShowAlreadyAvailableError() {
        LibraryPage libraryPage = new LibraryPage(driver);

        libraryPage.returnBook("B001");

        Assert.assertEquals(libraryPage.getMessageText(), "Book is already available.");
    }
}
