import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class GitWebUtils {
    public static void openGit(String path) throws InterruptedException {
        DriverUtils.driver.get("https://github.com/login");
        DriverUtils.waitForElement("xpath","/html/body/div[1]/div[6]/div/div/div/div/div");
        DriverUtils.driver.get(path);
        DriverUtils.waitForElement("xpath","/html/body/div[1]/div[5]/main/div/div[2]/form/button");
        DriverUtils.driver.findElement(By.xpath("/html/body/div[1]/div[5]/main/div/div[2]/form/button")).click();
    }

    public String getCommitMsg() throws InterruptedException {
        DriverUtils.waitForElement("xpath","/html/body/div[1]/div[6]/div/main/turbo-frame/div/div/div[2]/div[1]/div[1]/div/h1/bdi");
        return DriverUtils.driver.findElement(By.xpath("/html/body/div[1]/div[6]/div/main/turbo-frame/div/div/div[2]/div[1]/div[1]/div/h1/bdi")).getText();
    }
    public String getBranch() throws InterruptedException {
        DriverUtils.waitForElement("xpath","/html/body/div[1]/div[6]/div/main/turbo-frame/div/div[2]/div[2]/diff-file-filter/div[1]/div[3]/div[2]/span[3]/a/span");
        return DriverUtils.driver.findElement(By.xpath("/html/body/div[1]/div[6]/div/main/turbo-frame/div/div[2]/div[2]/diff-file-filter/div[1]/div[3]/div[2]/span[3]/a/span")).getText();
    }
    public String getFilePath() throws InterruptedException {
        DriverUtils.waitForElement("xpath","/html/body/div[1]/div[6]/div/main/turbo-frame/div/div[2]/div[2]/diff-file-filter/diff-layout/div[3]/div[2]/div/div[2]/copilot-diff-entry[1]/div/div[1]/div[1]/span[3]/a");
        return DriverUtils.driver.findElement(By.xpath("/html/body/div[1]/div[6]/div/main/turbo-frame/div/div[2]/div[2]/diff-file-filter/diff-layout/div[3]/div[2]/div/div[2]/copilot-diff-entry[1]/div/div[1]/div[1]/span[3]/a")).getText();
    }

    public void changeTab() throws InterruptedException {
        DriverUtils.waitForElement("xpath","/html/body/div[1]/div[6]/div/main/turbo-frame/div/div/div[2]/div[2]/nav/a[4]");
        DriverUtils.driver.findElement(By.xpath("/html/body/div[1]/div[6]/div/main/turbo-frame/div/div/div[2]/div[2]/nav/a[4]")).click();
    }

    public void inputCallFlowName(String cfName) throws InterruptedException {
        // Add CallFlow name
        DriverUtils.waitForElement("id","input-gwt-uid-7");
        Thread.sleep(3000);
        DriverUtils.driver.findElement(By.id("input-gwt-uid-7")).sendKeys(cfName);
        DriverUtils.driver.findElement(By.className("item")).click();
        DriverUtils.waitForElement("xpath","//*[@id=\"tri-panel-gwt-uid-9\"]/table/tbody/tr/td[2]/div");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"tri-panel-gwt-uid-9\"]/table/tbody/tr/td[2]/div")).click();
    }

    public void startDeploy(String comitComment) throws InterruptedException {
        DriverUtils.waitForElement("xpath","/html/body/table/tbody/tr[2]/td/div/div/div/table/tbody/tr[2]/td/div/div[1]/div[2]/div/table/tbody/tr[2]/td/table/tbody[1]/tr/td[8]/div/button");
        DriverUtils.driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td/div/div/div/table/tbody/tr[2]/td/div/div[1]/div[2]/div/table/tbody/tr[2]/td/table/tbody[1]/tr/td[8]/div/button")).click();
        DriverUtils.waitForElement("id","input-gwt-uid-11");
        DriverUtils.driver.findElement(By.id("input-gwt-uid-11")).sendKeys(DriverUtils.driver.findElement(By.id("cell_changelistIdColumn_0")).getText());
        DriverUtils.driver.findElement(By.id("input-gwt-uid-12")).sendKeys(comitComment);
        DriverUtils.waitForElement("xpath","//*[@id=\"tri-panel-gwt-uid-13\"]/table/tbody/tr/td[2]/div");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"tri-panel-gwt-uid-13\"]/table/tbody/tr/td[2]/div")).click();


    }

    public void selectFileAndSave(String fileName) throws InterruptedException {
        DriverUtils.waitForElement("xpath","//*[@id=\"glass\"]/table/tbody/tr[2]/td/form/table/tbody/tr[3]/td[2]/input");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"glass\"]/table/tbody/tr[2]/td/form/table/tbody/tr[3]/td[2]/input")).sendKeys(fileName);
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"glass\"]/table/tbody/tr[2]/td/form/table/tbody/tr[4]/td[2]/button")).click();
        Thread.sleep(3000);
        DriverUtils.waitForElement("xpath","//*[@id=\"tri-panel-gwt-uid-15\"]/table/tbody/tr/td[2]/div");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"tri-panel-gwt-uid-15\"]/table/tbody/tr/td[2]/div")).click();
        Thread.sleep(3000);
    }

    public boolean validateChange(String changeToBeValidated) throws InterruptedException {
        DriverUtils.waitForElement("xpath","//*[@id=\"cell_sourceCodeColumn_0\"]");
        DriverUtils.driver.findElement(By.xpath("//*[@id=\"cell_sourceCodeColumn_0\"]")).click();
        DriverUtils.waitForElement("xpath","//*[@id=\"glass\"]/table/tbody/tr[3]/td/div");
        Thread.sleep(1000);
        return DriverUtils.driver.findElement(By.xpath("//*[@id=\"glass\"]/table/tbody/tr[3]/td/div")).getText().contains(changeToBeValidated.trim());
    }
    
}

