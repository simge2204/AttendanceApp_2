/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attapp.gui.controller;

import attapp.be.Attendance;
import attapp.be.SchoolClass;
import attapp.be.Student;
import attapp.gui.model.SchoolAppModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Caspe
 */
public class ListOfAbsenceController implements Initializable
{

    @FXML
    private AnchorPane pane;
    @FXML
    private ChoiceBox<SchoolClass> choiseTeam;
    @FXML
    private Button changeBtn;
    @FXML
    private TableColumn<Student, String> stName;
    @FXML
    private TableColumn<Attendance, String> date1;
    @FXML
    private TableColumn<Attendance, String> dato2;
    @FXML
    private TableView<Student> StudentView;
    @FXML
    private TableView<Attendance> dateView;
    @FXML
    private TableView<Attendance> changeView;
    @FXML
    private TableColumn<Attendance, Boolean> changedAtten;
    
    private SchoolAppModel modelT;
    @FXML
    private Button closeBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       try 
        {
            modelT = new SchoolAppModel();
        } catch (IOException ex) 
        {
            Logger.getLogger(StudentViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stName.setCellValueFactory(new PropertyValueFactory<>("Navn"));
        date1.setCellValueFactory(new PropertyValueFactory<>("Dato"));
        dato2.setCellValueFactory(new PropertyValueFactory<>("Dato"));
        changedAtten.setCellValueFactory(new PropertyValueFactory<>("Godkendt"));
        
        choiseTeam.setItems(modelT.getAllClasses());
        choiseTeam.getSelectionModel().selectFirst();
    }    


    @FXML
    private void editAttendance(ActionEvent event)
    {
    }

    @FXML
    private void close(ActionEvent event)
    {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    
}
