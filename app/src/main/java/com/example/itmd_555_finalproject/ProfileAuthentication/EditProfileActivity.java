package com.example.itmd_555_finalproject.ProfileAuthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itmd_555_finalproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText editFullName, editEmailAddress, editUsername, editPassword;
    Button saveButton;
    String fullNameUser, emailAddressUser, usernameUser, passwordUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editFullName = findViewById(R.id.editFullName);
        editEmailAddress = findViewById(R.id.editEmailAddress);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        saveButton = findViewById(R.id.saveButton);

        showData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFullNameModified() || isPasswordModified() || isEmailAddressModified()) {
                    Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isFullNameModified() {
        if (!fullNameUser.equals(editFullName.getText().toString())) {
            reference.child(usernameUser).child("full_name").setValue(editFullName.getText().toString());
            fullNameUser = editFullName.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmailAddressModified() {
        if (!emailAddressUser.equals(editEmailAddress.getText().toString())) {
            reference.child(usernameUser).child("email_address").setValue(editEmailAddress.getText().toString());
            emailAddressUser = editEmailAddress.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean isPasswordModified() {
        if (!passwordUser.equals(editPassword.getText().toString())) {
            reference.child(usernameUser).child("password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void showData() {

        Intent intent = getIntent();

        // Changed variable names below
        fullNameUser = intent.getStringExtra("full_name");
        emailAddressUser = intent.getStringExtra("email_address");
        usernameUser = intent.getStringExtra("username");
        passwordUser = intent.getStringExtra("password");

        editFullName.setText(fullNameUser);
        editEmailAddress.setText(emailAddressUser);
        editUsername.setText(usernameUser);
        editPassword.setText(passwordUser);
    }
}
