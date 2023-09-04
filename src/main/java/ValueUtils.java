import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValueUtils {
    static String path = "C:/autoIVR_repo/pd.v1.callFlows/CallFlows/src/main/java/com.precash.autoivr.callmanager.callflows";
    static String branch = "";
    static String callFlowName = "";
    static String commitComment = "";
    static String filePath = "";
    static String change = "";
    static private JTextField[] textFields;
    static GitUtils git = new GitUtils();

    public void fillAndValidateValues() {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
        if (branch == null || callFlowName == null || commitComment == null || filePath == null || change == null) {
            JOptionPane.showMessageDialog(null, "You need to fill all the fields.");
            fillAndValidateValues();
        }
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Deployment Automation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = createInputPanel();

        frame.add(inputPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        String[] labels = {
                "Branch:",
                "CallFlow name on DC:",
                "Commit text:",
                "What to check:",
                "File name (without .java):"
        };

        textFields = new JTextField[labels.length]; // Inicializar o array de campos de texto

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            textFields[i] = new JTextField(20); // Armazenar os campos de texto no array

            constraints.gridx = 0;
            constraints.gridy = i;
            panel.add(label, constraints);

            constraints.gridx = 1;
            panel.add(textFields[i], constraints);
        }

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        constraints.gridx = 0;
        constraints.gridy = labels.length;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(okButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = labels.length;
        constraints.gridwidth = 2;
        panel.add(cancelButton, constraints);

        // Adicionar ação ao botão "OK"
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                branch = textFields[0].getText();
                callFlowName = textFields[1].getText();
                commitComment = textFields[2].getText();
                change = textFields[3].getText();
                filePath = path + "/" + textFields[4].getText() + ".java";

                if (git.checkout(path, branch)) {
                    JOptionPane.showMessageDialog(null, "Checkout Successful.");
                    try {
                        if(DeploymentAutomation.deploy()){
                            JOptionPane.showMessageDialog(null, "Deploy done!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Deploy failed.");
                        }
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        return panel;
    }
}

