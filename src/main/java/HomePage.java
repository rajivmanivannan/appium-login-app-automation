import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HomePage {
    private final AndroidDriver<MobileElement> driver;

    public HomePage(AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public String getSuccessLabelText() {
        return driver.findElement(By.id("success_msg")).getText();
    }
}
