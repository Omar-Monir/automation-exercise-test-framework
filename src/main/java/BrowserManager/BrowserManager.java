package BrowserManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BrowserManager {

    public static WebDriver driver;
    public static Properties properties;

    public static void loadFromPropertiesFile() throws IOException {

        properties =new Properties();
        File propFile = new File(System.getProperty("user.dir")
                + File.separator + "src"
                + File.separator + "main"
                + File.separator + "java"
                + File.separator + "BrowserManager"
                + File.separator + "config.properties");
        FileInputStream file =new FileInputStream(propFile);
        properties.load(file);

    }

    public static WebDriver openBrowserAndURL(String browserName){

        switch (browserName) {
            case "chrome" -> driver = new ChromeDriver();
            case "edge" -> driver = new EdgeDriver();
            case "fire fox" -> driver = new FirefoxDriver();
            case "safari" -> driver = new SafariDriver();
            case "brave" -> {
                System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("autofill.profile_enabled", false);
                prefs.put("autofill.credit_card_enabled", false);
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--disable-notifications");
                options.addArguments("--disable-popup-blocking");
                options.addArguments("--start-maximized");
                driver=new ChromeDriver(options);

            }

        }

        driver.manage().window().maximize();
        driver.navigate().to(properties.getProperty("URL"));
        return driver;
    }

}


