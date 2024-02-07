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
        String branch = "";
        Thread.sleep(3000);
        if(DriverUtils.driver.findElement(By.xpath("//*[@id=\"partial-discussion-header\"]/div[3]/div[2]/span[3]/a/span")).isDisplayed()){
            branch = DriverUtils.driver.findElement(By.xpath("//*[@id=\"partial-discussion-header\"]/div[3]/div[2]/span[3]/a/span")).getText();
        }else if(DriverUtils.driver.findElement(By.xpath("//*[@id=\"partial-discussion-header\"]/div[3]/div[2]/span[4]/a/span")).isDisplayed()){
            branch = DriverUtils.driver.findElement(By.xpath("//*[@id=\"partial-discussion-header\"]/div[3]/div[2]/span[4]/a/span")).getText();
        }
        return branch;
    }
    public String getFilePath() throws InterruptedException {
        DriverUtils.waitForElement("xpath","/html/body/div[1]/div[6]/div/main/turbo-frame/div/div[2]/div[2]/diff-file-filter/diff-layout/div[3]/div[2]/div/div[2]/copilot-diff-entry[1]/div/div[1]/div[1]/span[3]/a");
        return DriverUtils.driver.findElement(By.xpath("/html/body/div[1]/div[6]/div/main/turbo-frame/div/div[2]/div[2]/diff-file-filter/diff-layout/div[3]/div[2]/div/div[2]/copilot-diff-entry[1]/div/div[1]/div[1]/span[3]/a")).getAttribute("title");
    }

    public void changeTab() throws InterruptedException {
        DriverUtils.waitForElement("xpath","/html/body/div[1]/div[6]/div/main/turbo-frame/div/div/div[2]/div[2]/nav/a[4]");
        DriverUtils.driver.findElement(By.xpath("/html/body/div[1]/div[6]/div/main/turbo-frame/div/div/div[2]/div[2]/nav/a[4]")).click();
    }
}

