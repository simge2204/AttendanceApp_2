package attapp.be;

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
public class Attendance {

    private Date dateo;
    private Calendar curDate;
    private BooleanProperty wasThere;
    private StringProperty attendance;
//    private Date date;

    private boolean requestAttendance;

    public Attendance(Calendar dt, boolean here) {
        wasThere = new SimpleBooleanProperty();
        attendance = new SimpleStringProperty();
//        date = new SimpleStringProperty();
        requestAttendance = false;

    }

    public Attendance(Date dateo, boolean wasThere) {

        this.wasThere = new SimpleBooleanProperty();

        this.wasThere.set(wasThere);

        this.dateo = dateo;
    }

    public String getAttendance() {
        if (wasThere.getValue() == true) {
            return ("True");
        } else {
            return ("False");
        }

    }

    public Calendar getCurDate() {
        return curDate;
    }

    public Boolean getWasThere() {
        return wasThere.get();
    }

    public String getDateo() {
        return dateo.toString();

    }

//    public String getAttendance()
//    {
//        return attendance.get();
//    }
//
    public void setAttendance(String insert) {
        attendance.set(insert);
    }

    public void setRequestAttendance(boolean x) {
        requestAttendance = x;
    }

    public boolean getRequestAttendance() {
        return requestAttendance;
    }

    @Override
    public String toString() {
        return "Attendance{" + "dateo=" + dateo + ", wasThere=" + wasThere + '}';
    }

}
