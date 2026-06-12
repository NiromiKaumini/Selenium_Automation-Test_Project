package com.library.automation.tests;

import com.library.automation.base.BaseTest;
import com.library.automation.pages.LibraryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchBookTest extends BaseTest {

    @Test
    public void searchExistingBook_shouldShowMatchingBookOnly() {
        LibraryPage libraryPage = new LibraryPage(driver);

        libraryPage.searchBook("Clean Code");

        Assert.assertEquals(libraryPage.getMessageText(), "1 book(s) found.");
        Assert.assertEquals(libraryPage.getVisibleBookCount(), 1);
        Assert.assertTrue(libraryPage.isBookDisplayed("B001"));
    }
}
