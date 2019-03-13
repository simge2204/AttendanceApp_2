package attapp.dal;

import java.util.ArrayList;
import java.util.Calendar;
import attapp.be.Attendance;
import attapp.be.SchoolClass;
import attapp.be.Student;
import attapp.be.Teacher;

/**
 *This class contains mockdata which is sent to the GUI
 */
public class SchoolAppDAL implements DAOInterface
{

    private Student s;
    private Student b;
    private Student c;

    private SchoolClass one;
    private SchoolClass two;
    private Teacher t;
    private ArrayList<SchoolClass> allClasses;

    public SchoolAppDAL()
    {
        one = new SchoolClass("Hold 1");
        two = new SchoolClass("Hold 2");
        allClasses = new ArrayList<>();

        // Setup mockup dates
        Calendar eigthfeb = Calendar.getInstance();

        eigthfeb.set(Calendar.SECOND, 0);
        eigthfeb.set(Calendar.MINUTE, 13);
        eigthfeb.set(Calendar.HOUR, 7);
        eigthfeb.set(Calendar.AM_PM, Calendar.AM);
        eigthfeb.set(Calendar.MONTH, Calendar.FEBRUARY);
        eigthfeb.set(Calendar.DAY_OF_MONTH, 8);
        eigthfeb.set(Calendar.YEAR, 2019);

        Calendar seventhfeb = Calendar.getInstance();

        seventhfeb.set(Calendar.SECOND, 0);
        seventhfeb.set(Calendar.MINUTE, 13);
        seventhfeb.set(Calendar.HOUR, 7);
        seventhfeb.set(Calendar.AM_PM, Calendar.AM);
        seventhfeb.set(Calendar.MONTH, Calendar.FEBRUARY);
        seventhfeb.set(Calendar.DAY_OF_MONTH, 7);
        seventhfeb.set(Calendar.YEAR, 2019);

        Calendar sixthfeb = Calendar.getInstance();

        sixthfeb.set(Calendar.SECOND, 0);
        sixthfeb.set(Calendar.MINUTE, 13);
        sixthfeb.set(Calendar.HOUR, 7);
        sixthfeb.set(Calendar.AM_PM, Calendar.AM);
        sixthfeb.set(Calendar.MONTH, Calendar.FEBRUARY);
        sixthfeb.set(Calendar.DAY_OF_MONTH, 6);
        sixthfeb.set(Calendar.YEAR, 2019);

        Calendar fifthfeb = Calendar.getInstance();

        fifthfeb.set(Calendar.SECOND, 0);
        fifthfeb.set(Calendar.MINUTE, 13);
        fifthfeb.set(Calendar.HOUR, 7);
        fifthfeb.set(Calendar.AM_PM, Calendar.AM);
        fifthfeb.set(Calendar.MONTH, Calendar.FEBRUARY);
        fifthfeb.set(Calendar.DAY_OF_MONTH, 5);
        fifthfeb.set(Calendar.YEAR, 2019);

        Calendar fourthfeb = Calendar.getInstance();

        fourthfeb.set(Calendar.SECOND, 0);
        fourthfeb.set(Calendar.MINUTE, 13);
        fourthfeb.set(Calendar.HOUR, 7);
        fourthfeb.set(Calendar.AM_PM, Calendar.AM);
        fourthfeb.set(Calendar.MONTH, Calendar.FEBRUARY);
        fourthfeb.set(Calendar.DAY_OF_MONTH, 4);
        fourthfeb.set(Calendar.YEAR, 2019);

        Calendar firstfeb = Calendar.getInstance();

        firstfeb.set(Calendar.SECOND, 0);
        firstfeb.set(Calendar.MINUTE, 13);
        firstfeb.set(Calendar.HOUR, 7);
        firstfeb.set(Calendar.AM_PM, Calendar.AM);
        firstfeb.set(Calendar.MONTH, Calendar.FEBRUARY);
        firstfeb.set(Calendar.DAY_OF_MONTH, 1);
        firstfeb.set(Calendar.YEAR, 2019);

        s = new Student("Wilhelm", 2, "wilhelm@msn.com", "Hold 1");
        // Adds 'em to the Student
        s.addAttendance(firstfeb, true);
        s.addAttendance(fourthfeb, true);
        s.addAttendance(fifthfeb, true);
        s.addAttendance(sixthfeb, false);
        s.addAttendance(seventhfeb, true);
        s.addAttendance(eigthfeb, true);

        c = new Student("Jens", 3, "jens@msn.com", "Hold 1");
        // Setup mockup dates

        // Adds 'em to the Student
        c.addAttendance(firstfeb, false);
        c.addAttendance(fourthfeb, false);
        c.addAttendance(fifthfeb, true);
        c.addAttendance(sixthfeb, true);
        c.addAttendance(seventhfeb, true);
        c.addAttendance(eigthfeb, true);

        b = new Student("Carl", 4, "carl@msn.com", "Hold 2");

        b.addAttendance(firstfeb, false);
        b.addAttendance(fourthfeb, true);
        b.addAttendance(fifthfeb, true);
        b.addAttendance(sixthfeb, true);
        b.addAttendance(seventhfeb, true);
        b.addAttendance(eigthfeb, true);

        one.addStudent(s);
        one.addStudent(c);

        two.addStudent(b);

        allClasses.add(one);
        allClasses.add(two);
        
        t = new Teacher("Hans Jensen", 12, "hjen@easv.dk");
        t.addClass(one);
        t.addClass(two);

    }

    @Override
    public Student getStudent(int id)
    {
        return s;
    }

    
    @Override
    public boolean checkForSchoolNetWork()
    {
        return true;
    }

    @Override
    public boolean checkForDailtyAttendance(Calendar date)
    {
        return false;
    }

    public void askForAttendance(int id, Attendance chosenAttendance)
    {
        
    }
@Override
    public Teacher getTeacher()
    {
  return t;
    }

}
