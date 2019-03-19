/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attapp.dal;

import attapp.be.Attendance;
import attapp.be.Student;
import attapp.be.Teacher;
import java.util.Calendar;

/**
 *
 * @author Christian Occhionero
 */
public class TeacherDbDAO implements DAOInterface {

    @Override
    public boolean checkForDailtyAttendance(Calendar date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkForSchoolNetWork() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void askForAttendance(int id, Attendance chosenAttendance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Teacher getTeacher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
