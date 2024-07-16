package ContactManager;
class Contact {
    String mobileNumber;
    String name;
    String emailID;
    String gender;
    String address;
    String DOB;

    Contact(String mobileNumber, String name) {
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.emailID = "";
        this.gender = "";
        this.address = "";
        this.DOB = "";
    }

    Contact(String mobileNumber, String name, String email, String gender, String address, String DOB) {
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.emailID = email;
        this.gender = gender;
        this.address = address;
        this.DOB = DOB;
    }

}
