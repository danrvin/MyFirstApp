package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.DataBaseHandler;
import sample.objects.User;
import sample.animations.Shake;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField SurnameSignUpButton;

    @FXML
    private PasswordField PasswordSignUpBatton;

    @FXML
    private Button EnterSignUpButton;

    @FXML
    private TextField NameSignUpButton;

    @FXML
    private TextField LoginSignUpButton;

    @FXML
    private TextField LocationSignUpButton;

    @FXML
    private CheckBox ManSignUpCheckButton;

    @FXML
    private CheckBox WomanSignUpCheckButton;

    @FXML
    private Button backButon;

    @FXML
    void initialize() {

        EnterSignUpButton.setOnAction(event -> {

            singUpNewUser();

        });

        backButon.setOnAction(event -> {
            Controller controller = new Controller();
            controller.openNewScene("/sample/fxmls/sample.fxml", backButon);
        });

    }

    private void singUpNewUser() {

        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstName = NameSignUpButton.getText();
        String lastName = SurnameSignUpButton.getText();
        String userName = LoginSignUpButton.getText();
        String password = PasswordSignUpBatton.getText();
        String location = LocationSignUpButton.getText();
        String gender = "";
        if (ManSignUpCheckButton.isSelected())
            gender = "Man";
        else if (WomanSignUpCheckButton.isSelected())
            gender = "Woman";

        int counter = 0;

        if (firstName.equals("") | lastName.equals("") | userName.equals("") |
                password.equals("") | location.equals("") | gender.equals("")) {
            counter++;
        }

        User user = new User(firstName, lastName, userName, password, location, gender);
        if (counter == 0) {
            userIdPassword.setPasswordName(password);
            userIdPassword.setUserName(userName);
            dbHandler.signUpUser(user);
        } else {
            Shake enterSignUpButton = new Shake(EnterSignUpButton);
            enterSignUpButton.playAnim();
        }
        NameSignUpButton.clear();
        SurnameSignUpButton.clear();
        LoginSignUpButton.clear();
        PasswordSignUpBatton.clear();
        LocationSignUpButton.clear();
    }

    public User userIdPassword = new User();
}
