package attapp.be;

import attapp.dal.AttendanceDbDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Philip
 */
public class SchoolClass
{
private int id; 
private String name;
private ArrayList<Student> students;
private AttendanceDbDAO dal;

public SchoolClass(String name, int id) throws IOException, SQLException
{
    this.name=name;
    this.id=id;
    students = new ArrayList<>();
    dal = new AttendanceDbDAO(); 
    students = (ArrayList<Student>) dal.getAllStudents(id);
}


public void addStudent(Student x)
{
    students.add(x);
}

public ArrayList<Student> getAllStudents()
{
    return students;
}

  
            
        
        
        
@Override
public String toString()
{
    return name;
}

}
