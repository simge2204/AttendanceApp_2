package attapp.be;

import java.util.ArrayList;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Christian Occhionero
 */
public class Person
{
    private StringProperty name; 
   
    int id; 
    private StringProperty email;
    
       public Person (String name, int id, String email)
    {
        this.email = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.name.set(name);
        this.id = id;
        this.email.set(email);
    }

    public String getName()
    {
        return name.getValue();
    }

    public int getId()
    {
        return id;
    }
    public String getEmail()
    {
        return email.get();
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", id=" + id + ", email=" + email + '}';
    }
    
    
}
