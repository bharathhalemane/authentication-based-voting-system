import javax.swing.*;

public class VotingPanel extends JFrame{
    public VotingPanel(){
        setTitle("Voting Panel");
        setSize(400, 300);
        setLayout(null);

        JLabel label = new JLabel("Voting Coming Soon....");
        label.setBounds(100, 100, 200, 30);
        add(label);

        setVisible(true);
        
    }
}