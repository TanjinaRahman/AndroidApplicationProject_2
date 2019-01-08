package com.example.tinni.myway;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tinni.myway.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

//import android.support.v7.app.AlertDialog;
//import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {
    ProgressDialog mprogress;
    Button blogin;
    TextView bregister,fpass;
    RelativeLayout rootLayout;
    EditText mEmail;
    EditText mPassword;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //progressbar
        mprogress= new ProgressDialog(this);
        // mprogress.setTitle("Processing...");
        mprogress.setMessage("Please wait...");
        mprogress.setCancelable(true);
        mprogress.setIndeterminate(true);




        blogin = (Button) findViewById(R.id.butID);
        bregister = (TextView) findViewById(R.id.rID);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        mEmail = (MaterialEditText) findViewById(R.id.edtEmail);
        mPassword = (MaterialEditText) findViewById(R.id.edtPass);
        fpass = (TextView) findViewById(R.id.forgetpass);


        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users").child("Buyer");

        if (auth.getCurrentUser() != null && auth.getCurrentUser().isEmailVerified()) {
            // User is signed in.
            Intent i = new Intent(MainActivity.this, Homepage.class);
            startActivity(i);
            finish();
        }
        else {

        // No user is signed in.
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showForgetDialog();
            }
        });

        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRegisterDialog();
            }
        });

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();

            }
        });
    }

        }

    private void showLoginDialog(){
    final String edtEmail = mEmail.getText().toString();
    final String edtPass = mPassword.getText().toString();

    if (TextUtils.isEmpty(edtEmail)) {
        Snackbar.make(rootLayout, "Please enter email address", Snackbar.LENGTH_SHORT).show();
        return;
    }
    if (TextUtils.isEmpty(edtPass)) {
        Snackbar.make(rootLayout, "Please enter password", Snackbar.LENGTH_SHORT).show();
        return;
    }
        mprogress.show();
    auth.signInWithEmailAndPassword(edtEmail, edtPass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
        @Override
        public void onSuccess(AuthResult authResult) {

            if (auth.getCurrentUser().isEmailVerified()) {
                mprogress.dismiss();
                startActivity(new Intent(MainActivity.this, Homepage.class));
                finish();
            } else {
                mprogress.dismiss();
                Snackbar.make(rootLayout, "Please verify your email address. ", Snackbar.LENGTH_LONG).show();
            }
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            mprogress.dismiss();
            Snackbar.make(rootLayout, "Login Failed.\n" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
        }
    });

}


    private void showRegisterDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("REGISTER ");
        dialog.setMessage("Please use email to register");
        LayoutInflater inflater=LayoutInflater.from(this);
        View register_layout=inflater.inflate(R.layout.buy_register,null);


        final MaterialEditText edtEmail=register_layout.findViewById(R.id.edtEmail);
        final MaterialEditText edtPass=register_layout.findViewById(R.id.edtPass);
        final MaterialEditText edtName=register_layout.findViewById(R.id.edtName);
        final MaterialEditText edtPhone=register_layout.findViewById(R.id.edtPhone);

        dialog.setView(register_layout);

        dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.dismiss();

                if(TextUtils.isEmpty(edtEmail.getText().toString()))
                {
                    Snackbar.make(rootLayout,"Please enter email address",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtPass.getText().toString()))
                {
                    Snackbar.make(rootLayout,"Please enter password",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtName.getText().toString()))
                {
                    Snackbar.make(rootLayout,"Please enter Name",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtPhone.getText().toString()))
                {
                    Snackbar.make(rootLayout,"Please enter Phone No",Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(edtPass.getText().toString().length()<4)
                {
                    Snackbar.make(rootLayout,"Password is too short",Snackbar.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPass.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
<<<<<<< HEAD
                                final User user=new User();
=======
                                User user=new User();
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
                                user.setEmail(edtEmail.getText().toString());
                                user.setPassword(edtPass.getText().toString());
                                user.setName(edtName.getText().toString());
                                user.setPhone(edtPhone.getText().toString());
<<<<<<< HEAD
                                //common.currentUser=user;
=======
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5

                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){
                                                            Snackbar.make(rootLayout,"Registered successfully.\nPlease check your email for verification.",Snackbar.LENGTH_LONG).show();
                                                            //startActivity(new Intent(MainActivity.this,Favourite.class));
<<<<<<< HEAD

=======
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
                                                        }
                                                        else {
                                                            Snackbar.make(rootLayout,task.getException().getMessage(),Snackbar.LENGTH_LONG).show();
                                                            //startActivity(new Intent(MainActivity.this,Favourite.class));
                                                        }

                                                    }
                                                });

                                                //
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Snackbar.make(rootLayout,"Failed. "+e.getMessage(),Snackbar.LENGTH_LONG).show();

                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(rootLayout,"Failed. "+e.getMessage(),Snackbar.LENGTH_LONG).show();
                    }
                });

            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                dialogInterface.dismiss();
            }
        });

        dialog.show();

    }


    public void showForgetDialog(){
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("RESET PASSWORD ");
        dialog.setMessage("Please use email to Reset Password");
        LayoutInflater inflater=LayoutInflater.from(this);
        View repass_layout=inflater.inflate(R.layout.forgotpass,null);

        final MaterialEditText repass = repass_layout.findViewById(R.id.repass);
        dialog.setView(repass_layout);
        dialog.setPositiveButton("RESET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String usermail = repass.getText().toString().trim();

                if(usermail.equals("")){
                    Snackbar.make(rootLayout,"Please Enter Your Registered Email ID ",Snackbar.LENGTH_LONG).show();

                }
                else {
                    auth.sendPasswordResetEmail(usermail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Snackbar.make(rootLayout,"Password reset email sent",Snackbar.LENGTH_LONG).show();
                            }
                            else {
                                Snackbar.make(rootLayout,"Please try again",Snackbar.LENGTH_LONG).show();
                            }

                        }
                    });
                }
            }
        });
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}
