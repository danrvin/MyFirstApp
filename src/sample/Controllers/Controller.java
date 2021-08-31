package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataBaseHandler;
import sample.objects.User;
import sample.animations.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registerButton;

    @FXML
    private TextField LoginButton;

    @FXML
    private PasswordField PasswordBatton;

    @FXML
    private Button EnterButton;

    @FXML
    private Button adminButton;

    @FXML
    void initialize() {

        EnterButton.setOnAction(event -> {
            String loginText = LoginButton.getText().trim();         //убирает пробелы в написанном пользователем
            String loginPassword = PasswordBatton.getText().trim();  //убирает пробелы в написанном пользователем

            if (!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);
            else
                System.out.println("Login and password is empty");
        });

        registerButton.setOnAction(event -> {
            openNewScene("/sample/fxmls/signUp.fxml",registerButton);
        });

        adminButton.setOnAction(event -> {
            openNewScene("/sample/fxmls/admin.fxml",adminButton);
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPasswordName(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (counter >= 1) {
            openNewScene("/sample/fxmls/inventory.fxml",EnterButton);
        } else {
            Shake userLoginAnim = new Shake(LoginButton);
            Shake userPasswordAnim = new Shake(PasswordBatton);
            Shake enterButton = new Shake(EnterButton);
            userLoginAnim.playAnim();
            userPasswordAnim.playAnim();
            enterButton.playAnim();
        }
    }

    public void openNewScene(String window, Button button) {
        button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("fxmls/sample.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
