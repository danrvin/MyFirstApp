package sample.objects;

public class Inventory {
    private String nameField;
    private String loginField;

    public Inventory(String nameField, String loginField) {
        this.nameField = nameField;
        this.loginField = loginField;
    }

    public Inventory() {
    }

    public String getLoginField() {
        return loginField;
    }

    public void setLoginField(String loginField) {
        this.loginField = loginField;
    }

    public Inventory(String textField) {
        this.nameField = textField;
    }

    public String getNameField() {
        return nameField;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }
}
