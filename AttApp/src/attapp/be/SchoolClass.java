package attapp.be;

import java.util.ArrayList;

/**
 *
 * @author Philip
 */
public class SchoolClass
{
private String name;
private ArrayList<Student> students;

public SchoolClass(String name)
{
    this.name=name;
    students = new ArrayList<>();
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
