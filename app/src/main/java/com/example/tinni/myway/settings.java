package com.example.tinni.myway;

<<<<<<< HEAD
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
=======
<<<<<<< HEAD
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class settings extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class settings extends AppCompatActivity {
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5

>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
<<<<<<< HEAD
        final NavigationView naview = (NavigationView) findViewById(R.id.nav);
        naview.setNavigationItemSelectedListener(this);
        auth=FirebaseAuth.getInstance();


=======
<<<<<<< HEAD

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
<<<<<<< HEAD
            case R.id.about:
                startActivity(new Intent(settings.this,about.class));
                break;

            case R.id.update:
                updatePassword();
                break;

            case R.id.delete:
=======
            case 1:
                startActivity(new Intent(settings.this,about.class));
                break;

            case 2:
                updatePassword();
                break;

            case 3:
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
                deleteAccount();
                break;
        }
        return true;
    }



    private void deleteAccount() {
<<<<<<< HEAD
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Want to Delete Account? ");
        dialog.setMessage("If you delete once ");
        dialog.show();
=======
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
    }



    private void updatePassword() {
<<<<<<< HEAD
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

=======
=======
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
    }
}
