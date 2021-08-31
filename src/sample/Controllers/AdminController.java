package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button userInformation;

    @FXML
    private Button inventorysInformation;

    @FXML
    private Button addRemoveInventory;

    @FXML
    private Button addRemoveItem;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        Controller controller = new Controller();
        backButton.setOnAction(event -> {
            controller.openNewScene("/sample/fxmls/sample.fxml",backButton);
        });

        addRemoveItem.setOnAction(event -> {
            controller.openNewScene("/sample/fxmls/adminItem.fxml",addRemoveItem);
        });

        addRemoveInventory.setOnAction(event -> {
            controller.openNewScene("/sample/fxmls/adminInventory.fxml",addRemoveInventory);
        });
    }
}
