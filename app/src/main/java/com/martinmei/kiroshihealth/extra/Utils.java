package com.martinmei.kiroshihealth.extra;

import android.widget.EditText;

public class Utils {

    public static boolean validateDNI(EditText et){
        String dni = et.getText().toString().toUpperCase();
        String cadenaNumeros = dni.substring(0, 8);
        if (cadenaNumeros.matches("^[0-9]{8}$") && dni.substring(8, 9).matches(
                "^['T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E']{1}$")) {
            String cadena = "TRWAGMYFPDXBNJZSQVHLCKE";
            int numeros = Integer.parseInt(cadenaNumeros);
            int pos = numeros % 23;
            if (dni.charAt(8) == cadena.charAt(pos)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validatePhone(EditText et){
        return et.getText().toString().matches("^[6-9]{1}[0-9]{8}$");
    }

    public static boolean isEmpty(EditText et){
        return et.getText().toString().isEmpty();
    }

}
