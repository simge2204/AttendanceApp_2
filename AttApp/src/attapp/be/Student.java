package attapp.be;

import attapp.dal.AttendanceDbDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 *
 * @author Christian Occhionero
 */
public class Student extends Person
{

    private ObservableList<Attendance> fullAttendance;
     private StringProperty schoolClass;
    private double absencePercentage = 0;
    private DoubleProperty abPercentage;

    private AttendanceDbDAO dal; 
    
    public Student(String name, int id, String email, String schoolClass)
    {

        super(name, id, email);
        this.schoolClass = new SimpleStringProperty();
        abPercentage = new SimpleDoubleProperty();
        fullAttendance = FXCollections.observableArrayList();
        this.schoolClass.set(schoolClass);
        dal = new AttendanceDbDAO();
    }

   


    public void addAttendance() throws IOException, SQLException

    {
//        fullAttendance=(ObservableList<Attendance>) Dao.getAttendance(this.getId());
        fullAttendance = FXCollections.observableArrayList(dal.getAttendance(this.getId()));
        System.out.println("attendance laver lister "+dal.getAttendance(this.getId()));

        double totalDays = fullAttendance.size();
        double daysPresent = 0;

        for (Attendance x : fullAttendance)
        {
            if (x.getWasThere() == true)
            {
                daysPresent++;
            }
        }
        System.out.println("days present: " + daysPresent);
        System.out.println("totalDays: "+ totalDays);
        absencePercentage = 100 - daysPresent / totalDays * 100;
        abPercentage.set(absencePercentage);
    }

    public ObservableList<Attendance> getFullAttendance()
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

    @Override
    public String toString() {
        return "Student{" + "fullAttendance=" + fullAttendance + ", schoolClass=" + schoolClass + ", absencePercentage=" + absencePercentage + ", abPercentage=" + abPercentage + '}';
    }
    
    

}