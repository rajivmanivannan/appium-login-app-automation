import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LoginActivityTest {

    private AppiumDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "9");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("app", System.getenv("BITRISE_APK_PATH"));
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        webDriverWait = new WebDriverWait(driver, 15);
    }


    @Test(description = "Verify that a user cannot login to the application with invalid credentials")
    public void testInvalidLogin() {
        login("Bob","123");
        //Assert.assertEquals(loginPage.getAttemptsCounterLabelText(), "Number of attempts remaining: 4");
    }


    @Test(description = "Verify that a user can login to the application with valid credentials")
    public void testValidLogin() {
        login("Bob", "Welcome@123");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));
        String toastMessage = driver.findElement(By.xpath("/hierarchy/android.widget.Toast")).getText();
        Assert.assertEquals(toastMessage, "Welcome!" + "Bob");
    }


    public void login(String username, String password) {
        By usernameEditText = By.id("username");
        driver.findElement(usernameEditText).sendKeys(username);
        By passwordEditText = By.id("password");
        driver.findElement(passwordEditText).sendKeys(password);
        By signInButton = By.id("login");
        driver.findElement(signInButton).click();
    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit(); //Close application
        }
    }
}

