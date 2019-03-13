/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.bll;

import java.util.Calendar;
import schoolapp.be.Attendance;
import schoolapp.be.Student;
import schoolapp.be.Teacher;
import schoolapp.dal.SchoolAppDAL;

/**
 *
 * @author Christian Occhionero
 */
public class SchoolAppManager
{

    private final SchoolAppDAL dal;

    public SchoolAppManager()
    {
        this.dal = new SchoolAppDAL();
    }
    
    public Student getStudent(int id)
    {
        return dal.getStudent(id);
    }
    

    public boolean checkForSchoolNetwork()
    {
        return dal.checkForSchoolNetWork();
               
    }

    public boolean checkForDailtyAttendance(Calendar date)
    {
       return dal.checkForDailtyAttendance(date);
    }

    public void askForAttendance(int id, Attendance chosenAttendance)
    {
        dal.askForAttendance(id, chosenAttendance);
    }

    public Teacher getTeacher()
    {
       return dal.getTeacher();
    }
}
