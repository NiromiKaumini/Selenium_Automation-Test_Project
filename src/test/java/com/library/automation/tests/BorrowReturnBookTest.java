package com.library.automation.tests;

import com.library.automation.base.BaseTest;
import com.library.automation.pages.LibraryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BorrowReturnBookTest extends BaseTest {

    @Test
    public void borrowAvailableBook_shouldChangeStatusToBorrowed() {
        LibraryPage libraryPage = new LibraryPage(driver);

        libraryPage.borrowBook("B001", "Niromi");

        Assert.assertEquals(libraryPage.getMessageText(), "Book borrowed successfully.");
        Assert.assertEquals(libraryPage.getBookStatus("B001"), "BORROWED");
        Assert.assertEquals(libraryPage.getBorrowedBy("B001"), "Niromi");
        Assert.assertEquals(libraryPage.getBorrowHistoryCount(), 1);
    }

    @Test
    public void returnBorrowedBook_shouldChangeStatusToAvailable() {
        LibraryPage libraryPage = new LibraryPage(driver);

        libraryPage.borrowBook("B002", "Kasun");
        libraryPage.returnBook("B002");

        Assert.assertEquals(libraryPage.getMessageText(), "Book returned successfully.");
        Assert.assertEquals(libraryPage.getBookStatus("B002"), "AVAILABLE");
        Assert.assertEquals(libraryPage.getBorrowedBy("B002"), "");
        Assert.assertEquals(libraryPage.getBorrowHistoryCount(), 2);
    }
}
