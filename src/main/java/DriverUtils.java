import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by bgatlin on 12/6/2019.
 */
public class DriverUtils {

    public static WebDriver driver=null;

    public static WebDriver startdriver(){
            System.setProperty("webdriver.chrome.driver", "src/main/java/chromedriver.exe");
            driver=new ChromeDriver();
            return driver;
    }

    public static boolean elementExists(By elem, long timeout, int interval) throws InterruptedException {
        long timeoutTime = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < timeoutTime) {
            try {
                driver.findElement(elem);
                return true;
            }
            catch (NoSuchElementException e) {
                Thread.sleep(interval);
            }
        }
        return false;
    }

}
