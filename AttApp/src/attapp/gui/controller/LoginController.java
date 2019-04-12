package attapp.gui.controller;

import attapp.be.Student;
import attapp.be.Teacher;
import attapp.dal.Authentication;
import attapp.dal.AttendanceDbDAO;
import attapp.dal.StudentDbDAO;
import attapp.bll.SchoolAppManager;
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
import javafx.scene.layout.BorderPane;
import java.sql.SQLException;

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

    SchoolAppManager bll;
    

    public LoginController() throws IOException {
        this.bll = new SchoolAppManager();
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
    private void openStudent(ActionEvent event) throws IOException, SQLException
    {
        Student stud = bll.getLoginStudent(username.getText(), password.getText());
        if (stud != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attapp/gui/view/StudentView.fxml"));
            
            StudentViewController.setStudent(stud);
            Parent root = loader.load();
            StudentViewController controller = loader.getController();
            System.out.println(controller);
            controller.setRootLayout(rootLayout);
            rootLayout.setCenter(root);
        } else
        {
            infoLabel.setText("Please type a valid password");
        }
    }

    public void setRootLayout(BorderPane rootLayout)
    {
        System.out.println(rootLayout);
        this.rootLayout = rootLayout;
    }

    @FXML
    private void openTeacher(ActionEvent event) throws IOException, SQLException
    {
        Teacher teach = bll.getLoginTeacher(username.getText(), password.getText());
                if (teach != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/attapp/gui/view/TeacherView.fxml"));
            
            TeacherViewController.setTeacher(teach);
            Parent root = loader.load();
            TeacherViewController controller = loader.getController();
            System.out.println(controller);
            controller.setTableView(); 
            controller.setRootLayout(rootLayout);
            rootLayout.setCenter(root);
        } else
        {
            infoLabel.setText("Please type a valid password");
        }
    }

}
