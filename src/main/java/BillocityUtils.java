import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class BillocityUtils {
    public static void openBillocity() throws InterruptedException {
        DriverUtils.driver.get("http://autoivrmulti.pd.wexcp.com:8080/CallManager/autoivr/index.html#com.prenet.gwtapps.autoivr.client.HomeView");
        Thread.sleep(5000);
    }

    public void login() throws InterruptedException {
        waitForElement("id","userNameInput");
        DriverUtils.driver.findElement(By.id("userNameInput")).sendKeys("*username*");
        Thread.sleep(1000);
        DriverUtils.driver.findElement(By.id("pwInput")).sendKeys("*password*");
        Thread.sleep(1000);
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"loginButton\"]/table/tbody/tr/td[2]/div")).click();
        Thread.sleep(1000);
        waitForElement("xpath","//*[@id=\"submitExtButton\"]/table/tbody/tr/td[2]/div");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"submitExtButton\"]/table/tbody/tr/td[2]/div")).click();
        Thread.sleep(1000);
    }

    public void openDeploymentPage() throws InterruptedException {
        waitForElement("id","autoivrMenu.callFlowClass");
        DriverUtils.driver.findElement(By.id("autoivrMenu.callFlowClass")).click();
    }

    public void inputCallFlowName(String cfName) throws InterruptedException {
        // Add CallFlow name
        waitForElement("id","input-gwt-uid-7");
        Thread.sleep(3000);
        DriverUtils.driver.findElement(By.id("input-gwt-uid-7")).sendKeys(cfName);
        DriverUtils.driver.findElement(By.className("item")).click();
        waitForElement("xpath","//*[@id=\"tri-panel-gwt-uid-9\"]/table/tbody/tr/td[2]/div");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"tri-panel-gwt-uid-9\"]/table/tbody/tr/td[2]/div")).click();
    }

    public void startDeploy(String comitComment) throws InterruptedException {
        waitForElement("xpath","/html/body/table/tbody/tr[2]/td/div/div/div/table/tbody/tr[2]/td/div/div[1]/div[2]/div/table/tbody/tr[2]/td/table/tbody[1]/tr/td[8]/div/button");
        DriverUtils.driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/div/div/div/table/tbody/tr[2]/td/div/div[1]/div[2]/div/table/tbody/tr[2]/td/table/tbody[1]/tr/td[8]/div/button")).click();
        waitForElement("id","input-gwt-uid-11");
        DriverUtils.driver.findElement(By.id("input-gwt-uid-11")).sendKeys(DriverUtils.driver.findElement(By.id("cell_changelistIdColumn_0")).getText());
        DriverUtils.driver.findElement(By.id("input-gwt-uid-12")).sendKeys(comitComment);
        waitForElement("xpath","//*[@id=\"tri-panel-gwt-uid-13\"]/table/tbody/tr/td[2]/div");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"tri-panel-gwt-uid-13\"]/table/tbody/tr/td[2]/div")).click();


    }

    public void selectFileAndSave(String fileName) throws InterruptedException {
        waitForElement("xpath","//*[@id=\"glass\"]/table/tbody/tr[2]/td/form/table/tbody/tr[3]/td[2]/input");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"glass\"]/table/tbody/tr[2]/td/form/table/tbody/tr[3]/td[2]/input")).sendKeys(fileName);
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"glass\"]/table/tbody/tr[2]/td/form/table/tbody/tr[4]/td[2]/button")).click();
        Thread.sleep(3000);
        waitForElement("xpath","//*[@id=\"tri-panel-gwt-uid-15\"]/table/tbody/tr/td[2]/div");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"tri-panel-gwt-uid-15\"]/table/tbody/tr/td[2]/div")).click();
        Thread.sleep(3000);
    }

    public boolean validateChange(String changeToBeValidated) throws InterruptedException {
        waitForElement("xpath","//*[@id=\"cell_sourceCodeColumn_0\"]");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"cell_sourceCodeColumn_0\"]")).click();
        waitForElement("xpath","//*[@id=\"glass\"]/table/tbody/tr[3]/td/div");
        Thread.sleep(1000);
        return DriverUtils.driver.findElement(By.xpath("//*[@id=\"glass\"]/table/tbody/tr[3]/td/div")).getText().contains(changeToBeValidated.trim());
    }

    public void waitForElement(String type, String element) throws InterruptedException {
        int count = 0;
        int maxTries = 15;

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
        throw new NoSuchElementException("Failed after " + maxTries + " tries. Searching for " + type + ": " + element);
    }
}

