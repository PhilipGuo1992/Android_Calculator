package com.example.phili.assignment_calculator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    // log
    private static final String TAG = "testing";

    // the input number

    // first input
    private double firstNum;
    // second input
    private double secondNum;

    // operator click second time
    private boolean operatorClickSecondTime = false;

    // check if operator clicked several times without click number
    private boolean numberIsClicked = false;

    private String storedNumber = "";
    // change button color
    private Button clickedBtn;
    //  buttons of number
    private Button numButton;
    // button of operators
    private Button operatorBtn;
    // check if
    private boolean operator_is_clicked = false;

    private int index = 0;

    // choosed operator
    private char firstOperator;
    private char secondOperator;

    //output results
    private TextView textOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get widges of each view
        // get output
        textOutput = findViewById(R.id.output);

        // test
        Log.i(TAG, "the default of first num is: " + secondOperator);

    }

    /*
     this is the onClick listener for all the numbers
     */
    public void clickNum(View view) {
        // to store number temperially
        char tempNumber;

        // get the clicked button
        numButton = (Button) view;
        tempNumber = numButton.getText().charAt(0);

        if (operator_is_clicked) {
            // get first number from array
            Log.i(TAG, "it goes to here");
            firstNum = Double.parseDouble(storedNumber);
            // reset the screen
            // updateScreen(storedNumber);
            // reset the string to empty
            storedNumber = "";

            storedNumber += tempNumber;
            updateScreen(storedNumber);


            operator_is_clicked = false;

            // check if the length is more than 10
        } else if (textOutput.length() < 10) {
            // avoid mistake: if user clicks twice decimal button
            if (tempNumber == '.' && storedNumber.contains(".")) {
                return;
            }
            // store num to global
            storedNumber += tempNumber;
            updateScreen(storedNumber);
        }
        // reset
        numberIsClicked = true;

    }

    /*
    when user click any operator
     */
    @SuppressLint("ResourceAsColor")
    public void clickOperator(View view) {
        // how to highlight the choosed operator
        // get the clicked button

        // if user did not choose number, and click operator first: return nothing
        if (storedNumber.equals("")) {
            Log.i(TAG, "the default stored number is : " + storedNumber);
            return;
        }


        operatorBtn = (Button) view;
        //
        if (clickedBtn == null) {

            operatorBtn.setBackgroundColor(Color.MAGENTA);

            clickedBtn = operatorBtn;

            Log.i(TAG, "the color one  : " + clickedBtn);


        } else {
            //clickedBtn.setBac
            clickedBtn.setBackgroundColor(Color.CYAN);
            Log.i(TAG, "the color two : " + clickedBtn);

            clickedBtn = operatorBtn;

            clickedBtn.setBackgroundColor(Color.MAGENTA);

        }


        // which operator is clicked
        char operator = operatorBtn.getText().charAt(0);

        // check if it has the situation user input like this: 3 + 4 = 3 - 4
        if (firstOperator == '=') {
            firstOperator = operator;
            Log.i(TAG, "the equal operator is : " + operator);
            operator_is_clicked = true;
            return;
        }

        operator_is_clicked = true;

        if (!numberIsClicked) {
            // replace the previous operator, and return
            // for example: if user click + - + , he will get + at end.
            firstOperator = operator;
            return;
        }


        index = index + 1;

        if (index == 1) {
            firstOperator = operator;
            Log.i(TAG, "the default of first num is: " + firstOperator);
        }


        if (index == 2) {

            operatorClickSecondTime = true;


            secondOperator = operator;

            //
            index = index - 1;
        }
        if (operatorClickSecondTime) {
            // get second input number
            Log.i(TAG, storedNumber);
            secondNum = Double.parseDouble(storedNumber);

            // check the second operator
            storedNumber = operatorUtil.doOperation(firstNum, secondNum, firstOperator);


            //update operator
            firstOperator = secondOperator;

            // check if the operator is equal
            //if(firstOperator == '=')

            updateScreen(storedNumber);

        }

        // reset
        numberIsClicked = false;

    }

    /*
    update the screen
     */
    private void updateScreen(String s) {
        textOutput.setText(s);

    }

    /*
     the all clear button AC
     */
    public void clickAllClear(View view) {
        // reset everything
        firstNum = 0;
        secondNum = 0;
        firstOperator = ' ';
        secondOperator = ' ';
        index = 0;
        operatorClickSecondTime = false;
        numberIsClicked = false;
        storedNumber = "";
        operator_is_clicked = false;

        // reset the screen
        updateScreen("0");
    }

    // clear: C
    public void clearCurrentOperand(View view) {
        //reset
        storedNumber = "";
        updateScreen("0");
    }

    // clickSquareRoot
    public void clickSquareRoot(View view) {
        // get the number on the screen
        TextView currentText = findViewById(R.id.output);

        Log.i(TAG, "the square root is working " + currentText.getText().toString());

        if (currentText.getText().toString().contains("-")) {
            Log.i(TAG, "to string " + currentText.getText().toString());

            updateScreen("Error");
            return;
        }

        double currentValue = Double.parseDouble(currentText.getText().toString());
        if (currentValue > 0) {
            double d = Math.sqrt(currentValue);

            String result = d + "";
            if (result.endsWith(".0")) {
                result = result.substring(0, result.length() - 2);
            }
            // maximum ten digits
            if (result.length() > 10) {
                result = result.substring(0, 10);
            }

            updateScreen(result);
        }
    }

    /*
     if user click toggle button
     */
    public void clickToggleBtn(View view) {
        // just change the storedNumber
        if (storedNumber.contains("-")) {
            storedNumber = storedNumber.substring(1, storedNumber.length());
            updateScreen(storedNumber);

        } else {
            storedNumber = "-" + storedNumber;
            updateScreen(storedNumber);

        }
    }

}