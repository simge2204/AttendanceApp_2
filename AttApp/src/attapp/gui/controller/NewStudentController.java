/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attapp.gui.controller;

import attapp.be.Student;
import attapp.gui.model.SchoolAppModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author simge
 */
public class NewStudentController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label schoolClass;
    @FXML
    private Label email;
    @FXML
    private Button addStd;
    @FXML
    private Button annStd;
    
    TeacherViewController teacherController = new TeacherViewController();
    SchoolAppModel appModel;
    private Student selectedStudent;
    private int type = 1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void tilføjElev(ActionEvent event) throws IOException, SQLException {
        String Navn = name.getText();
        int Klasse = Integer.parseInt(schoolClass.getText());
        String Email = email.getText();
        int id = 0;
        
        Stage stage = (Stage) addStd.getScene().getWindow();
        switch (type) 
        {
            case 1:
                appModel.addStudent(Navn, id, Email, Klasse);
                break;
//            case 2:
//                selectedStudent.setName(Navn);
//                selectedStudent.setText(Klasse);
//                selectedStudent.setText(Email);
//                break;
            default:
                System.out.println("Something went wrong");
                stage.close();
                break;
//       }
//        
//        teacherController.reload();
//        stage.close();
    }
    }
//
    public void setTeacherViewController(TeacherViewController teacherController) 
        {
        this.teacherController = teacherController;
        }
    
    public void setAppModel(SchoolAppModel appModel)
    {
        this.appModel = appModel;
    }
//
//    public void setEdit() throws SQLException
//        {
//        type = 2;
//        }
//
    public void setNew()
        {
        type = 1;
        }
    
    public void setStudent(Student selectedStudent) 
        {
        this.selectedStudent = selectedStudent;
        name.setText(selectedStudent.getName());
        schoolClass.setText(selectedStudent.getSchoolClass());
        email.setText(selectedStudent.getEmail());
        }

    @FXML
    private void annullerElev(ActionEvent event) {
        Stage stage = (Stage) annStd.getScene().getWindow();
        stage.close();
    }
}
