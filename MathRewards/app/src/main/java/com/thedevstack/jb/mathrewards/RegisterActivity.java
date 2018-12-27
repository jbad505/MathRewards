package com.thedevstack.jb.mathrewards;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class RegisterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }
    // TODO: Add Comments To Class CredentialValidation
    class CredentialValidation {

        EditText nameField = findViewById(R.id.name_field);
        EditText emailField = findViewById(R.id.email_field);
        EditText passwordField = findViewById(R.id.password_field);
        EditText confirmPasswordField = findViewById(R.id.confirm_password_field);

        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String confirmPassword = confirmPasswordField.getText().toString();

        private boolean validateFields() {

            return TextUtils.isEmpty(name) || TextUtils.isEmpty(email)
                    || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword);
        }

        private boolean doPasswordsMatch() {
            if (password.equals(confirmPassword)) {

                Toast.makeText(RegisterActivity.this, "DO NOT MATCH", Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        }

        private boolean isPasswordLongEnough() {
            if (password.length() >= 8) {

                Toast.makeText(RegisterActivity.this, "PASSWORD MUST BE A MINIMUM OF 8 CHARACTERS", Toast.LENGTH_LONG).show();
                return true;
            } else {
                Toast.makeText(RegisterActivity.this, "SUBMITTED", Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }

    public void run(View view) {
        CredentialValidation credentialValidation = new CredentialValidation();

        boolean allFields = credentialValidation.validateFields();
        if (!allFields) {
            boolean matchPasswords = credentialValidation.doPasswordsMatch();
            if (matchPasswords) {
                boolean passwordLongEnough = credentialValidation.isPasswordLongEnough();
                if (passwordLongEnough) {
                    Toast.makeText(RegisterActivity.this, "Hey it worked!!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Password not long enough", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(RegisterActivity.this, "All fields required", Toast.LENGTH_LONG).show();
        }
    }
}