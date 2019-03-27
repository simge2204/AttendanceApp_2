package attapp.gui.model;

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

    private SchoolAppManager manager;
    private Student s;
    private ObservableList<Attendance> oList;
    private ObservableList<SchoolClass> classList;
    private Teacher t;

    public SchoolAppModel() throws IOException, SQLException
    {
        this.manager = new SchoolAppManager();
        s = manager.getStudent(6);
        classList=FXCollections.observableArrayList();
        classList.add(new SchoolClass("Hold"));
        System.out.println(s);
            oList = FXCollections.observableArrayList(manager.getAttendance(s.getId())); //null pointer ra student
    }

    public Student getStudent(int id) throws IOException, SQLException
    {
        return manager.getStudent(6);

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

    public boolean checkForDailyAttendance(Date date) throws IOException, SQLException
    {
        return manager.checkForDailyAttendance(date);
    }

    public void askForAttendance(int id, Attendance chosenAttendance)
    {
        manager.askForAttendance(id,chosenAttendance);
    }

    public Teacher getTeacher() throws IOException, SQLException
    {
        Teacher t1 = manager.getTeacher(8);
        classList.addAll(manager.getTeacher(8).getAllClasses());
        System.out.println(manager.getTeacher(8).getAllClasses());
        t=t1;
        return t1;
    }
    public Student getStudent() throws IOException, SQLException
    {
    Student s1 = manager.getStudent(1); 
     s=s1;
     return s1;
    }
    public Arraylist<Attendance>  getAttendance( int studId)throws SQLServerException, IOException, SQLException{
       ArrayList aList = manager.getAttendance(1);
       return aList; 
    }
}
