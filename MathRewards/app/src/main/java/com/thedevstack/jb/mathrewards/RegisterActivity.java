package com.thedevstack.jb.mathrewards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class RegisterActivity extends Activity {

    /* Class Scope */

    /* EditText */
    private EditText nameID ,emailID, passwordID, confirmPasswordID;

    /* String */
    String name, email, password, confirmPassword;

    /* START ON CREATE METHOD */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /* Set field ID's */
        nameID = findViewById(R.id.name_field);
        emailID = findViewById(R.id.email_field);
        passwordID = findViewById(R.id.password_field);
        confirmPasswordID = findViewById(R.id.confirm_password_field);
    }
    /* END ON CREATE METHOD */

    /*
      fieldValidator
      Check fields are not empty
      Check password length
      Check password matches confirmPassword
    */
    public boolean validateFields() {

        /* Set string value from ID */
        name = nameID.getText().toString().trim();
        email = emailID.getText().toString().trim();
        password = passwordID.getText().toString().trim();
        confirmPassword = confirmPasswordID.getText().toString().trim();

        /* Validate name field is not empty */
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(RegisterActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();

            return true;
        } // End if

        /* Validate email field is not empty */
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(RegisterActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
            return true;
        } // End if

        /*
          Validate password field is not empty
          Check passwords length is 8 characters or more
        */
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(RegisterActivity.this, "Please Enter A Password", Toast.LENGTH_SHORT).show();
            return true;
        } else if (password.length() < 8) {
            Toast.makeText(RegisterActivity.this, "Password Must Be 8 Characters or Longer", Toast.LENGTH_SHORT).show();
            return true;
        } // End if

        /*
          Validate confirmPassword field is not empty
          Check password matches confirmPassword
        */
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Please Confirm Your Password", Toast.LENGTH_SHORT).show();
            return true;
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
            return true;
        } // End if

        Toast.makeText(RegisterActivity.this, "Thank You For Signing Up " + name + "!", Toast.LENGTH_SHORT).show();
        return false;
    } // End method

    /*
      validateFileds
      Call validateFields
     */
    public void registerUser(View view) {
        if (!validateFields()) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }  // End if
    } // End method
}