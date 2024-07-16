package ContactManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ContactHelper {

    int binarySearch(Vector<Contact> vc, Contact contact) {
        int high = vc.size() - 1;
        int low = 0;
        int index = -1;

        

        while (low <= high) {
            int mid = (low + high) / 2;
           ;

            if (vc.get(mid).name.compareToIgnoreCase(contact.name) == 0) {
                    index = mid;
                   
            }

            if (vc.get(mid).name.compareToIgnoreCase(contact.name) < 0) {
                low = mid + 1;
            } else {
                System.out.println("Yolo");
                high = mid - 1;
            }

        }

        return index;
    }

    void addContact(Vector<Contact> vc, Contact con) {
        int high = vc.size() - 1;
        int low = 0;
        int index = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (vc.get(mid).name.compareToIgnoreCase(con.name) < 0) {
                low = mid + 1;
                index = low;
            } else {
                high = mid - 1;
                index = mid;
            }
        }

        vc.insertElementAt(con, index);
    }

    void displayContacts(Vector<Contact> vc) {
        Enumeration<Contact> ec = vc.elements();
        while (ec.hasMoreElements()) {
            System.out.println(ec.nextElement().name);
        }
    }

    void saveContacts(Vector<Contact> vc) {
        XMLHandler xmlh = new XMLHandler();
        File xmlfile = new File("./tp.xml");

        Enumeration<Contact> ec = vc.elements();

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(xmlfile, false), true);
            pw.println("<ContactList>");
            while (ec.hasMoreElements()) {
                // System.out.println(xmlh.formatContactInXMLEntry(ec.nextElement()));
                pw.println(xmlh.formatContactInXMLEntry(ec.nextElement()));
                pw.println();

            }
            pw.println("</contactList>");

            pw.close();

        } catch (FileNotFoundException e) {
            System.out.println("caught");
        }

    }

    void loadContacts(File xmlfile, Vector<Contact> vc) {
        try {
            FileInputStream fis = new FileInputStream(xmlfile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            StringBuffer content = new StringBuffer("");
            RegexWork rw = new RegexWork();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);

            }

            String GroupRegex = "(<Contact>)(.*?)(</Contact>)";
            Pattern groupPattern = Pattern.compile(GroupRegex);
            Matcher groupMatcher = groupPattern.matcher(content);
            while (groupMatcher.find()) {
                vc.add(rw.convertFromStringToContact(groupMatcher));
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Caught");
        }
    }

}

