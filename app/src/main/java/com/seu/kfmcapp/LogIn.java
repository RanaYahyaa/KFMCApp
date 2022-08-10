package com.seu.kfmcapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LogIn extends AppCompatActivity {
//    FirebaseFirestore db;
//    FirebaseAuth mAuth;
    TextView signUptxt;
    EditText emailtxt, passwordtxt;
    Button logInbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
//        db = FirebaseFirestore.getInstance();
//        mAuth = FirebaseAuth.getInstance();
        emailtxt = (EditText) findViewById(R.id.emailTxt);
        passwordtxt = (EditText) findViewById(R.id.passwordTxt);
        logInbtn = (Button) findViewById(R.id.logInbtn);
        setSignUptxt();

    //TODO: authentication
    logInbtn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View v){
        String email = emailtxt.getText().toString().trim();
        String password = passwordtxt.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            emailtxt.setError(getResources().getString(R.string.EnterEmail));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordtxt.setError(getResources().getString(R.string.EnterPassword));
            return;
        }
//            mAuth.signInWithEmailAndPassword(email, password)
//                     .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                              //   Sign in success, update UI with the signed-in user's information
//                                FirebaseUser user = mAuth.getCurrentUser();

                                                        Toast.makeText(LogIn.this, "أهلا بك",
                                                                Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(LogIn.this, MainActivity.class);
                                                        startActivity(intent);


//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "signInWithEmail:failure", task.getException());
//                                Toast.makeText(LogIn.this,R.string.AuthenticationFailed ,
//                                        Toast.LENGTH_SHORT).show();
//
//                            }
//                        }
//                    });
    }
    });
    }
    //TODO: forget password

    private void setSignUptxt(){
    signUptxt= (TextView) findViewById(R.id.signUpPrompttxt);
    signUptxt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LogIn.this, Registration.class);
            startActivity(intent);
        }
        });
    }
}