package attapp.gui.model;

import java.util.ArrayList;
import java.util.Calendar;
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
public class SchoolAppModel
{

    private final SchoolAppManager manager;
    private Student s;
    private ObservableList<Attendance> oList;
    private ObservableList<SchoolClass> classList;
    private Teacher t;

    public SchoolAppModel() throws IOException
    {
        this.manager = new SchoolAppManager();
        classList=FXCollections.observableArrayList();
        classList.add(new SchoolClass("Hold"));
        
    }
    
    
    public ObservableList<SchoolClass> getAllClasses()
    {

        return classList;
    }
    
    public boolean checkForSchoolNetwork()
    {
        return manager.checkForSchoolNetwork();
    }

    public boolean checkForDailyAttendance(Date date) throws IOException, SQLException
    {
        return manager.checkForDailyAttendance(date);
    }

    public void askForAttendance(int id, Attendance chosenAttendance)
    {
        manager.askForAttendance(id,chosenAttendance);
    }

public ArrayList<Attendance>  getAttendance( int studId)throws SQLServerException, IOException, SQLException{
        return manager.getAttendance(studId);
    }
    
    
}
