package MyFrameWork;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.time.Duration;
import java.util.Set;


public class SeleniumFrameWork {

    private WebDriver driver;
    private WebDriverWait explicitWait;
    private final int DEFAULT_TIMEOUT = 10;

    public SeleniumFrameWork(WebDriver driver) {
        this.driver = driver;
        this.explicitWait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
    // Click element using explicit wait
    public void click(WebElement element) {
        explicitWait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        System.out.println("Clicked element successfully");
    }

    // SendKeys method
    public void sendKeys(WebElement element, String text) {
        explicitWait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        System.out.println("Typed: " + text);
    }
    // Browser implicitly wait
    public void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        System.out.println("Set Implicit Wait to " + seconds + " seconds.");
    }

    // Explicit wait for element presence
    public void explicitWait(WebElement element, int timeoutSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOf(element));
        System.out.println("Explicit wait for presence of " + element);
    }

    // Fluent wait for element visibility with customizable timeout and polling interval
    public void fluentWait(By locator, int timeoutSeconds, int pollingMillis, String timeoutMessage) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .withMessage(timeoutMessage)
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        System.out.println("Fluent wait found element " + locator);
    }


    // Navigate to URL
    public void navigateToURL(String url) {
        driver.get(url);
        System.out.println("Navigated to URL: " + url);
    }

    // Get page title
    public String getPageTitle() {
        String title = driver.getTitle();
        System.out.println("Page title is '" + title + "'");
        return title;
    }

    // Get current URL
    public String getCurrentURL() {
        String currentURL = driver.getCurrentUrl();
        System.out.println("Current URL is '" + currentURL + "'");
        return currentURL;
    }

    // Right click (context click) on element
    public void rightClick(WebElement webElement) {
        explicitWait.until(ExpectedConditions.visibilityOf(webElement));
        Actions actions = new Actions(driver);
        actions.contextClick(webElement).perform();
        System.out.println("Right-clicked on element " + webElement);
    }

    // Get text from element
    public String getText(WebElement element) {
        String text = explicitWait.until(ExpectedConditions.visibilityOf(element)).getText();
        System.out.println("Got text from element " + element + ": " + text);
        return text;
    }

    // Dropdown handling by visible text
    public void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
        System.out.println("Selected dropdown value by visible text: " + visibleText);
    }

    // Dropdown handling by value
    public void selectDropdownByValue(WebElement element, String value) {
        WebElement dropdown = explicitWait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(dropdown);
        select.selectByValue(value);
        System.out.println("Selected dropdown value by value: " + value);
    }

    // Dropdown handling by index
    public void selectDropdownByIndex(WebElement element, int index) {
        WebElement dropdown = explicitWait.until(ExpectedConditions.visibilityOf(element));
        Select select = new Select(dropdown);
        select.selectByIndex(index);
        System.out.println("Selected dropdown by index: " + index);
    }

    // Drag and drop element
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
        WebElement target = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(targetLocator));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
        System.out.println("Dragged element " + sourceLocator + " and dropped on " + targetLocator);
    }

    // Checkbox handling: check checkbox
    public void checkCheckbox(WebElement element) {
        WebElement checkbox = explicitWait.until(ExpectedConditions.elementToBeClickable(element));
        if (!checkbox.isSelected()) {
            checkbox.click();
            System.out.println("Checked the checkbox " + element);
        } else {
            System.out.println("Checkbox already checked " + element);
        }
    }

    // Checkbox handling: uncheck checkbox
    public void uncheckCheckbox(By locator) {
        WebElement checkbox = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (checkbox.isSelected()) {
            checkbox.click();
            System.out.println("Unchecked the checkbox " + locator);
        } else {
            System.out.println("Checkbox already unchecked " + locator);
        }
    }

    // Radio button handling: select radio button
    public void selectRadioButton(By locator) {
        WebElement radioButton = explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
        if (!radioButton.isSelected()) {
            radioButton.click();
            System.out.println("Selected radio button " + locator);
        } else {
            System.out.println("Radio button already selected " + locator);
        }
    }

    // Window handle: switch to window by title
    public void switchToWindowByTitle(String windowTitle) {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(windowTitle)) {
                System.out.println("Switched to window with title: " + windowTitle);
                return;
            }
        }

        driver.switchTo().window(currentWindow);
        System.out.println("Window with title '" + windowTitle + "' not found. Stayed in original window.");
    }

    // Window handle: switch to window by handle
    public void switchToWindowByHandle(String windowHandle) {
        Set<String> allWindows = driver.getWindowHandles();
        if (allWindows.contains(windowHandle)) {
            driver.switchTo().window(windowHandle);
            System.out.println("Switched to window handle: " + windowHandle);
        } else {
            System.out.println("Window handle " + windowHandle + " does not exist. No switch performed.");
        }
    }


    // Close current window
    public void closeCurrentWindow() {
        driver.close();
        System.out.println("Closed current window.");
    }

    // Navigate back
    public void navigateBack() {
        driver.navigate().back();
        System.out.println("Navigated back.");
    }

    // Navigate forward
    public void navigateForward() {
        driver.navigate().forward();
        System.out.println("Navigated forward.");
    }

    // Refresh the page
    public void refreshPage() {
        driver.navigate().refresh();
        System.out.println("Page refreshed.");
    }

    // Scroll to element using JavaScript
    public void scrollToElement(WebElement element) {
        explicitWait.until(ExpectedConditions.visibilityOf(element));
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).perform();
        System.out.println("Scrolled to element " + element + " using Actions.scrollToElement().");
    }

    // Handle alert: accept alert
    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        System.out.println("Alert accepted.");
    }


    // Handle alert: dismiss alert
    public void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        System.out.println("Alert dismissed.");
    }

    // Handle alert: get alert text
    public String getAlertText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        System.out.println("Alert text: " + text);
        return text;
    }

    public void sendTextToAlert(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
        System.out.println("Sent text to alert and accepted it: " + text);
    }

    // Upload file
    public void uploadFile(WebElement fileInput, String filePath) {
        fileInput.sendKeys(filePath);
        System.out.println("File uploaded successfully: " + filePath);
    }

    //Is element visible
    public boolean isElementVisible(WebElement element) {
        try {
            return explicitWait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
    //Hover over element
    public void hoverOverElement(WebElement element) {
        explicitWait.until(ExpectedConditions.visibilityOf(element));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        System.out.println("Successfully hovered over the element");
    }

    // Close the browser
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser Closed.");
        }
    }
}
