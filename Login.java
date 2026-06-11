/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapppart1;

/**
 *
 * @author Admin
 */
public class Login {
    // Store user data
    String username;
    String password;
    String phonenumber;
     
     // Check if username is valid.
     public boolean checkUserName(String username) {
         return username.contains("_") && username.length() <= 5;    
     }
     // Check strength of password
     public boolean checkPasswordComplexity(String password) {
            boolean hasCapital = false;
            boolean hasNumber = false;
            boolean hasSpecial = false;
            
     // Loop through the password characters       
     for (int i = 0; i < password.length(); i++) {
          char c = password.charAt(i);
                
          if (Character.isUpperCase(c)) {
             hasCapital = true;
        }   else if (Character.isDigit(c)){
             hasNumber = true;
        } else if (!Character.isLetterOrDigit(c)) {
             hasSpecial = true;
              }    
        }        
           return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
     }
     
     // Check if Phone number is valid.
     public boolean checkCellphoneNumber(String phonenumber) {
         return phonenumber.startsWith("+27") && phonenumber.length() <= 12;
     }
     
     // Register User
     public String registerUser(String username, String password, String phonenumber) {
         if (!checkUserName(username)){
             return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
         }
         if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at    least eight characters, a capital letter, a number, and a special character.";
         }
         if (!checkCellphoneNumber(phonenumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
         }
         
     // Store User Details    
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        return "User registered successfully.";
        }

     // Login Check
     public boolean loginUser(String username, String password) {
         return this.username.equals(username) && this.password.equals(password);
        
     }   
     public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + username + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}




