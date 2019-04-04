package attapp.be;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 *
 * @author Christian Occhionero
 */
public class Student extends Person
{

    private ArrayList<Attendance> fullAttendance;
     private StringProperty schoolClass;
    private double absencePercentage = 0;
    private DoubleProperty abPercentage;
    private ArrayList<Attendance> attenlist;

    public Student(String name, int id, String email, String schoolClass)
    {

        super(name, id, email);
        this.schoolClass = new SimpleStringProperty();
        abPercentage = new SimpleDoubleProperty();
        fullAttendance = new ArrayList<>();
        this.schoolClass.set(schoolClass);
    }

 

    public void addAttendance(Calendar theDate, boolean wasThere) // needs fix 
    {
        fullAttendance.add(new Attendance(theDate, wasThere));

        double totalDays = fullAttendance.size();
        double daysPresent = 0;

        for (Attendance x : fullAttendance)
        {
            if (x.getWasThere().booleanValue() == true)
            {
                daysPresent++;
            }
        }

        absencePercentage = 100 - daysPresent / totalDays * 100;
        abPercentage.set(absencePercentage);
    }

    public ArrayList<Attendance> getFullAttendance()
    {
        return fullAttendance;
    }

    public Double getAbsencePercentage()
    {

        return absencePercentage;
    }

    public String getSchoolClass()
    {
        return schoolClass.get();
    }

    public double getAbPercentage() throws ParseException
    {
        String oneDecimal = String.format("%.1f", abPercentage.get());
        NumberFormat nf = NumberFormat.getInstance();

        double per = nf.parse(oneDecimal).doubleValue();
        return per;
    }

    public ArrayList<Integer> getMostAbsentDay()
    {
        int monday = 0;
        int tuesday = 0;
        int wedensday = 0;
        int thursday = 0;
        int friday = 0;

        ArrayList<Integer> allDays = new ArrayList<>();

        for (Attendance x : fullAttendance)
        {
            if (x.getWasThere() == false)
            {
                int day = x.getCurDate().get(Calendar.DAY_OF_WEEK);
                switch (day)
                {

                    case 2:
                        monday++;
                        break;
                    case 3:
                        tuesday++;
                        break;
                    case 4:
                        wedensday++;
                        break;
                    case 5:
                        thursday++;
                        break;
                    case 6:
                        friday++;
                        break;

                }
            }
        }
        
        allDays.add(monday);
        allDays.add(tuesday);
        allDays.add(wedensday);
        allDays.add(thursday);
        allDays.add(friday);

        return allDays;
    }

}