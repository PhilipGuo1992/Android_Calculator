package com.example.phili.assignment_calculator;

import android.util.Log;

/**
 * Created by phili on 2018-02-04.
 */

public class operatorUtil {

    private static final String TAG = "testing";


    private static double calculatedResult;

    // check which operator is choosed
    public static String doOperation(double a, double b, char operator){

        if(operator == '+'){
            // call the addition method
            calculatedResult = doAddition(a, b);
            Log.i(TAG, "the addition is working" + calculatedResult);

        }
        if(operator == '-'){
            calculatedResult = doSubtraction(a, b);
        }
        if(operator == '*'){
            calculatedResult = doMutipilation(a, b);
            Log.i(TAG, "the multipicaion is working " + calculatedResult);


        }
        // calculate squre root

        if(operator == '\u00F7'){
            //check if b is zero
            Log.i(TAG, "the division is working");

            if(b == 0){
                return "Error";
            }

            calculatedResult = doDivision(a , b);

        }

        String checkResult = calculatedResult + "";
        Log.i(TAG, "the multipicaion is changed to string :" + checkResult);

        // check if result length is more than 10
        // maximum ten digits
        if(checkResult.length() > 10 && (!checkResult.contains("E"))){

            Log.i(TAG, "the dividison is changed to string :" + checkResult);
            Log.i(TAG, "the dividison is changed to string :" + checkResult.contains("E"));

            checkResult = checkResult.substring(0,10);
        }


        if(checkResult.length()>10 && checkResult.contains("E")){
            // for example:  4.938364839408E12
            // for example:  5.92527408E8
            Log.i(TAG, "the result with more than 10 digits : " + checkResult);
            int e = checkResult.indexOf("E");
            int theTail = checkResult.length() - e;
            int theHead = 10 - theTail;

            checkResult = checkResult.substring(0, theHead) + checkResult.substring(e, checkResult.length());

        }
        // check if the output is integer; not a double
        if(checkResult.endsWith(".0")){
            checkResult = checkResult.substring(0, checkResult.length()-2);
        }

        return checkResult;
    }

    // addition
    private static double doAddition(double a, double b){


        return a + b;
    }

    // subtraction
    private static double doSubtraction(double a, double b){


        return a - b;
    }

    //division
    private static double doDivision(double a, double b){

        return a / b;
    }
    //multiple
    private static double doMutipilation(double a, double b){

        return a * b;
    }

}
