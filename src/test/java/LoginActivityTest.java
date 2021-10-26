import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.concurrent.TimeUnit.SECONDS;

public class LoginActivityTest {

    private final DesiredCapabilities caps = new DesiredCapabilities();
    private final boolean enableLocalServerCapabilities = false;
    private AndroidDriver<MobileElement> driver;
    private LoginPage loginPage;


    @BeforeSuite
    public void setUp() {
        caps.setCapability("platformName", "android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("noReset", true);
        // Local Appium Server
        if (enableLocalServerCapabilities) {
            caps.setCapability("deviceName", "emulator-5554");
            caps.setCapability("skipServerInstallation", false);
            caps.setCapability("appPackage", "com.rm.loginapp");
            caps.setCapability("appActivity", "com.rm.loginapp.ui.login.LoginActivity");
            caps.setCapability("app", "/Users/rajivmanivannan/Documents/Appium/LoginApp/app-debug.apk");
        } else {
            //Bitrise Config
            caps.setCapability("platformVersion", "9");
            caps.setCapability("deviceName", "Android Emulator");
            caps.setCapability("app", System.getenv("BITRISE_APK_PATH"));
        }
    }

    @BeforeTest
    public void setDriver() throws MalformedURLException {
        driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(15, SECONDS);
        loginPage = new LoginPage(driver);
    }


    @Test(description = "Verify that a user cannot login to the application with invalid credentials")
    public void testInvalidLogin() {
        loginPage.login("Bob", "123");
    }


    @Test(description = "Verify that a user can login to the application with valid credentials")
    public void testValidLogin() {
        loginPage.login("Bob", "Welcome@123");
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getSuccessLabelText(), "Login Success");
    }


    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit(); //Close application
        }
    }
}

