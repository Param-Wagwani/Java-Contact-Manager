package ContactManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegexWork {
    Contact convertFromStringToContact(Matcher groupMatcher) {

        String mobileNumber = getMobileNumberFromString(groupMatcher.group(2));
        String name = getNameFromString(groupMatcher.group(2));
        String email = getEmailFromString(groupMatcher.group(2));
        String gender = getGenderFromString(groupMatcher.group(2));
        String address = getAddressFromString(groupMatcher.group(2));
        String dob = getDOBFromString(groupMatcher.group(2));

        return new Contact(mobileNumber, name, email, gender, address, dob);
    }

    private String getGenderFromString(String group) {
        String GroupRegex = "(<gender>)(.*?)(</gender>)";
        Pattern groupPattern = Pattern.compile(GroupRegex);
        Matcher groupMatcher = groupPattern.matcher(group);
        return groupMatcher.find() ? groupMatcher.group(2) : "";
    }

    private String getEmailFromString(String group) {
        String GroupRegex = "(<email>)(.*?)(</email>)";
        Pattern groupPattern = Pattern.compile(GroupRegex);
        Matcher groupMatcher = groupPattern.matcher(group);
        return groupMatcher.find() ? groupMatcher.group(2) : "";
    }

    private String getAddressFromString(String group) {
        String GroupRegex = "(<address>)(.*?)(</address>)";
        Pattern groupPattern = Pattern.compile(GroupRegex);
        Matcher groupMatcher = groupPattern.matcher(group);
        return groupMatcher.find() ? groupMatcher.group(2) : "";
    }

    private String getDOBFromString(String group) {
        String GroupRegex = "(<dateOfBirth>)(.*?)(</dateOfBiirth>)";
        Pattern groupPattern = Pattern.compile(GroupRegex);
        Matcher groupMatcher = groupPattern.matcher(group);
        return groupMatcher.find() ? groupMatcher.group(2) : "";
    }

    private String getMobileNumberFromString(String group) {
        String GroupRegex = "(<mobileNumber>)(.*?)(</mobileNumber>)";
        Pattern groupPattern = Pattern.compile(GroupRegex);
        Matcher groupMatcher = groupPattern.matcher(group);
        return groupMatcher.find() ? groupMatcher.group(2) : "";
    }

    private String getNameFromString(String group) {
        String GroupRegex = "(<name>)(.*?)(</name>)";
        Pattern groupPattern = Pattern.compile(GroupRegex);
        Matcher groupMatcher = groupPattern.matcher(group);
        return groupMatcher.find() ? groupMatcher.group(2) : "";
    }

    boolean isAValidEmail(String Email) {
        String emailRegex = "^([A-Za-z]([A-Za-z._0-9]+)*)@([0-9A-Za-z\\-_]+).(\\w{2,3}(\\.w{2})*)$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(Email);
        if (emailMatcher.matches())
            return true;

        return false;
    }

    boolean isAValidMobileNumber(String mobileNumber) {
        String emailRegex = "^\\+[1-9]{1,3}\\s{0,1}[1-9][0-9]{1,14}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(mobileNumber);
        if (emailMatcher.matches())
            return true;

        return false;
    }

     boolean isAValidDate(String dateStr) {
    
        String regex1 = "^(0?[1-9]|[12][0-9]|3[01])[-\\/](0?[1-9]|1[0-2])[-\\/]\\d{4}$"; 
        String regex2 = "^(0?[1-9]|1[0-2])[-\\/](0?[1-9]|[12][0-9]|3[01])[-\\/]\\d{4}$";
        String regex3 = "^\\d{4}[-\\/](0?[1-9]|1[0-2])[-\\/](0?[1-9]|[12][0-9]|3[01])$"; // 
        
     
        Pattern pattern1 = Pattern.compile(regex1);
        Pattern pattern2 = Pattern.compile(regex2);
        Pattern pattern3 = Pattern.compile(regex3);

    
        Matcher matcher1 = pattern1.matcher(dateStr);
        Matcher matcher2 = pattern2.matcher(dateStr);
        Matcher matcher3 = pattern3.matcher(dateStr);

        
        if (matcher1.matches() || matcher2.matches() || matcher3.matches()) 
            return true;
       return false; 
    }

    

}


//^([0-3]{0,1}[0-9])[\/\-|]([[0-1]{0,1}[0-2])[\/\-|](\d{4})$   dd-mm-yyyy

//^([0-1]{0,1}[0-2])[\/\-|]([[0-3]{0,1}[0-9])[\/\-|](\d{4})$   mm-dd-yyyy

//^(\d{4})[\/\-|]([[0-3]{0,1}[0-9])[\/\-|]([0-1]{0,1}[0-2])$  yyyy-dd-mm