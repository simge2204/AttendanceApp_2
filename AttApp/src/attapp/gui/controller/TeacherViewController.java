package attapp.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import attapp.be.Attendance;
import attapp.be.SchoolClass;
import attapp.be.Student;
import attapp.be.Teacher;
import attapp.gui.model.SchoolAppModel;
import attapp.gui.controller.LoginController;
import java.sql.SQLException;
import javafx.scene.control.ListView;
import static org.omg.CORBA.CompletionStatusHelper.id;

/**
 * FXML Controller class
 *
 * @author simge
 */
public class TeacherViewController implements Initializable
{

    private Teacher teacher;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, String> name;
    @FXML
    private TableColumn<Student, String> schoolClass;
    @FXML
    private TableColumn<Student, String> email;
    @FXML
    private TableColumn<Student, Double> absence;
    @FXML
    private ChoiceBox<SchoolClass> classChooser;

    private SchoolAppModel model;
    private BorderPane borderPane;

    private ObservableList<Student> curClass;
    @FXML
    private LineChart<String, Double> chart;
    @FXML
    private NumberAxis percentage;
    @FXML
    private CategoryAxis days;

    @FXML
    private Label absenceClass;
    @FXML
    private BarChart<String, Integer> dayChart;
    @FXML
    private NumberAxis dayY;
    @FXML
    private CategoryAxis dayX;

    @FXML
    private Label tName;
    @FXML
    private Label tMail;
    @FXML
    private AnchorPane teacherPage;
    private BorderPane rootLayout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        try {
            model = new SchoolAppModel();
            
            // init tableview
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            schoolClass.setCellValueFactory(new PropertyValueFactory<>("schoolClass"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            absence.setCellValueFactory(new PropertyValueFactory<>("abPercentage"));
            absence.setSortType(TableColumn.SortType.DESCENDING);
            
            classChooser.setItems(model.getAllClasses());
            classChooser.getSelectionModel().selectFirst();
            //Setting up the charts
            
            chart.setTitle("Fraværshistorik");
            chart.setLegendVisible(false);
            chart.setAnimated(false);
            
            dayChart.setLegendVisible(false);
            dayChart.setTitle("Fravær pr. dag");
            dayChart.setAnimated(false);
            
            tName.setText("Navn");
            tMail.setText("email");
          
            
//            tName.setText(teacher.getName());
//            tMail.setText(teacher.getEmail());
//            
            classChooser.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
            {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
                {
                    Platform.runLater(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            try
                            {
                                setTableView();
                                calculateAverageAbsence();
                            } catch (ParseException ex)
                            {
                                Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    
                }
                
            });
            
            tableView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
            {

                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
                {
                    Platform.runLater(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            
                            try {
                                initStudentLineChart();
                                initStudentBarChart();
                            } catch (IOException ex) {
                                Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                    });
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TeacherViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setTableView()
    {
        curClass = FXCollections.observableArrayList(classChooser.getSelectionModel().getSelectedItem().getAllStudents());
        tableView.setItems(curClass);
        tableView.getSortOrder().setAll(absence);

    }

    private void initStudentLineChart() throws IOException, SQLException
    {
        chart.getData().clear();
        // Gets the selected student
        Student chosenStudent = tableView.getSelectionModel().getSelectedItem();
        if (chosenStudent != null)
        {
            calculateAbsence(chosenStudent);
        }

    }

    private void calculateAbsence(Student s) throws IOException, SQLException
    {

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        ArrayList<Attendance> allAttendance = model.getAttendance(s.getId());

        int numberOfDays = 0;
        double daysAttended = 0;

        for (Attendance x : allAttendance)
        {
            numberOfDays++;
            if (x.getWasThere() == true)
            {
                daysAttended++;
            }
            int calAttendance = (int) (100 - daysAttended / numberOfDays * 100);

            series.getData().add(new XYChart.Data("" + numberOfDays, calAttendance));
        }

        chart.getData().add(series);

    }

    public void calculateAverageAbsence() throws ParseException
    {
        ArrayList<Student> allStudents = classChooser.getSelectionModel().getSelectedItem().getAllStudents();
        double averageAbsence = 0;
        double numberOfStudents = allStudents.size();
        for (Student x : allStudents)
        {
            averageAbsence = averageAbsence + x.getAbPercentage();
        }
        absenceClass.setText("" + averageAbsence / numberOfStudents);
    }

    private void initStudentBarChart()
    {

        dayChart.getData().clear();
        // Gets the selected student

        Student chosenStudent = tableView.getSelectionModel().getSelectedItem();
        if (chosenStudent != null)
        {

            calculateWeekdayAbsence(chosenStudent);

        }

    }

    private void calculateWeekdayAbsence(Student chosenStudent)
    {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        ArrayList<Integer> weekDays = chosenStudent.getMostAbsentDay();

        series.getData().add(new XYChart.Data("Man", weekDays.get(0)));
        series.getData().add(new XYChart.Data("Tir", weekDays.get(1)));
        series.getData().add(new XYChart.Data("Ons", weekDays.get(2)));
        series.getData().add(new XYChart.Data("Tor", weekDays.get(3)));
        series.getData().add(new XYChart.Data("Fre", weekDays.get(4)));

        dayChart.getData().add(series);

    }

    @FXML
    private void openLineChart(MouseEvent event)
    {

        Stage newStage = new Stage();

        NumberAxis y = new NumberAxis();
        CategoryAxis x = new CategoryAxis();
        LineChart l = new LineChart(x, y);

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        if (tableView.getSelectionModel().getSelectedItem() != null)
        {
            ArrayList<Attendance> allAttendance = tableView.getSelectionModel().getSelectedItem().getFullAttendance();

            int numberOfDays = 0;
            double daysAttended = 0;

            for (Attendance k : allAttendance)
            {
                numberOfDays++;
                if (k.getWasThere() == true)
                {
                    daysAttended++;
                }
                int calAttendance = (int) (100 - daysAttended / numberOfDays * 100);

                series.getData().add(new XYChart.Data("" + numberOfDays, calAttendance));
            }

            l.getData().add(series);
            l.setLegendVisible(false);
            l.setTitle("Fraværshistorik");
            y.setLabel("Fravær i procent");
            x.setLabel("Antal skoledage");

            BorderPane bPane = new BorderPane();
            bPane.setCenter(l);

            bPane.getStyleClass().add("background");

            Scene newScene = new Scene(bPane);
            newStage.setHeight(600);
            newStage.setWidth(1000);
            newStage.setResizable(false);

            newScene.getStylesheets().add("attapp/gui/view/Style.css");

            newStage.setScene(newScene);
            newStage.show();
        }
    }

    @FXML
    private void openBarChart(MouseEvent event)
    {
        Student chosenStudent = tableView.getSelectionModel().getSelectedItem();
        if (chosenStudent != null)
        {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            ArrayList<Integer> weekDays = chosenStudent.getMostAbsentDay();

            series.getData().add(new XYChart.Data("Man", weekDays.get(0)));
            series.getData().add(new XYChart.Data("Tir", weekDays.get(1)));
            series.getData().add(new XYChart.Data("Ons", weekDays.get(2)));
            series.getData().add(new XYChart.Data("Tor", weekDays.get(3)));
            series.getData().add(new XYChart.Data("Fre", weekDays.get(4)));

            NumberAxis c = new NumberAxis();
            c.setLabel("Dage med fravær");
            CategoryAxis n = new CategoryAxis();

            BarChart b = new BarChart(n, c);
            b.setTitle("Fravær pr. dag");
            b.setLegendVisible(false);
            b.getData().add(series);

            Stage newStage = new Stage();

            BorderPane bPane = new BorderPane();
            bPane.setCenter(b);

            bPane.getStyleClass().add("background");

            Scene newScene = new Scene(bPane);
            newStage.setHeight(600);
            newStage.setWidth(1000);
            newStage.setResizable(false);

            newScene.getStylesheets().add("attapp/gui/view/Style.css");

            newStage.setScene(newScene);
            newStage.show();

        }
    }

    @FXML
    private void teacherLogOut(ActionEvent event) throws IOException
    {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/attapp/gui/view/LoginView.fxml"));
        Parent root = loader.load();
        LoginController con = loader.getController();
        con.setRootLayout(rootLayout);
        rootLayout.setCenter(root);
    }

    void setRootLayout(BorderPane rootLayout)
    {
        this.rootLayout = rootLayout;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
 
  
 
            

}
