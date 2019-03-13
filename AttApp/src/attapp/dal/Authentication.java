/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.dal;

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

public static boolean validateTeacherLogin(String tLogin, String password)
{
    if(tLogin.equals("Hans Jensen") && password.equals("xxcc"))
    {
        return true;
    }
    
    return false;
}

    
}
