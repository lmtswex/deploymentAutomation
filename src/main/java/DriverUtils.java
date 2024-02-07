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

    public static void waitForElement(String type, String element) throws InterruptedException {
        int count = 0;
        int maxTries = 600;

        while (count < maxTries) {
            try {
                if (type.equals("xpath")) {
                    DriverUtils.driver.findElement(By.xpath(element));
                } else if (type.equals("id")) {
                    DriverUtils.driver.findElement(By.id(element));
                }
                return;
            } catch (Exception e) {
                count++;
                Thread.sleep(1000);
            }
        }
        throw new NoSuchElementException("Failed after ten minutes. Searching for " + type + ": " + element);
    }

}
