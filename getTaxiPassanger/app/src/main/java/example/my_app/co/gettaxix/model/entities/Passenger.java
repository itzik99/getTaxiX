package example.my_app.co.gettaxix.model.entities;

public class Passenger extends Users {
private String phoneNum;
private String name;
private String email;
private String origLong;
private String origLat;
private String destLong;
private String destLat;
private Boolean isPicked;


    public Passenger(){}

    public Passenger(String phoneNum, String name, String email, String origLong, String origLat, String destLong, String destLat, Boolean isPicked) {
        this.phoneNum = phoneNum;
        this.name = name;
        this.email = email;
        this.origLong = origLong;
        this.origLat = origLat;
        this.destLong = destLong;
        this.destLat = destLat;
        this.isPicked = isPicked;
    }

    @Override
    public String getPhoneNum() {
        return phoneNum;
    }

    @Override
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrigLong() {
        return origLong;
    }

    public void setOrigLong(String origLong) {
        this.origLong = origLong;
    }

    public String getOrigLat() {
        return origLat;
    }

    public void setOrigLat(String origLat) {
        this.origLat = origLat;
    }

    public String getDestLong() {
        return destLong;
    }

    public void setDestLong(String destLong) {
        this.destLong = destLong;
    }

    public String getDestLat() {
        return destLat;
    }

    public void setDestLat(String destLat) {
        this.destLat = destLat;
    }
    public boolean isPicked() {
        return isPicked;
    }

    public void setPicked(boolean picked) {
        isPicked = picked;
    }

}
