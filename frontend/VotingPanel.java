import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class VotingPanel extends JFrame {

    JTextField aadhaarField;
    JPanel candidatePanel;

    public VotingPanel() {
        setUndecorated(true);        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // 🌟 TOP PANEL
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(30, 144, 255));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));

        JLabel label = new JLabel("Enter Aadhaar:");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 16));

        aadhaarField = new JTextField(15);
        aadhaarField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton verifyBtn = new JButton("Verify");
        verifyBtn.setFont(new Font("Arial", Font.BOLD, 14));
        verifyBtn.setBackground(new Color(0, 102, 204));
        verifyBtn.setForeground(Color.WHITE);
        verifyBtn.setFocusPainted(false);

        topPanel.add(label);
        topPanel.add(aadhaarField);
        topPanel.add(verifyBtn);

        add(topPanel, BorderLayout.NORTH);

        // 🌟 CENTER PANEL
        candidatePanel = new JPanel();
        candidatePanel.setLayout(new BoxLayout(candidatePanel, BoxLayout.Y_AXIS));
        candidatePanel.setBackground(new Color(245, 245, 245));
        candidatePanel.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200));

        JScrollPane scrollPane = new JScrollPane(candidatePanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // 🔥 BUTTON ACTION
        verifyBtn.addActionListener(e -> {

            candidatePanel.removeAll();

            String aadhaar = aadhaarField.getText();
            String voterJson = ApiService.getVoter(aadhaar);

            if (voterJson == null || voterJson.equals("null")) {
                JOptionPane.showMessageDialog(this, " Voter not found!");
                return;
            }

            if (voterJson.contains("\"hasVoted\":true")) {
                JOptionPane.showMessageDialog(this, " Already voted!");
                return;
            }

            String voterId = voterJson.split("\"id\":\"")[1].split("\"")[0];

            String candidatesJson = ApiService.getCandidates();
            candidatesJson = candidatesJson.replace("[", "").replace("]", "");

            String[] candidates = candidatesJson.split("\\},\\{");

            for (String c : candidates) {

                String name = extractValue(c, "name");
                String party = extractValue(c, "party");
                String id = extractValue(c, "id");

                // 🌟 CARD
                JPanel card = new JPanel(new BorderLayout());
                card.setMaximumSize(new Dimension(500, 70));
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200)),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                card.setBackground(Color.WHITE);

                JLabel info = new JLabel(name + " (" + party + ")");
                info.setFont(new Font("Arial", Font.BOLD, 16));

                JButton voteBtn = new JButton("Vote");
                voteBtn.setFont(new Font("Arial", Font.BOLD, 14));
                voteBtn.setBackground(new Color(34, 139, 34));
                voteBtn.setForeground(Color.WHITE);
                voteBtn.setFocusPainted(false);

                voteBtn.addActionListener(ev -> {
                    String res = ApiService.vote(voterId, id);
                    JOptionPane.showMessageDialog(this, res);

                    aadhaarField.setText("");
                    candidatePanel.removeAll();
                    candidatePanel.revalidate();
                    candidatePanel.repaint();
                });

                card.add(info, BorderLayout.CENTER);
                card.add(voteBtn, BorderLayout.EAST);

                candidatePanel.add(card);
                candidatePanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }

            candidatePanel.revalidate();
            candidatePanel.repaint();
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private String extractValue(String json, String key) {
        try {
            return json.split("\"" + key + "\":\"")[1].split("\"")[0];
        } catch (Exception e) {
            return "N/A";
        }
    }
}