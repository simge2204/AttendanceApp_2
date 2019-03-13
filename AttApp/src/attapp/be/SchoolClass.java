/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.be;

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
