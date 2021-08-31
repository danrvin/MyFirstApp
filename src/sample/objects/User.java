package sample.objects;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String passwordName;
    private String locationName;
    private String gender;

    public User(String firstName, String lastName, String userName,
                String passwordName, String locationName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passwordName = passwordName;
        this.locationName = locationName;
        this.gender = gender;
    }

    public User(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return passwordName;
    }

    public void setPasswordName(String passwordName) {
        this.passwordName = passwordName;
    }

    public String getLocation() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
