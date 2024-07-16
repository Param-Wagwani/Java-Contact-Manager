package ContactManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ApplicationFrame extends JFrame{
    Vector<Contact> vc;
    AddContactPanel add;
    EditContactPanel edit;
    AllContactsPanel all;
    public ApplicationFrame(String title){
        super(title);
       setSize(1000,800);
       setVisible(true);
       vc = new Vector<Contact>();
        JTabbedPane jtp = new JTabbedPane();
       add = new AddContactPanel(this);
       edit = new EditContactPanel(this);
       all = new AllContactsPanel(this);
       
         ContactHelper ch = new ContactHelper();

        File xmFile = new File("./tp.xml");

        if (xmFile.exists()) {
            ch.loadContacts(xmFile, vc);
        }

        jtp.addTab("Add", add);
        jtp.addTab("Edit Contact", edit);
        jtp.addTab("All Contacts", all);
        add(jtp);
       

      jtp.addChangeListener(new ChangeListener() {

        public void stateChanged(ChangeEvent ce) {
          
            if(jtp.getSelectedIndex()==2){
               all.updateTable(vc);
          }
        }
        
      });
    
      addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent we){
            ch.saveContacts(vc);
            System.exit(0);
        }
      });
        
    }
}
