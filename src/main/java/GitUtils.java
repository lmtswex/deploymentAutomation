import javax.swing.*;
import java.io.IOException;

public class GitUtils {

    public boolean checkout(String path, String branch) {
            try {
                ProcessBuilder pb = new ProcessBuilder("C:/Program Files/Git/bin/bash.exe", "-c", "cd " + path + " && git checkout " + branch);
                pb.inheritIO();
                Process p = pb.start();
                int exitCode = p.waitFor();
                return exitCode == 0;
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return false;
            }
    }
}
