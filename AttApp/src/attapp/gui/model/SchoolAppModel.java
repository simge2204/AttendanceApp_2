package attapp.gui.model;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import attapp.be.Attendance;
import attapp.be.SchoolClass;
import attapp.be.Student;
import attapp.be.Teacher;
import attapp.bll.SchoolAppManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author Christian Occhionero
 */
public class SchoolAppModel {

    private final SchoolAppManager manager;
    private Student s;
    private ObservableList<Attendance> oList;
    private ObservableList<SchoolClass> classList;
    private Teacher t;

    public SchoolAppModel() throws IOException, SQLException {
        this.manager = new SchoolAppManager();
        classList = FXCollections.observableList(manager.getAllClasses(((1014))));
        classList.add(new SchoolClass("Hold", 1));

    }

    public ObservableList<SchoolClass> getAllClasses() {
//      
        return classList;
    }

    public boolean checkForSchoolNetwork() {
        return manager.checkForSchoolNetwork();
    }

    public boolean checkForDailyAttendance(Date date) throws IOException, SQLException {
        return manager.checkForDailyAttendance(date);
    }

    public void askForAttendance(int id, Attendance chosenAttendance) {
        manager.askForAttendance(id, chosenAttendance);
    }

    public ArrayList<Attendance> getAttendance(int studId) throws SQLServerException, IOException, SQLException {
        return manager.getAttendance(studId);
    }

    public void editAttendance(int id, String date) throws SQLException, SQLServerException, IOException {

        manager.editAttendance(id, date);

    }

    public Attendance addAttendanceDays(int id) throws SQLServerException, SQLException {
        return manager.addAttendanceDays(id);
    }

}
