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
import sample.animations.Shake;
import sample.objects.Inventory;

public class AdminInventoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inventoryNameField;

    @FXML
    private Button enterButton;

    @FXML
    private CheckBox addInventory;

    @FXML
    private CheckBox removeInventory;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        Controller controller = new Controller();
        backButton.setOnAction(event -> {
            controller.openNewScene("/sample/fxmls/admin.fxml", backButton);
        });

        enterButton.setOnAction(event -> {
            if (addInventory.isSelected()) {
                addInvent(inventoryNameField.getText());
            }
            if (removeInventory.isSelected()) {
                removeInventory(inventoryNameField.getText());
            }
        });

    }

    private void addInvent(String nameInventory) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        Inventory inventory = new Inventory(nameInventory);
        dataBaseHandler.signUpAdminInventory(inventory);
    }

    private void removeInventory(String nameInventory) {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        Inventory inventory = new Inventory(nameInventory);
        ResultSet resultSet = dataBaseHandler.getInventory(inventory);
        int counter = 0;
        try {
            while (resultSet.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (counter >= 1) {
            dataBaseHandler.adminDeleteInventory(inventory);
        } else {
            Shake enterBut = new Shake(enterButton);
            Shake nameF = new Shake(inventoryNameField);
            enterBut.playAnim();
            nameF.playAnim();
        }
    }
}
