package com.example.pawfect_1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 100;
    private GoogleSignInClient googleSignInClient;

    FirebaseAuth firebaseAuth;
    EditText memailEt,mpasswordEt;
    TextView mnotHaveAccntTv,mRecoverPassTv;
    Button mLoginBtn;
//    SignInButton mGoogleLoginBtn;
    ProgressDialog pd;


    private FirebaseAuth mAuth;
private static final String TAG="GOOGLE_SIGN_IN_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ActionBar actionBar = null;
//        actionBar.setTitle("Login");
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
GoogleSignInOptions googleSignInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("469524602663-vvbbrdaho16sa55nffkk986oovj1kb1j.apps.googleusercontent.com")
        .requestEmail()
        .build();
googleSignInClient=GoogleSignIn.getClient(this,googleSignInOptions);

        mAuth = FirebaseAuth.getInstance();
        memailEt=findViewById(R.id.emailEt);
        mpasswordEt=findViewById(R.id.passwordEt);
        mnotHaveAccntTv=findViewById(R.id.nothave_accountTv);
        mLoginBtn=findViewById(R.id.loginBtn);
        mRecoverPassTv=findViewById(R.id.recoverPassTv);
//        mGoogleLoginBtn=findViewById(R.id.googleLoginBtn);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String email=memailEt.getText().toString().trim();
                String password=mpasswordEt.getText().toString().trim();
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    memailEt.setError("Invalid Email");
                    memailEt.setFocusable(true);
                }
                else
                {
                    loginUser(email,password);
                }

            }
        });
        mnotHaveAccntTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });
        //password recovery
        mRecoverPassTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRecoverPasswordDialog();
            }
        });
        //Google Sign In onclick
//        mGoogleLoginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view)
//            {
//                    Log.d(TAG,"onclick:Begin Google SignIn");
//                    Intent intent=googleSignInClient.getSignInIntent();
//                    startActivityForResult(intent,RC_SIGN_IN);
//
//            }
//        });
        pd=new ProgressDialog(this);

    }

    private void showRecoverPasswordDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");
        //Linear Layout
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText emailEt=new EditText(this);
        emailEt.setHint("Email");
        emailEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        emailEt.setMinEms(16);//This is for adjusting the width to 16 whatever may be the edit text
        linearLayout.addView(emailEt);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);
        //positive button
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //If I click on Recover
                String email=emailEt.getText().toString().trim();
                beginRecovery(email);
            }
        });
        //negative button
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //If I click on cancel the dialog should dismiss
               dialogInterface.dismiss();

            }
        });
        //here it shows the dialog
        builder.create().show();
    }

    private void beginRecovery(String email)
    {
        pd.setMessage("Sending Email..");
        pd.show();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    pd.dismiss();
                    Toast.makeText(LoginActivity.this, "Email has been sent", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Failed to sent the mail", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginUser(String email, String password)
    {
        pd.setMessage("Logging In..");
        pd.show();
      mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful())
              {
                  pd.dismiss();
                  FirebaseUser user = mAuth.getCurrentUser();
                  Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                 /* String email=user.getEmail();
                  String uid=user.getUid();
                  HashMap<Object,String> hashMap=new HashMap<>();
                  hashMap.put("email",email);
                  hashMap.put("uid",uid);
                  hashMap.put("name","");
                  hashMap.put("phone","");
                  hashMap.put("city","");
                  hashMap.put("image","");
                  hashMap.put("cover","");
                  //Database*/
                  FirebaseDatabase database=FirebaseDatabase.getInstance();
                  //path to store user data named "users"
                  DatabaseReference reference=database.getReference("Users");
                  //put data within hashmap in database
                  //reference.child(uid).setValue(hashMap);
                  //
                  startActivity(new Intent(LoginActivity.this, MainActivity.class));
                  finish();

              }
              else
              {
                  pd.dismiss();
                  Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();


              }
          }
      }).addOnFailureListener(new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
              Toast.makeText(LoginActivity.this, "Login Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
          }
      });
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN)
        {
            Log.d(TAG,"onActivityResult:Google SignInintent result");
            Task<GoogleSignInAccount>accountTask=GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account=accountTask.getResult(ApiException.class);
                firebaseAuthWithGoogleAccount(account);


            }catch(Exception e){
                Log.d(TAG,"onActivity:"+e.getMessage());

            }
        }
    }

    private void firebaseAuthWithGoogleAccount(GoogleSignInAccount account)
    {
        Log.d(TAG,"onActivity:firebase auth with google");
        AuthCredential credential=GoogleAuthProvider.getCredential(account.getIdToken(),null);
         mAuth.signInWithCredential(credential)
               .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                   @Override
                   public void onSuccess(AuthResult authResult) {


                       Log.d(TAG,"onActivity:Logged In");
                       FirebaseUser firebaseUser=mAuth.getCurrentUser();
                       startActivity(new Intent(LoginActivity.this, MainActivity.class));
                       finish();


                   }
               }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Log.d(TAG,"onActivity:Logged In failed"+e.getMessage());
             }
         });
    }
}