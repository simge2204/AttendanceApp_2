package attapp.dal;

import java.util.Calendar;
import attapp.be.Attendance;
import attapp.be.Student;
import attapp.be.Teacher;

/**
 *
 * @author Philip
 */
public interface DAOInterface
{

    /**
     * Returns false if the user hasn't registered his/hers attendance
     *
     * @param date
     * @return
     */
    boolean checkForDailtyAttendance(Calendar date);

    /**
     * Returns true if the user is on the school network
     *
     * @return
     */

    boolean checkForSchoolNetWork();


    /**
     * Returns the student with the given ID
     *
     * @param id
     * @return
     */
    Student getStudent(int id);
    /**
     * Asks for a change of attendance
     * @param id
     * @param chosenAttendance 
     */
    void askForAttendance(int id, Attendance chosenAttendance);
    
     Teacher getTeacher();

}