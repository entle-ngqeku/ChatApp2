/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.chatapp2.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    
    Login login = new Login();

    //-------------------------
    // USERNAME TESTS
    //-------------------------

    @Test
    public void testValidUsername() {
        assertTrue(login.checkUsername("ky_1"));
    }

    @Test
    public void testInvalidUsername_NoUnderscore() {
        assertFalse(login.checkUsername("kyle!!!!!11"));
    }

    @Test
    public void testInvalidUsername_TooLong() {
        assertFalse(login.checkUsername("ky1_12345")); // more than 5 chars
    }

    //-------------------------
    // PASSWORD TESTS
    //-------------------------

    @Test
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99"));
    }

    @Test
    public void testInvalidPassword_NoCapital() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testInvalidPassword_NoNumber() {
        assertFalse(login.checkPasswordComplexity("Password"));
    }

    @Test
    public void testInvalidPassword_NoSpecialChar() {
        assertFalse(login.checkPasswordComplexity("Password123"));
    }

    @Test
    public void testInvalidPassword_TooShort() {
        assertFalse(login.checkPasswordComplexity("Passd"));
    }

    //-------------------------
    // PHONE NUMBER TESTS
    //-------------------------

    @Test
    public void testValidPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber("+27837719808"));
    }

    @Test
    public void testInvalidPhoneNumber_WrongFormat() {
        assertFalse(login.checkCellPhoneNumber("0837719808"));
    }

    //-------------------------
    // REGISTRATION TESTS
    //-------------------------

   

    //-------------------------
    // Login Successful
    //-------------------------

    @Test
    public void testSuccessfulLogin() {
        login.registerUser("ky1_1", "CH&&sec@ke99!", "+27837719808");
        assertTrue(login.loginUser("ky1_1", "CH&&sec@ke99!"));  // Fixed username from "kyl_1" to "ky1_1"
    }

    
    //----------------------------
    // Login Failed
    //----------------------------
    @Test
    public void testFailedLogin() {
        login.registerUser("ky1_1", "CH&&sec@ke99!", "+27837719808");
        assertFalse(login.loginUser("ky1_1", "kyle!!!!!"));  // Correct password is "CH&&sec@ke99!", so this will fail
    }
}
