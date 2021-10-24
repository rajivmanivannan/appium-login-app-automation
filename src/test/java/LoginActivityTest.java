import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
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

    private final By usernameEditText = By.id("com.rm.loginapp:id/username");
    private final By passwordEditText = By.id("com.rm.loginapp:id/password");
    private final By signInButton = By.id("com.rm.loginapp:id/login");

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


    @Test(description = "Verify that a user can login to the application with valid credentials")
    public void testValidLogin() {
        login("Bob", "Welcome@123");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));
        String toastMessage = driver.findElement(By.xpath("/hierarchy/android.widget.Toast")).getText();
        Assert.assertEquals(toastMessage, "Welcome!" + "Bob");
    }

    public void login(String username, String password) {
        driver.findElement(usernameEditText).sendKeys(username);
        driver.findElement(passwordEditText).sendKeys(password);
        driver.findElement(signInButton).click();
    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit(); //Close application
        }
    }
}

