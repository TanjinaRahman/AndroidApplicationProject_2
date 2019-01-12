package com.example.tinni.myway;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rengwuxian.materialedittext.MaterialEditText;

public class settings extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final NavigationView naview = (NavigationView) findViewById(R.id.nav);
        naview.setNavigationItemSelectedListener(this);
        auth=FirebaseAuth.getInstance();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.about:
                startActivity(new Intent(settings.this,about.class));
                break;

            case R.id.update:
                updatePassword();
                break;

            case R.id.delete:
                deleteAccount();
                break;
        }
        return true;
    }



    private void deleteAccount() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Want to Delete Account? ");
        dialog.setMessage("Deleting this account will result in completely removing your account from the system and you wouldn't be able to access again.");

        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseUser firebaseUser = auth.getCurrentUser();

                firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(settings.this,"Account Deleted.",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(settings.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(settings.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }



    private void updatePassword() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Update Password ");
        LayoutInflater inflater=LayoutInflater.from(this);
        View c_layout=inflater.inflate(R.layout.changepass,null);

        final MaterialEditText edtc=c_layout.findViewById(R.id.edtn);
        final MaterialEditText edtr=c_layout.findViewById(R.id.edtr);

        dialog.setView(c_layout);

        dialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                dialog.dismiss();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null) {

                    if (edtc.getText().toString().equals(edtr.getText().toString())) {
                        user.updatePassword(edtr.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            dialog.dismiss();
                                            Toast.makeText(settings.this, "Password has been updated.", Toast.LENGTH_SHORT).show();
                                            auth.signOut();
                                            if(auth.getCurrentUser()==null) {
                                                settings.this.finish();
                                                startActivity(new Intent(settings.this,MainActivity.class));}

                                        } else {
                                            dialog.dismiss();
                                            Toast.makeText(settings.this, "Password could not be changed", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                        //
                    } else {
                        Toast.makeText(settings.this, "Doesn't match!", Toast.LENGTH_SHORT).show();
                    }

                }
                }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
dialog.show();

    }
}
