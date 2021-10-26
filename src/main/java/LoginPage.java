import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage {


    private final AndroidDriver<MobileElement> driver;

    public LoginPage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(By.id("username")).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(By.id("login")).click();
    }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
    }

}
