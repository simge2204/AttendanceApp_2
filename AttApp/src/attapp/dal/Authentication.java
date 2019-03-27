package attapp.dal;

/**
 *
 * @author Philip
 */
public class Authentication
{
    
public static boolean validateStudentLogin(String sLogin, String password)
{
    if(sLogin.equals("Wilhelm Hansen") && password.equals("elev"))
            {
                return true;
            }
    
    
    return false;
}

public static boolean validateTeacherLogin(String tLogin, String password)
{
    if(tLogin.equals("Hans Jensen") && password.equals("lære"))
    {
        return true;
    }
    
    return false;
}

    
}
