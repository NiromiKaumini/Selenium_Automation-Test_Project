package com.library.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LibraryPage {
    private final WebDriver driver;

    private final By bookIdInput = By.id("bookId");
    private final By titleInput = By.id("title");
    private final By authorInput = By.id("author");
    private final By pagesInput = By.id("pages");
    private final By addBookButton = By.id("addBookBtn");

    private final By searchInput = By.id("searchInput");
    private final By searchButton = By.id("searchBtn");

    private final By borrowBookIdInput = By.id("borrowBookId");
    private final By memberNameInput = By.id("memberName");
    private final By borrowButton = By.id("borrowBtn");

    private final By returnBookIdInput = By.id("returnBookId");
    private final By returnButton = By.id("returnBtn");

    private final By message = By.id("message");
    private final By bookRows = By.cssSelector("#bookTableBody tr");
    private final By historyRows = By.cssSelector("#historyTableBody tr");

    public LibraryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addBook(String bookId, String title, String author, String pages) {
        driver.findElement(bookIdInput).sendKeys(bookId);
        driver.findElement(titleInput).sendKeys(title);
        driver.findElement(authorInput).sendKeys(author);
        driver.findElement(pagesInput).sendKeys(pages);
        driver.findElement(addBookButton).click();
    }

    public void searchBook(String titleKeyword) {
        driver.findElement(searchInput).clear();
        driver.findElement(searchInput).sendKeys(titleKeyword);
        driver.findElement(searchButton).click();
    }

    public void borrowBook(String bookId, String memberName) {
        driver.findElement(borrowBookIdInput).clear();
        driver.findElement(borrowBookIdInput).sendKeys(bookId);

        driver.findElement(memberNameInput).clear();
        driver.findElement(memberNameInput).sendKeys(memberName);

        driver.findElement(borrowButton).click();
    }

    public void returnBook(String bookId) {
        driver.findElement(returnBookIdInput).clear();
        driver.findElement(returnBookIdInput).sendKeys(bookId);
        driver.findElement(returnButton).click();
    }

    public String getMessageText() {
        return driver.findElement(message).getText();
    }

    public int getVisibleBookCount() {
        return driver.findElements(bookRows).size();
    }

    public int getBorrowHistoryCount() {
        return driver.findElements(historyRows).size();
    }

    public boolean isBookDisplayed(String bookId) {
        return !driver.findElements(By.cssSelector("#bookTableBody tr[data-book-id='" + bookId + "']")).isEmpty();
    }

    public String getBookStatus(String bookId) {
        WebElement row = getBookRow(bookId);
        List<WebElement> cells = row.findElements(By.tagName("td"));
        return cells.get(4).getText();
    }

    public String getBorrowedBy(String bookId) {
        WebElement row = getBookRow(bookId);
        List<WebElement> cells = row.findElements(By.tagName("td"));
        return cells.get(5).getText();
    }

    private WebElement getBookRow(String bookId) {
        return driver.findElement(By.cssSelector("#bookTableBody tr[data-book-id='" + bookId + "']"));
    }
}
