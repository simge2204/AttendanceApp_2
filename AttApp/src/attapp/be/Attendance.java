/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.be;

import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Philip
 */
public class Attendance
{

    private Calendar curDate;
    private BooleanProperty wasThere;
    private StringProperty attendance;
    private StringProperty date;
    private boolean requestAttendance;

    public Attendance(Calendar dt, boolean here)
    {
        wasThere = new SimpleBooleanProperty();
        attendance = new SimpleStringProperty();
        date = new SimpleStringProperty();
        requestAttendance=false;
 
        this.curDate = dt;
        wasThere.setValue(here);
        if(here==true)
        {
            attendance.set("Godkendt");
        }
        else
        {
            attendance.set("Fravær");
        }
        
        // set the time with date and year
        int day = curDate.get(Calendar.DAY_OF_MONTH);
        int month = curDate.get(Calendar.MONTH);
         month++;
        int year = curDate.get(Calendar.YEAR);
        date.set(""+day+"/"+month+"-"+year);
    }

    public Calendar getCurDate()
    {
        return curDate;
    }

    public Boolean getWasThere()
    {
        return wasThere.get();
    }

    public String getDate()
    {
        return date.get();
    }

    public String getAttendance()
    {
        return attendance.get();
    }
    
    
   public void setAttendance(String insert)
   {
       attendance.set(insert);
   }
   
   public void setRequestAttendance(boolean x)
   {
       requestAttendance=x;
   }
   
   public boolean getRequestAttendance()
   {
       return requestAttendance;
   }
}
         
    
    







