package sample.Controllers;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.DataBaseHandler;
import sample.animations.Shake;
import sample.objects.Item;

public class AdminItemController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox addButton;

    @FXML
    private CheckBox removeButton;

    @FXML
    private TextField nameSignUpButton;

    @FXML
    private Button enterButton;

    @FXML
    private TextArea textFieldButton;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        Controller controller = new Controller();
        backButton.setOnAction(event -> {
            controller.openNewScene("/sample/fxmls/admin.fxml", backButton);
        });

        enterButton.setOnAction(event -> {
            String nameItem = nameSignUpButton.getText().trim();
            if (addButton.isSelected()) {
                addItem();
            } else if (removeButton.isSelected()) {
                removeItem(nameItem);
            } else {
                Shake addBut = new Shake(addButton);
                Shake removeBut = new Shake(removeButton);
                addBut.playAnim();
                removeBut.playAnim();
            }
        });
    }

    private void addItem() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String nameSignUp = nameSignUpButton.getText();
        String textField = textFieldButton.getText();
        Item item = new Item(nameSignUp, textField);
        dbHandler.signUpItem(item);
    }

    public void removeItem(String nameItem) {
        DataBaseHandler dbHandler = new DataBaseHandler();
        Item item = new Item();
        item.setNameItem(nameItem);
        ResultSet result = dbHandler.getItem(item);

        int counter = 0;

        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (counter >= 1) {
            dbHandler.deleteItem(item);
        } else {
            Shake enterBut = new Shake(enterButton);
            enterBut.playAnim();
        }
    }
}
