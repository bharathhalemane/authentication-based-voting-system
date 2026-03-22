import javax.swing.*;
import java.awt.event.ActionEvent;

public class VotingPanel extends JFrame{
    JTextField aadhaarField;
    JTextArea output;

    public VotingPanel(){

        setTitle("Voting System");
        setSize(500, 400);
        setLayout(null);

        JLabel label = new JLabel("Enter Aadhaar:");
        label.setBounds(50, 50, 150, 25);
        add(label);

        aadhaarField = new JTextField();
        aadhaarField.setBounds(200, 50, 200, 25);
        add(aadhaarField);

        JButton verifyBtn = new JButton("verify");
        verifyBtn.setBounds(200, 100, 100, 30);
        add(verifyBtn);

        output = new JTextArea();
        output.setBounds(50, 150, 400, 150);
        add(output);

        verifyBtn.addActionListener((ActionEvent e)-> {
            String aadhaar = aadhaarField.getText();
            String voterJson = ApiService.getVoter(aadhaar);

            if(voterJson == null || voterJson.equals("null")) {
                JOptionPane.showMessageDialog(null, "Voter not found!");
                return;
            }

            if(voterJson.contains("\"hasVoted\":true")) {
                JOptionPane.showMessageDialog(null, "Already voted!");
                return;
            }

            String candidates = ApiService.getCandidates();
            output.setText(candidates);

            String voterId = voterJson.split("\"id\":\"")[1].split("\"")[0];
            String  candidateId = JOptionPane.showInputDialog("Enter Candidate ID:");
            String response = ApiService.vote(voterId, candidateId);
            JOptionPane.showMessageDialog(null, response);

        });
        setVisible(true);
    }
}