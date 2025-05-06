package com.sp.utility;

public class Validations {

	//Email Validation
	public static boolean isValidEmail(String email) {
	        if (email == null) return false;
	        return email.contains("@") && email.endsWith(".com");
	    }

	//Password Validation
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (!Character.isLetterOrDigit(ch)) hasSpecial = true;

            // If all conditions met, break early
            if (hasUpper && hasLower && hasSpecial) return true;
        }

        return false;
    }

    //ContactNo Validation
    public static boolean isValidContact(String contact) {
        if (contact == null) return false;
        return contact.length() == 10 && contact.matches("\\d{10}");
    }

}
