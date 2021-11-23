package com.example.pneumoniadetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AppCompatActivity {

    private EditText fullName,password,email;
    private Button createAccount;
    private FirebaseAuth mAuth;
    private boolean valid=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        fullName=findViewById(R.id.fullName);
        password=findViewById(R.id.createPassword);
        email=findViewById(R.id.createEmailId);
        createAccount=findViewById(R.id.buttonCreateAccount);
        mAuth=FirebaseAuth.getInstance();
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                checkField(fullName);
//                checkField(email);
//                checkField(password);
               if(checkField(fullName)&&checkField(email)&&checkField(password)){
                   mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                       @Override
                       public void onSuccess(AuthResult authResult) {
                           Toast.makeText(getApplicationContext(), "Account created", Toast.LENGTH_SHORT).show();
                           mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void unused) {
                                   Toast.makeText(getApplicationContext(), "Verification email has been sent", Toast.LENGTH_SHORT).show();
                               }
                           }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(getApplicationContext(), "Verification email not sent", Toast.LENGTH_SHORT).show();
                               }
                           });
                           //startActivity(new Intent(getApplicationContext(),Login_Activity.class));
                           finish();
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   });
               }
            }
        });
    }
    public  static boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            return false;
        }else{
            return true;
        }
    }
}