package com.martinmei.kiroshihealth.extra;

import android.widget.EditText;

public class Utils {

    public static boolean validateDNI(EditText et){

        return et.getText().toString().toUpperCase().matches("^[0-9]{8}[A-Z]{1}$");
    }

    public static boolean validatePhone(EditText et){

        return et.getText().toString().matches("^[6-9]{1}[0-9]{8}$");
    }

    public static boolean isEmpty(EditText et){

        return et.getText().toString().isEmpty();
    }

}
