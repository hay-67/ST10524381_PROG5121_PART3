/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Admin
 */
public class LoginTest {
    // Create Login object to use its methods
    Login login = new Login();
    
    // Test valid username
    @Test
    public void testValidUsername() {
        // Check if correct username format reuturns true
        assertTrue(login.checkUserName("user_"));
    }
    
    // Test invalid username
    @Test
    public void testInvalidUserName() {
        // Check if wrong username format returns false
        assertFalse(login.checkUserName("user123"));
    }
    
    // Test valid password
    @Test
    public void testValidPassword() {
        // Check if strong password returns true
        assertTrue(login.checkPasswordComplexity("Pass123!"));
    }
    
    // Test inavlid password
    @Test
     public void testInvalidPassword() {
         // Check if weak password returns false
         assertFalse(login.checkPasswordComplexity("pass"));
     }
     
     // Test valid phone number
     @Test
    public void testValidPhoneNumber() {
        // Check if correct SA number returns true
        assertTrue(login.checkCellphoneNumber("+27831234567"));
    }
    
    // Test inavlid phone number
    @Test
     public void testInvalidPhoneNumberd() {
         // Check if wrong phone number returns false
         assertFalse(login.checkCellphoneNumber("0831234567"));
     }
     
     // Test successful login
      @Test
    public void testLoginSuccess() {
        // First register user
        login.registerUser("user_", "Pass123!", "+27831234567");
        // Check login returns true
        assertTrue(login.loginUser("user_", "Pass123!"));
    }
    
    // Test failed login
    @Test
     public void testLoginFailure() {
         // First register user
         login.registerUser("user_", "Pass123!", "+27831234567");
         
         // Check if logins returns false for wrong details
         assertFalse(login.loginUser("user_", "wrongpass"));
     }
     
     // Test login message
     @Test
       public void testLoginMessage() {
           
           // Register user first
           login.registerUser("user_", "Pass123!", "+27831234567");
           
         // Check correct message for successful login
         String message = login.returnLoginStatus(true);
    
         assertEquals("Welcome user_ it is great to see you again.", message);
     }
}
