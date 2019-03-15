package attapp.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Philip
 */
public class RootLayoutController implements Initializable
{
    private double xOffSet = 0;
    private double yOffSet = 0;
    
    @FXML
    private BorderPane borderPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private HBox hBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
     
    }    

    @FXML
    private void logOut(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attapp/gui/view/LoginView.fxml"));
        Parent root = loader.load();
        LoginController con = loader.getController();
        con.setRootLayout(borderPane);
        
        borderPane.setCenter(root);
    }

    @FXML
    private void close(ActionEvent event)
    {
        Platform.exit();
    }

    @FXML
    private void about(ActionEvent event)
    {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setHeaderText("Om attapp");
        about.setContentText("Dette program holder styr på dit fravær");
        about.show();
    }

    @FXML
    private void closeBtn(MouseEvent event)
    {
        Platform.exit();
    }

    @FXML
    private void setOnMouseDragged(MouseEvent event) {
        
                }

    @FXML
    private void setOnMousePressed(MouseEvent event) {
    }
}
