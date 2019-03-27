package attapp.dal;

import attapp.be.Student;
import attapp.be.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Philip
 */
public class Authentication
{


public static boolean validateStudentLogin(String sLogin, String password)
{
    if(sLogin.equals("Wilhelm Hansen") && password.equals("xsdc"))
            {
                return true;
            }


    return false;
}

public static boolean validateTeacherLogin(String tLogin, String password) throws IOException, SQLServerException, SQLException
{
    if(tLogin.equals("Hans Jensen") && password.equals("lære"))

    {
        return true;
    }

    return false;
}

        public Student loginStudent(String email, String password) throws SQLServerException, IOException, SQLException
        {
        DbConnection dc = new DbConnection();
        try (Connection con = dc.getConnection(); PreparedStatement pstmt = con.prepareStatement("Select 'Email_stud', 'password' FROM LoginStud WHERE Email_stud = (?) AND password = (?)");)
        {
            Student loginStud = null;
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.execute();

            return loginStud;
        }

    }
}
