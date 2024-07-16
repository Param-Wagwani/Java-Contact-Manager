
package ContactManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.Vector;

class AllContactsPanel extends JPanel {
    private ApplicationFrame ref;
    private Vector<Vector<Object>> dataMatrix;
    private Vector<String> headers;
    private JTable table;

    AllContactsPanel(ApplicationFrame ref) {
        this.ref = ref;
        setLayout(new BorderLayout());
        JPanel searchPane = new JPanel();
        searchPane.add(new JLabel("Search", JLabel.RIGHT));
        JTextField searchField = new JTextField(15);
        searchPane.add(searchField);
        add(searchPane,BorderLayout.NORTH);

        headers = new Vector<>();
        headers.add("Mobile Number");
        headers.add("Name");
        headers.add("Email");
        headers.add("Gender");
        headers.add("Address");
        headers.add("Date Of Birth");

        dataMatrix = new Vector<>();
        table = new JTable(dataMatrix, headers);
        JPanel tablePane = new JPanel();
        tablePane.setLayout(new BorderLayout());
        tablePane.add(table.getTableHeader(),BorderLayout.NORTH);
        tablePane.add(table,BorderLayout.CENTER);
        add(tablePane,BorderLayout.CENTER);

        searchField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                String text = searchField.getText().trim();
                String regex = ".*" + text + ".*";
                Vector<Contact> matched = getContactsWhichMatchRegex(regex);
                updateTable(matched);
            }
        });
    }

    void updateTable(Vector<Contact> vc) {
        dataMatrix.clear();

        Enumeration<Contact> ec = vc.elements();
        while (ec.hasMoreElements()) {
            Contact contact = ec.nextElement();
            Vector<Object> rowData = new Vector<>();
            rowData.add(contact.mobileNumber);
            rowData.add(contact.name);
            rowData.add(contact.emailID);
            rowData.add(contact.gender);
            rowData.add(contact.address);
            rowData.add(contact.DOB);
            dataMatrix.add(rowData);
        }
        repaint();
        revalidate();
        
    }

    Vector<Contact> getContactsWhichMatchRegex(String regex) {
        Vector<Contact> matched = new Vector<>();

        Enumeration<Contact> ec = ref.vc.elements();
        while (ec.hasMoreElements()) {
            Contact contact = ec.nextElement();
            if (contact.mobileNumber.matches(regex) || contact.name.matches(regex)) {
                matched.add(contact);
            }
        }

        return matched;
    }
}
