package edu.fsu.cs.mobile.hw2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class LinearLayoutActivity extends AppCompatActivity {

    private EditText empIdField;
    private EditText nameField;
    private EditText emailField;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private RadioGroup radioGroup;
    private EditText accessCode;
    private EditText confirmCode;
    private Spinner departmentSpinner;
    private CheckBox terms;
    private Button reset;
    private Button register;
    private String[] empIDArray;
    private Toast intro;
    private Toast success;
    private Toast incomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear);

        intro = Toast.makeText(this, "Please complete the registration form", Toast.LENGTH_SHORT );
        success = Toast.makeText(this, "Thank you for registering!", Toast.LENGTH_SHORT );
        incomplete = Toast.makeText(this, "The form is incomplete. Please reference details above", Toast.LENGTH_SHORT );


        empIdField = findViewById(R.id.empIDEditText);
        empIDArray = getResources().getStringArray(R.array.empids);
        nameField = findViewById(R.id.nameEditText);
        emailField = findViewById(R.id.emailEditText);
        maleButton = findViewById(R.id.radioMale);
        femaleButton = findViewById(R.id.radioFemale);
        radioGroup = findViewById(R.id.radioGroup);
        accessCode = findViewById(R.id.accessCodeEditText);
        confirmCode = findViewById(R.id.confirmCodeEditText);
        departmentSpinner = findViewById(R.id.departmentSpin);
        terms = findViewById(R.id.agreeCheck);
        reset = findViewById(R.id.button_reset);
        register = findViewById(R.id.button_register);

        intro.show();
        reset.setOnClickListener(buttonListener);
        register.setOnClickListener(buttonListener);

    }

    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button_reset:
                    resetFields();
                    break;
                case R.id.button_register:
                    if(validator()){
                        success.show();
                        resetFields();
                    }
                    else{
                        incomplete.show();
                    }
                    break;
            }

        }
    };


    //____________________check functions___________________//

    //check that editText fields are populated.
    private boolean editTextPopulated(EditText editText){
        if(!TextUtils.isEmpty(editText.getText())){
            return true;
        }
        return false;
    }

    //checker to cross reference empIDs with the array provided
    private boolean empIDConfirm(){
        if(TextUtils.isEmpty(empIdField.getText())){
            empIdField.setError("Enter your employee ID");
            return false;
        }

        for(String str: empIDArray){
            if(TextUtils.equals(empIdField.getText(), str)){
                return true;
            }
        }

        empIdField.setError("Employee ID was not found please try again");
        return false;
    }

    private boolean nameChecker(){
        if(editTextPopulated(nameField)){
            return true;
        }

        nameField.setError("Name not valid!");
        return false;
    }

    //check 1) email is not empty
    //check 2) utilize a regex expression to verify that the email is in the proper format.
    private boolean emailChecker(){
        if(editTextPopulated(emailField)){
            if(Patterns.EMAIL_ADDRESS.matcher(emailField.getText()).matches()){
                return true;
            }
        }
        emailField.setError("Email is not in the proper format!");
        return false;
    }

    //compare the passwords
    private boolean pwMatch(){
        if(editTextPopulated(confirmCode) && editTextPopulated(accessCode))
            if(TextUtils.equals(confirmCode.getText(), accessCode.getText())){
                return true;
            }

        confirmCode.setError("The \"access code\" does not match  \"confirm code\" ");
        return false;
    }

    private boolean radioMarked(){
        if(maleButton.isChecked() || femaleButton.isChecked()){
            return true;
        }

        femaleButton.setError("Please select a gender");
        return false;
    }

    private boolean agreeCheck(){
        if(terms.isChecked()){
            return true;
        }

        terms.setError("Please read and check the terms to confirm registration");
        return false;
    }

    //_________________Non-Checker functions_____________//
    private void resetFields(){
        empIdField.setText("");
        nameField.setText("");
        emailField.setText("");
        maleButton.setChecked(false);
        femaleButton.setChecked(false);
        accessCode.setText("");
        confirmCode.setText("");
        terms.setChecked(false);
        resetErrors();
    }

    private void resetErrors(){
        empIdField.setError(null);
        nameField.setError(null);
        emailField.setError(null);
        femaleButton.setError(null);
        accessCode.setError(null);
        confirmCode.setError(null);
        terms.setError(null);
    }

    private boolean validator(){

        boolean flag = true;

        if (!empIDConfirm()) {
            flag = false;
        }

        if (!nameChecker()) {
            flag = false;
        }
        if (!emailChecker()) {
            flag = false;
        }
        if (!radioMarked()) {
            flag = false;
        }
        if (!pwMatch()) {
            flag = false;
        }
        if (!agreeCheck()) {
            flag = false;
        }

       if(flag == true){
            return true;
       } else{
            return false;
       }
    }


}

