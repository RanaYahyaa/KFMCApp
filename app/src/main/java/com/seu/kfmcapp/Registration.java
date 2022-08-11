package com.seu.kfmcapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
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
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.RandomAccess;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registration extends AppCompatActivity {
    EditText firstNameedt, lastNameedt, Emailedt, Passwordedt, PasswordConfirmationedt, phoneedt;
    String userId;
    Button submitbtn, cancelbtn;
   /* FirebaseFirestore db;
    FirebaseAuth mAuth;*/
    RadioButton fradio, mradio;
    AlertDialog.Builder builder;
    String firstName, lastName,Password, phone, Email, PasswordConfirmation;
  /*   @Override
   public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //database
            /*db = FirebaseFirestore.getInstance();
            mAuth = FirebaseAuth.getInstance();*/
        //getting views
        firstNameedt = (EditText) findViewById(R.id.firstName);
        lastNameedt = (EditText) findViewById(R.id.lastName);
        Emailedt = (EditText) findViewById(R.id.Email);
        Passwordedt = (EditText) findViewById(R.id.password);
        PasswordConfirmationedt = (EditText) findViewById(R.id.passwordConfirmation);
        phoneedt = (EditText) findViewById(R.id.phone);
        submitbtn = (Button) findViewById(R.id.submit);
        cancelbtn = (Button) findViewById(R.id.cancel);
        fradio = (RadioButton) findViewById(R.id.female);
        mradio = (RadioButton) findViewById(R.id.male);
        initCancelButtonClick();

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPost();
                 firstName = firstNameedt.getText().toString().trim();
                 lastName = lastNameedt.getText().toString().trim();
                 Password = Passwordedt.getText().toString().trim();
                 PasswordConfirmation = PasswordConfirmationedt.getText().toString().trim();
                 Email = Emailedt.getText().toString().trim();
                 phone = phoneedt.getText().toString().trim();

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
                // check email is not duplicated
             /*  if(!isNewEmail(Email)){
                   Emailedt.setError(getResources().getString(R.string.AlreadyRegistered));
                   return;
               }*/
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

            }
            private void btnPost() {
                RetrofitAPI apiInterface = RetrofitBuilder.getRetrofitInstance().create(RetrofitAPI.class);
                Call<Intern> call = apiInterface.getInternInformation(firstName,lastName,Email, Password, phone,
                        fradio.isChecked());
                call.enqueue(new Callback<Intern>() {
                    @Override
                    public void onResponse(Call<Intern> call, Response<Intern> response) {
                        Log.e(TAG, "onResponse: "+response.code());
                        Log.e(TAG, "onResponse: First name:  "+response.body().getFirstName());
                        Log.e(TAG, "onResponse: Last name:  "+response.body().getLastName());
                        Log.e(TAG, "onResponse: Email:  "+response.body().getEmail());
                        Log.e(TAG, "onResponse: Password:  "+response.body().getPassword());
                        Log.e(TAG, "onResponse: Mobile:  "+response.body().getPhone());
                        Log.e(TAG, "onResponse: Gender:  "+response.body().getGender());
                    }

                    @Override
                    public void onFailure(Call<Intern> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage());
                    }
                });


            }


           /* private void btnPost() {
                ApiInterface apiInterface = RetrofitPost.getRetrofitInstance().create(ApiInterface.class);
                Call<User> call = apiInterface.getUserInformation(firstName, "Developer");
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.e(TAG, "onResponse: "+response.code());
                        Log.e(TAG, "onResponse: name:  "+response.body().getName());
                        Log.e(TAG, "onResponse: id:  "+response.body().getId());
                        Log.e(TAG, "onResponse: job:  "+response.body().getJob());
                        Log.e(TAG, "onResponse: createdAt:  "+response.body().getCreatedAt());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getMessage());

                    }
                });

            }*/
        });
    }
              /*  mAuth.createUserWithEmailAndPassword(Email, Password)
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

    }*/

    /*boolean isNew;
    private boolean isNewEmail(String email) {

        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                boolean isNewUser = task.getResult().getSignInMethods().isEmpty();

                if (isNewUser) {
                    Log.e("TAG", "Is New User!");
                    isNew =true;
                } else {
                    Log.e("TAG", "Is Old User!");
                    isNew =  false;
                }

            }
        });
return isNew;
    }*/


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

