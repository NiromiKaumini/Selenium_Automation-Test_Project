package com.library.automation.tests;

import com.library.automation.base.BaseTest;
import com.library.automation.pages.LibraryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddBookTest extends BaseTest {

    @Test
    public void addBookWithValidDetails_shouldDisplayBookInTable() {
        LibraryPage libraryPage = new LibraryPage(driver);

        libraryPage.addBook("B003", "Selenium Testing", "QA Team", "250");

        Assert.assertEquals(libraryPage.getMessageText(), "Book added successfully.");
        Assert.assertTrue(libraryPage.isBookDisplayed("B003"));
        Assert.assertEquals(libraryPage.getBookStatus("B003"), "AVAILABLE");
    }
}
