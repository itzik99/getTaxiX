package example.my_app.co.gettaxix.model.entities;

public abstract class Users {
    String fName, lName, phoneNum, email, localOrigin, localDest;

    public Users(String fName, String lName, String phoneNum, String email, String localOrigin, String localDest) {
        this.fName = fName;
        this.lName = lName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.localOrigin = localOrigin;
        this.localDest = localDest;
    }
    public Users(){}
//seters and geters
    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLocalOrigin(String localOrigin) {
        this.localOrigin = localOrigin;
    }

    public void setLocalDest(String localDest) {
        this.localDest = localDest;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getLocalOrigin() {
        return localOrigin;
    }

    public String getLocalDest() {
        return localDest;
    }
}
