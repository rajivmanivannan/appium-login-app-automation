import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class HomePage {
    private final AppiumDriver<MobileElement> driver;

    public HomePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public String getSuccessLabelText() {
        return driver.findElement(By.id("success_msg")).getText();
    }
}
