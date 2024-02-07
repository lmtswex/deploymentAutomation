public class DeploymentAutomation {
    public static ValueUtils values = new ValueUtils();
    public static BillocityUtils billocity = new BillocityUtils();
    public static GitWebUtils webGit = new GitWebUtils();
    public static DriverUtils driver = new DriverUtils();
    public static String CFName = "";
    public static String gitLink = "";
    public static String commitComment = "";
    public static String branch = "";
    public static String filePath = "";
    static GitUtils git = new GitUtils();

    public static void main(String[] args) throws InterruptedException {
        values.fillAndValidateValues();
    }

    public static void getGitValuesAndCheckout(String callFlowName, String gitLink) throws InterruptedException {
        CFName = callFlowName;
        driver.startdriver();
        webGit.openGit(gitLink);
        commitComment = webGit.getCommitMsg();
        webGit.changeTab();
        branch = webGit.getBranch();
        filePath = "C:/autoIVR_repo/pd-v1-callflows/" + webGit.getFilePath();
        git.checkout("C:/autoIVR_repo/pd-v1-callflows/CallFlows/src/main/java/com.precash.autoivr.callmanager.callflows", branch);
        deploy();

    }

    public static void deploy() throws InterruptedException {

        billocity.openBillocity();
        billocity.login();
        billocity.openDeploymentPage();
        billocity.inputCallFlowName(CFName);
        billocity.startDeploy(commitComment);
        billocity.selectFileAndSave(filePath);
//        if (billocity.validateChange(values.change)) {
//            return true;
//        } else {
//            return false;
//        }
    }


}
