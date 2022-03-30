package com.example.pawfect_1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    EditText memailEt,mpasswordEt;
    Button mregisterBtn;
    ProgressDialog progressDialog;
    TextView mHaveAccountTv;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        ActionBar actionBar=getSupportActionBar();
//        assert actionBar != null;
//        actionBar.setTitle("Create Account");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
        memailEt=findViewById(R.id.emailEt);
        mpasswordEt=findViewById(R.id.passwordEt);
        mregisterBtn=findViewById(R.id.registerBtn);
        mHaveAccountTv=findViewById(R.id.accounTv);
        mAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registering User...");
        mregisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= memailEt.getText().toString().trim();
                String password= mpasswordEt.getText().toString().trim();
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    memailEt.setError("Invalid Email");
                    memailEt.setFocusable(true);
                }
                else
                    if(mpasswordEt.length()<6){
                       mpasswordEt.setError("Password length atleast 6 characters");
                        mpasswordEt.setFocusable(true);
                    }
                    else{
                        registerUser(email,password);
                    }
            }
        });
//handle login text view click listener
        mHaveAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
finish();
            }
        });
    }

    private void registerUser(String email, String password) {
progressDialog.show();
mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()){
    progressDialog.dismiss();
    FirebaseUser user=mAuth.getCurrentUser();
    String email=user.getEmail();
    String uid=user.getUid();
    HashMap<Object,String>hashMap=new HashMap<>();
    hashMap.put("email",email);
    hashMap.put("uid",uid);
    hashMap.put("name","");
    hashMap.put("phone","");
    hashMap.put("city","");
    hashMap.put("image","");
    hashMap.put("cover","");


    //Database
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    //path to store user data named "users"
    DatabaseReference reference=database.getReference("Users");
    //put data within hashmap in database
    reference.child(uid).setValue(hashMap);
    //
    Toast.makeText(RegisterActivity.this, "Register Successful\n"+ user.getEmail(), Toast.LENGTH_SHORT).show();
    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    finish();

}
else {
    progressDialog.dismiss();
    Log.w(TAG, "createUserWithEmailToken:failure", task.getException());
    Toast.makeText(RegisterActivity.this, "Authentication failed.",
            Toast.LENGTH_SHORT).show();

}
    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        progressDialog.dismiss();
        Toast.makeText(RegisterActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
    }
});
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }
}