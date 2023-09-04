import javax.swing.*;

public class DeploymentAutomation {
    public static ValueUtils values = new ValueUtils();
    public static BillocityUtils billocity = new BillocityUtils();
    public static DriverUtils driver = new DriverUtils();

    public static void main(String[] args) throws InterruptedException {
        values.fillAndValidateValues();
    }

    public static boolean deploy() throws InterruptedException {
        // After checking out without problems, start the chromedriver
        driver.startdriver();
        billocity.openBillocity();
        billocity.login();
        billocity.openDeploymentPage();
        billocity.inputCallFlowName(values.callFlowName);
        billocity.startDeploy(values.commitComment);
        billocity.selectFileAndSave(values.filePath);
        if (billocity.validateChange(values.change)) {
            return true;
        } else {
            return false;
        }
    }
}
