package com.martinmei.kiroshihealth;

public class Utils {

    public static boolean validarDNI(String dni){

        return dni.matches("^[0-9]{8}[A-Z]{1}$");
    }

    public static boolean validarTelefono(String telef){

        return telef.matches("^[6-9]{1}[0-9]{8}$");
    }


}
