package attapp.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import attapp.dal.Authentication;
import attapp.gui.model.SchoolAppModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author simge
 */
public class LoginController implements Initializable
{

    @FXML
    private Button button;
    @FXML
    private Label label;

    private BorderPane rootLayout;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Label infoLabel;
    
    SchoolAppModel sam;
    
    public LoginController() throws IOException, SQLException {
        sam = new SchoolAppModel(); //badstuff
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void openStudent(ActionEvent event) throws IOException
    {
        if (Authentication.validateStudentLogin(username.getText(), password.getText()) == true)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/attapp/gui/view/StudentView.fxml"));
                Parent root = loader.load();
                StudentViewController controller = loader.getController();
                controller.setStudent(sam.getStudent());
                controller.setRootLayout(rootLayout);
                rootLayout.setCenter(root);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        {
            infoLabel.setText("Please type a valid password");
        }
    }

    public void setRootLayout(BorderPane rootLayout)
    {
        this.rootLayout = rootLayout;
    }

    @FXML
    private void openTeacher(ActionEvent event) throws IOException
    {
        if (Authentication.validateTeacherLogin(username.getText(), password.getText()) == true)
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/attapp/gui/view/TeacherView.fxml"));
                Parent root = loader.load();
                TeacherViewController controller = loader.getController();
                controller.setTeacher(sam.getTeacher());
                
                controller.setRootLayout(rootLayout);
                rootLayout.setCenter(root);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        {
            infoLabel.setText("Please type a valid password");
        }
    }

}
