package com.thedevstack.jb.mathrewards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

import static android.content.ContentValues.TAG;

public class RegisterActivity extends Activity {

    /* Class Scope */

    private FirebaseAuth mAuth;

    /* EditText */
    private EditText nameID ,emailID, passwordID, confirmPasswordID;

    /* String */
    String name, email, password, confirmPassword;

    /* START ON CREATE METHOD */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        /* Set field ID's */
        nameID = findViewById(R.id.name_field);
        emailID = findViewById(R.id.email_field);
        passwordID = findViewById(R.id.password_field);
        confirmPasswordID = findViewById(R.id.confirm_password_field);
    }
    /* END ON CREATE METHOD */

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null).
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

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
        if (name.isEmpty()) {
            nameID.setError("Name Is Required");
            nameID.requestFocus();
            return true;
        } // End if

        /* Validate email field is not empty */
        if (email.isEmpty()) {
            emailID.setError("Email Is Required");
            emailID.requestFocus();
            return true;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailID.setError("You Entered An Invalid Email Address");
            emailID.requestFocus();
            return true;
        }

        /*
          Validate password field is not empty
          Check passwords length is 8 characters or more
        */
        if (password.isEmpty()) {
            passwordID.setError("Password Is Required");
            passwordID.requestFocus();
            return true;
        } else if (password.length() < 8) {
            passwordID.setError("Password Length Must Be At Least 8 Characters");
            return true;
        }

        /*
          Validate confirmPassword field is not empty
          Check password matches confirmPassword
        */
        if (confirmPassword.isEmpty()) {
            confirmPasswordID.setError("Confirm Password Is Required");
            confirmPasswordID.requestFocus();
            return true;
        } else if (!password.equals(confirmPassword)) {
            confirmPasswordID.setError("Passwords Do Not Match");
            confirmPasswordID.requestFocus();
            return true;
        }

        Toast.makeText(RegisterActivity.this,
                "Welcome To MathRewards \n Thank You For Signing Up " + name + "!", Toast.LENGTH_SHORT).show();
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