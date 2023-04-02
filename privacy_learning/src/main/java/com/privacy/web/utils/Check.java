package com.privacy.web.utils;


public class Check {

    /**
     * Questo metodo controlla se il parametro email rispetta il giusto formato
     *
     * @param email da controllare
     * @return boolean (true = rispettato , false = altrimenti)
     */

    public static boolean checkEmail(String email) {
        if (email == null) {
            return false;
        }
        String regular = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        return email.matches(regular);
    }

    /**
     * Questo metodo controlla se il parametro password rispetta il giusto formato
     *
     * @param pwd password
     * @return boolean (true = rispettato , false = altrimenti)
     */

    public static boolean checkPwd(String pwd) {
        if (pwd == null) {
            return false;
        }
        //String regular = "^.*(?=.*[a-z A-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$";
        String regular = "^[A-Za-z0-9]{3,16}$";
        return pwd.matches(regular);
    }

    /**
     * Questo metodo controlla se il parametro nome rispetta il giusto formato
     *
     * @param name nome di un utente
     * @return boolean (true = rispettato , false = altrimenti)*/

    public static boolean checkName(String name) {
        if (name == null) {
            return false;
        }
        String regular = "^[A-Za-z]{2,32}$";
        return name.matches(regular);
    }

    /**
     * Questo metodo controlla se il parametro cognome rispetta il giusto formato
     *
     * @param surname , cognome dell'utente
     * @return boolean (true = rispettato , false = altrimenti)*/

    public static boolean checkSurname(String surname) {
        if (surname == null) {
            return false;
        }
        String regular = "^[A-Za-z]{2,32}$";
        return surname.matches(regular);
    }
}