/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attapp.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Caspe
 */
public class ListOfAbsenceController implements Initializable
{

    @FXML
    private ListView<?> EditView;
    @FXML
    private ListView<?> dateView;
    @FXML
    private ListView<?> studView;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button okBtn;
    @FXML
    private ChoiceBox<?> choiseTeam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void okAndClose(ActionEvent event)
    {
    }

    
}
