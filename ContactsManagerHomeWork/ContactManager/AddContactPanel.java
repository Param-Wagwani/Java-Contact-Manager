package ContactManager;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import ContactManager.RegexWork;

class AddContactPanel extends JPanel {
    ApplicationFrame ref;

    JTextField nameField;
    JTextField phoneField;
    JTextField emailField;
    JComboBox<String> genderComboBox;
    JTextArea addressArea;
    JTextField dobField;

    AddContactPanel(ApplicationFrame ref) {
        this.ref = ref;
        setLayout(new GridLayout(8, 2));

        JLabel nameLabel = new JLabel("Name:", JLabel.RIGHT);
        nameField = new JTextField(15);
        JLabel phoneLabel = new JLabel("Phone:", JLabel.RIGHT);
        phoneField = new JTextField(15);
        JLabel emailLabel = new JLabel("Email:", JLabel.RIGHT);
        emailField = new JTextField(15);
        JLabel genderLabel = new JLabel("Gender:", JLabel.RIGHT);
        genderComboBox = new JComboBox<String>(new String[] { "Male", "Female", "Other" });
        JLabel addressLabel = new JLabel("Address:", JLabel.RIGHT);
        addressArea = new JTextArea(3, 20);
        JScrollPane addressScrollPane = new JScrollPane(addressArea);
        JLabel dobLabel = new JLabel("Date of Birth:", JLabel.RIGHT);
        dobField = new JTextField();
        JButton addButton = new JButton("Add");
        JButton clearButton = new JButton("Clear");

        add(nameLabel);
        add(nameField);
        add(phoneLabel);
        add(phoneField);
        add(emailLabel);
        add(emailField);
        add(genderLabel);
        add(genderComboBox);
        add(addressLabel);
        add(addressScrollPane);
        add(dobLabel);
        add(dobField);
        add(addButton);
        add(clearButton);

        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                RegexWork rw = new RegexWork();
                String name = nameField.getText().trim();
                String phone = phoneField.getText().trim();
                if (!rw.isAValidMobileNumber(phone))
                    phone = "";
                String email = emailField.getText().trim();
                if (!rw.isAValidEmail(email))
                    email = "";

                String gender = (String) genderComboBox.getSelectedItem();
                String address = addressArea.getText().trim();
                String dob = dobField.getText().trim();

                if (!name.equals("") && !phone.equals("")) {
                    Contact contact = new Contact(phone, name, email, gender, address, dob);
                    ContactHelper ch = new ContactHelper();
                    ch.addContact(ref.vc, contact);
                    ch.saveContacts(ref.vc);
                    clearFields();
                }
            }

        });

        phoneField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke){
                String mobileText = phoneField.getText().trim();
                RegexWork rw = new RegexWork();
                if(!mobileText.equals("")){
                    if(!rw.isAValidMobileNumber(mobileText)){
                        phoneField.setForeground(Color.RED);
                    } else {
                        phoneField.setForeground(Color.BLACK);
                    }
                }
            }
        });

        emailField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                String emailText = emailField.getText().trim();
                RegexWork rw = new RegexWork();
                if (!emailText.equals("")) {
                    if (!rw.isAValidEmail(emailText)) {
                        emailField.setForeground(Color.RED);
                    } else {
                        emailField.setForeground(Color.BLACK);
                    }
                }
            }
        });


        dobField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                String dobText = dobField.getText().trim();
                RegexWork rw = new RegexWork();
                if (!dobText.equals("")) {
                    if (!rw.isAValidDate(dobText)) {
                        dobField.setForeground(Color.RED);
                    } else {
                        dobField.setForeground(Color.BLACK);
                    }
                }
            }
        });


        

    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        genderComboBox.setSelectedIndex(0);
        addressArea.setText("");
        dobField.setText("");
    }

}