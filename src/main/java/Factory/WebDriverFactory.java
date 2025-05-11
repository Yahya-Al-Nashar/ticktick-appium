package Factory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URI;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class WebDriverFactory {

    private static AndroidDriver driver;
    private static AppiumDriverLocalService service;

    public static AndroidDriver getDriver() {
        if(driver ==null) {
//            service = AppiumDriverLocalService.buildDefaultService();
//            service.start();

            service = new AppiumServiceBuilder().
                    withAppiumJS(new File("C:\\Users\\yahia\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js")).
                    withIPAddress("127.0.0.1").
                    usingPort(4723).
                    build();
            service.start();

            UiAutomator2Options options = new UiAutomator2Options()
                    .setUdid("RFCXA13PB6Z")
                    .setAutoGrantPermissions(true)
                    .setNoReset(true)
                    .setPlatformName("Android")
                    .setAppWaitForLaunch(true);

            try {
                driver = new AndroidDriver(
                        new URI("http://127.0.0.1:4723").toURL(), options);

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                // Thread.sleep(5000);
                return driver;

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            return driver;
        }
        return null;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
        service.stop();
    }
}
