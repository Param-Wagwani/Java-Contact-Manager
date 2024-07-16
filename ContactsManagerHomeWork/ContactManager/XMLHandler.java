package ContactManager;

class XMLHandler {
    String formatContactInXMLEntry(Contact contact) {
        StringBuffer contactEntry = new StringBuffer();
        contactEntry.append("\t<Contact>\n");
        contactEntry.append("\t\t<name>" + contact.name + "</name>\n");
        contactEntry.append("\t\t<mobileNumber>" + contact.mobileNumber + "</mobileNumber>\n");
        contactEntry.append("\t\t<email>" + contact.emailID + "</email>\n");
        contactEntry.append("\t\t<gender>" + contact.gender + "</gender>\n");
        contactEntry.append("\t\t<address>" + contact.address + "</address>\n");
        contactEntry.append("\t\t<dateOfBirth>" + contact.DOB + "</dateOfBirth>\n");
        contactEntry.append("\t</Contact>");

        return new String(contactEntry);
    }
}
