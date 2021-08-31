package sample.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import sample.DataBaseHandler;

public class InventoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterButton;

    @FXML
    private CheckBox addInventory;

    @FXML
    private CheckBox removeInventory;

    @FXML
    private TextField inventoryNameField;

    @FXML
    private TextField loginField;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        backButton.setOnAction(event -> {
            Controller controller = new Controller();
            controller.openNewScene("/sample/fxmls/sample.fxml", backButton);
        });

        enterButton.setOnAction(event -> {
            if (addInventory.isSelected() && !removeInventory.isSelected()) {
                if (!inventoryNameField.getText().equals("") | !loginField.getText().equals("")) {
                    addInventory(inventoryNameField.getText(), loginField.getText());
                }
            }
            if (removeInventory.isSelected() && !addInventory.isSelected()) {
                if (!loginField.getText().equals("")) {
                    removeInventory(loginField.getText());
                }
            }
        });
    }

    private void removeInventory(String username) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ResultSet resultSet = dataBaseHandler.getUserInformation(username);
        try {
            if (resultSet.next()) {
                String idUser = resultSet.getString(1);
                dataBaseHandler.deleteInventory(idUser);
                dataBaseHandler.deleteUserInformation(idUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addInventory(String nameInventory, String loginUser) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ResultSet resultSet = dataBaseHandler.getUserInformation(loginUser);
        try {
            String idUser = null;
            if (resultSet.next()) {
                idUser = resultSet.getString(1);
            }

            dataBaseHandler.signUpInventory(nameInventory, idUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet resultSetUser = dataBaseHandler.getUserInformation(loginUser);
        try {
            String idUser;
            if (resultSetUser.next()) {
                idUser = resultSetUser.getString(1);
                String idInventory = getIdInventory(nameInventory);
                System.out.println(idInventory);
                dataBaseHandler.addUserInformation(idInventory,idUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getIdInventory(String nameInventory) {
        String idInventory = null;
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        ResultSet resultSet = dataBaseHandler.getInventoryInformation(nameInventory);
        try {
            if (resultSet.next()) {
                idInventory = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idInventory;
    }
}


