package com.seu.kfmcapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    EditText firstNameedt, lastNameedt, Emailedt, Passwordedt, PasswordConfirmationedt;
    String userId;
    Button submitbtn, cancelbtn;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    RadioButton fradio, mradio;
    AlertDialog.Builder builder;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //database
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //getting views
        firstNameedt = (EditText) findViewById(R.id.firstName);
        lastNameedt = (EditText) findViewById(R.id.LastName);
        Emailedt = (EditText) findViewById(R.id.Email);
        Passwordedt = (EditText) findViewById(R.id.password);
        PasswordConfirmationedt = (EditText) findViewById(R.id.passwordConfirmation);
        submitbtn = (Button) findViewById(R.id.submit);
        cancelbtn = (Button) findViewById(R.id.cancel);
        fradio = (RadioButton) findViewById(R.id.female);
        mradio = (RadioButton) findViewById(R.id.male);
        initCancelButtonClick();

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameedt.getText().toString().trim();
                String lastName = lastNameedt.getText().toString().trim();
                String Password = Passwordedt.getText().toString().trim();
                String PasswordConfirmation = PasswordConfirmationedt.getText().toString().trim();
                String Email = Emailedt.getText().toString().trim();

                // check first name is not empty
                if (TextUtils.isEmpty(firstName)) {
                    firstNameedt.setError(getResources().getString(R.string.RequiredFeild));
                    return;
                }
                // check first name length
                if (firstName.length() < 3) {
                    firstNameedt.setError(getResources().getString(R.string.NameLength));
                    return;
                }

                // check last name is not empty
                if (TextUtils.isEmpty(lastName)) {
                    lastNameedt.setError(getResources().getString(R.string.RequiredFeild));
                    return;
                }
                // check last name length
                if (lastName.length() < 3) {
                    lastNameedt.setError(getResources().getString(R.string.NameLength));
                    return;
                }

                // check password is not empty
                if (TextUtils.isEmpty(Password)) {
                    Passwordedt.setError(getResources().getString(R.string.RequiredFeild));
                    return;
                }
                // check password length
                if (Password.length() < 8) {
                    Passwordedt.setError(getResources().getString(R.string.PasswordLength));
                    return;
                }

                // check email is not empty
                if (TextUtils.isEmpty(Email)) {
                    Emailedt.setError(getResources().getString(R.string.RequiredFeild));
                    return;
                }
                // check password confirmation
                if (TextUtils.isEmpty(PasswordConfirmation)) {
                    PasswordConfirmationedt.setError(getResources().getString(R.string.RequiredFeild));
                    return;
                }

                // check password confirmation
                if (!Password.equals(PasswordConfirmation)) {
                    PasswordConfirmationedt.setError(getResources().getString(R.string.NotMatch));
                    return;
                }


                mAuth.createUserWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(Registration.this, R.string.VerificationEmail, Toast.LENGTH_LONG).show();
                                            //move to log in
                                            Intent intent = new Intent(Registration.this, LogIn.class);
                                            startActivity(intent);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d(TAG, "OnFailure: Email Not Sent");
                                        }
                                    });

                                    userId = mAuth.getCurrentUser().getUid();
                                }

                                // Create a new user
                                Map<String, Object> t = new HashMap<>();
                                t.put("ID", userId);
                                t.put("firstName", firstName);
                                t.put("lastName", lastName);
                                t.put("Email", Email);
                                t.put("Password", Password);
                                t.put("Gender", fradio.isChecked());

                                // Add a new document
                                db.collection("Profiles")
                                        .add(t)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {

                                                // Log.d(TAG,"OnSuccess: user profile is created for"+userId);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //  Log.d(TAG,"OnFailure "+ e.toString());
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registration.this, R.string.RegisterationError, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    private void initCancelButtonClick() {
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        builder = new AlertDialog.Builder(Registration.this);
        // Set the message and title
        builder.setMessage(R.string.deleteConfirm) .setTitle(R.string.deleteConfirmTitle);

        //Setting message manually and performing action on button click
        builder.setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // delete fields
                        firstNameedt.setText("");
                        lastNameedt.setText("");
                        Emailedt.setText("");
                        Passwordedt.setText("");
                        PasswordConfirmationedt.setText("");
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        alert.show();
            }
        });

    }
    }

