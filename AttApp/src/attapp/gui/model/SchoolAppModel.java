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
import java.sql.Date;

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

    public SchoolAppModel()
    {
        this.manager = new SchoolAppManager();
        s = manager.getStudent(1);
        classList=FXCollections.observableArrayList();
        classList.add(new SchoolClass("Hold"));
        oList = FXCollections.observableArrayList(s.getFullAttendance());
    }

    public Student getStudent(int id)
    {
        return manager.getStudent(1);

    }

    public ObservableList<Attendance> getList()
    {
        return oList;
    }
    
    public ObservableList<SchoolClass> getAllClasses()
    {

        return classList;
    }
    
    public boolean checkForSchoolNetwork()
    {
        return manager.checkForSchoolNetwork();
    }

    public boolean checkForDailyAttendance(Date date)
    {
        return manager.checkForDailyAttendance(date);
    }

    public void askForAttendance(int id, Attendance chosenAttendance)
    {
        manager.askForAttendance(id,chosenAttendance);
    }

    public Teacher getTeacher()
    {
        Teacher t1 = manager.getTeacher();
        classList.addAll(manager.getTeacher().getAllClasses());
        t=t1;
        return t1;
    }
    
    
}
