import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel extends JFrame{

    JTextField nameField, ageField, aadhaarField;

    public AdminPanel(){

        setTitle("Admin Panel");
        setSize(400, 300);
        setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 100,25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 50, 150, 25);
        add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 90,100,25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(150, 90, 150,25);
        add(ageField);

        JLabel aadhaarLabel = new JLabel("Aadhaar:");
        aadhaarLabel.setBounds(50, 130,100,25);
        add(aadhaarLabel);

        aadhaarField=new JTextField();
        aadhaarField.setBounds(150, 130, 150, 25);
        add(aadhaarField);

        JButton submitBtn = new JButton("Register");
        submitBtn.setBounds(150, 180, 100, 30);
        add(submitBtn);

        submitBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(nameField.getText().isEmpty() || ageField.getText().isEmpty() || aadhaarField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null , "All fields required!");
                    return;
                }
                String json = String.format(
                    "{\"name\":\"%s\",\"age\":%d,\"aadhaarId\":\"%s\",\"biometricData\":\"face_data\",\"hasVoted\":false}",
                    nameField.getText(),
                    Integer.parseInt(ageField.getText()),
                    aadhaarField.getText(), 
                );
                System.out.println(json);
                String response = ApiService.registerVoter(json);

                JOptionPane.showMessageDialog(null, response);
            }
        });

        setVisible(true);
    }
}