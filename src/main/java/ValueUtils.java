import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValueUtils {
    static String callFlowName = "";
    static String gitLink = "";
    static private JTextField[] textFields;

    public void fillAndValidateValues() {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
        if (callFlowName == null || gitLink == null) {
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
                "CallFlow name on DC:",
                "Git link:"
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
                DeploymentAutomation.CFName = textFields[0].getText();
                DeploymentAutomation.gitLink = textFields[1].getText();
                try {
                    DeploymentAutomation.getGitValuesAndCheckout(DeploymentAutomation.CFName,DeploymentAutomation.gitLink);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
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

