package ContactManager;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

public class EditContactPanel extends JPanel {
    ApplicationFrame ref;
    JList<String> contactList;
    Vector<String> suggestions;

    JLabel mobileLabel;
    JLabel mobileValueLabel;
    JLabel nameLabel;
    JLabel nameValuLabel;
    JLabel emailLabel;
    JLabel emailValueLabel;
    JLabel genderLabel;
    JLabel genderValueLabel;
    JLabel addressLabel;
    JLabel addressValueLabel;
    JLabel DOBLabel;
    JLabel DOBValueLabel;

    JTextField editPhoneTextField;
    JTextField editNameTextField;
    JTextField editEmailTextField;
    JComboBox<String> genderBox;
    JTextArea addressValue;
    JTextField dobTextField;

    JPanel detailsJPanel;

    JButton editButton;
    JButton deleButton;
    JButton cancelButton;
    JButton saveButton;

    int Foundindex;

    public EditContactPanel(ApplicationFrame ref) {
        this.ref = ref;

        add(new JLabel("Search", JLabel.RIGHT));
        JTextField searchField = new JTextField(15);
        add(searchField);

        suggestions = new Vector<>();
        contactList = new JList<>();
        contactList.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent lse) {

                String sel = contactList.getSelectedValue();
                // System.out.println(sel);
                if (sel != null) {
                    String arr[] = sel.split("         ");
                    Contact con = new Contact(arr[1].trim(), arr[0].trim());
                    ContactHelper ch = new ContactHelper();
                    int index = ch.binarySearch(ref.vc, con);
                    System.out.println(index);
                    if (index != -1) {
                        Contact contactSelected = ref.vc.get(index);
                        Foundindex = index;
                        System.out.println(contactSelected.mobileNumber);
                        contactList.clearSelection();
                        displayDetails(contactSelected);

                        searchField.setText("");
                        suggestions.clear();
                        contactList.setListData(suggestions);
                        repaint();
                        revalidate();

                    }

                }
            }

        });

        searchField.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {

                String searchText = searchField.getText().trim();
                if (!searchText.equals("")) {
                    add(contactList);
                } else {
                    remove(contactList);
                }
                String regex = ".*" + searchText + ".*";
                Vector<Contact> matchedContacts = ref.all.getContactsWhichMatchRegex(regex);

                updateSuggestions(matchedContacts);
            }
        });
    }

    void displayDetails(Contact contact) {
        if(detailsJPanel!=null){
            removeDetails();
        }
        detailsJPanel = new JPanel();
        add(detailsJPanel);
        detailsJPanel.setLayout(new GridLayout(8, 2));

        mobileLabel = new JLabel("Mobile: ", JLabel.RIGHT);
        mobileValueLabel = new JLabel(contact.mobileNumber);
        detailsJPanel.add(mobileLabel);
        detailsJPanel.add(mobileValueLabel);

        nameLabel = new JLabel("Name: ", JLabel.RIGHT);
        nameValuLabel = new JLabel(contact.name);
        detailsJPanel.add(nameLabel);
        detailsJPanel.add(nameValuLabel);

        emailLabel = new JLabel("Email: ", JLabel.RIGHT);
        emailValueLabel = new JLabel(contact.emailID);
        detailsJPanel.add(emailLabel);
        detailsJPanel.add(emailValueLabel);

        genderLabel = new JLabel("Gender: ", JLabel.RIGHT);
        genderValueLabel = new JLabel(contact.gender);
        detailsJPanel.add(genderLabel);
        detailsJPanel.add(genderValueLabel);

        addressLabel = new JLabel("Address: ", JLabel.RIGHT);
        addressValueLabel = new JLabel(contact.address);
        detailsJPanel.add(addressLabel);
        detailsJPanel.add(addressValueLabel);

        DOBLabel = new JLabel("Date of Birth: ", JLabel.RIGHT);
        DOBValueLabel = new JLabel(contact.DOB);
        detailsJPanel.add(DOBLabel);
        detailsJPanel.add(DOBValueLabel);

        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Edit Event");
                editPhoneTextField = new JTextField(contact.mobileNumber, 15);
                detailsJPanel.remove(mobileValueLabel);
                detailsJPanel.add(editPhoneTextField, 1);

                editPhoneTextField.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent ke) {
                        String mobileText = editPhoneTextField.getText().trim();
                        RegexWork rw = new RegexWork();
                        if (!mobileText.equals("")) {
                            if (!rw.isAValidMobileNumber(mobileText)) {
                                editPhoneTextField.setForeground(Color.RED);
                            } else {
                                editPhoneTextField.setForeground(Color.BLACK);
                            }
                        }
                    }
                });

                editNameTextField = new JTextField(contact.name, 15);
                detailsJPanel.remove(nameValuLabel);
                detailsJPanel.add(editNameTextField, 3);

                editEmailTextField = new JTextField(contact.emailID, 15);

                editEmailTextField.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent ke) {
                        String emailText = editEmailTextField.getText().trim();
                        RegexWork rw = new RegexWork();
                        if (!emailText.equals("")) {
                            if (!rw.isAValidEmail(emailText)) {
                                editEmailTextField.setForeground(Color.RED);
                            } else {
                                editEmailTextField.setForeground(Color.BLACK);
                            }
                        }

                    }
                });

                detailsJPanel.remove(emailValueLabel);
                detailsJPanel.add(editEmailTextField, 5);

                String options[] = { "Male", "Female", "Other" };
                genderBox = new JComboBox<>(options);
                detailsJPanel.remove(genderValueLabel);
                detailsJPanel.add(genderBox, 7);

                addressValue = new JTextArea(contact.address);
                detailsJPanel.remove(addressValueLabel);
                detailsJPanel.add(addressValue, 9);

                dobTextField = new JTextField(contact.DOB, 15);
                detailsJPanel.remove(DOBValueLabel);
                detailsJPanel.add(dobTextField, 11);

                dobTextField.addKeyListener(new KeyAdapter() {
                    public void keyReleased(KeyEvent ke) {
                        String dobText = dobTextField.getText().trim();
                        RegexWork rw = new RegexWork();
                        if (!dobText.equals("")) {
                            if (!rw.isAValidEmail(dobText)) {
                                dobTextField.setForeground(Color.RED);
                            } else {
                                dobTextField.setForeground(Color.BLACK);
                            }
                        }
                    }
                });

                detailsJPanel.remove(editButton);
                saveButton = new JButton("Save");
                detailsJPanel.add(saveButton);

                saveButton.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        String updatedMobile = editPhoneTextField.getText();
                        String updatedName = editNameTextField.getText();
                        String updatedEmail = editEmailTextField.getText();
                        String updatedGender = (String) genderBox.getSelectedItem();
                        String updatedAddress = addressValue.getText();
                        String updatedDOB = dobTextField.getText();

                        Contact updatedContact = ref.vc.get(Foundindex);
                        updatedContact.mobileNumber = updatedMobile;
                        updatedContact.name = updatedName;
                        updatedContact.emailID = updatedEmail;
                        updatedContact.gender = updatedGender;
                        updatedContact.address = updatedAddress;
                        updatedContact.DOB = updatedDOB;

                        mobileValueLabel.setText(updatedMobile);
                        nameValuLabel.setText(updatedName);
                        emailValueLabel.setText(updatedEmail);
                        genderValueLabel.setText(updatedGender);
                        addressValueLabel.setText(updatedAddress);
                        DOBValueLabel.setText(updatedDOB);

                        detailsJPanel.remove(editPhoneTextField);
                        detailsJPanel.remove(editNameTextField);
                        detailsJPanel.remove(editEmailTextField);
                        detailsJPanel.remove(genderBox);
                        detailsJPanel.remove(addressValue);
                        detailsJPanel.remove(dobTextField);

                        detailsJPanel.add(mobileValueLabel, 1);
                        detailsJPanel.add(nameValuLabel, 3);
                        detailsJPanel.add(emailValueLabel, 5);
                        detailsJPanel.add(genderValueLabel, 7);
                        detailsJPanel.add(addressValueLabel, 9);
                        detailsJPanel.add(DOBValueLabel, 11);

                        detailsJPanel.remove(saveButton);
                        detailsJPanel.remove(cancelButton);

                        detailsJPanel.add(editButton);
                        detailsJPanel.add(deleButton);

                        revalidate();
                        repaint();
                    }

                });

                detailsJPanel.remove(deleButton);
                cancelButton = new JButton("Cancel");
                detailsJPanel.add(cancelButton);

                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {

                        detailsJPanel.remove(editPhoneTextField);
                        detailsJPanel.remove(editNameTextField);
                        detailsJPanel.remove(editEmailTextField);
                        detailsJPanel.remove(genderBox);
                        detailsJPanel.remove(addressValue);
                        detailsJPanel.remove(dobTextField);

                        detailsJPanel.add(mobileValueLabel, 1);
                        detailsJPanel.add(nameValuLabel, 3);
                        detailsJPanel.add(emailValueLabel, 5);
                        detailsJPanel.add(genderValueLabel, 7);
                        detailsJPanel.add(addressValueLabel, 9);
                        detailsJPanel.add(DOBValueLabel, 11);

                        detailsJPanel.remove(saveButton);
                        detailsJPanel.remove(cancelButton);

                        detailsJPanel.add(editButton);
                        detailsJPanel.add(deleButton);

                        revalidate();
                        repaint();
                    }
                });

                revalidate();
                repaint();
            }
        });
        detailsJPanel.add(editButton);

        deleButton = new JButton("Delete");
        detailsJPanel.add(deleButton);

        deleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ref.vc.remove(contact);
                removeDetails();
            }
        });

    }

    void removeDetails(){
        detailsJPanel.removeAll();
        detailsJPanel = null;
        repaint();
        revalidate();
    }

    void updateSuggestions(Vector<Contact> vc) {

        suggestions.clear();
        Enumeration<Contact> ec = vc.elements();
        while (ec.hasMoreElements()) {
            Contact contact = ec.nextElement();
            suggestions.add(contact.name + "         " + contact.mobileNumber);
        }

        contactList.setListData(suggestions);
        repaint();
        revalidate();
    }

}
