package com.example.tinni.myway;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tinni.myway.Model.Work;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
     //ProgressDialog mprogress;
    public String name;

    DrawerLayout mDrawerLayout;
     ActionBarDrawerToggle mToggle;
     FirebaseAuth auth;
     FirebaseDatabase db;
     DatabaseReference user,workk;
     TextView nm,eml;

     CardView dr,hd,fr,fo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);



        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        user = db.getReference("Users").child("Buyer").child(auth.getCurrentUser().getUid());

     /*   //progressbar
        mprogress= new ProgressDialog(this);
        // mprogress.setTitle("Processing...");
        mprogress.setMessage("Please wait...");
        mprogress.setCancelable(true);
        mprogress.setIndeterminate(true);*/



        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer);
        dr = (CardView)findViewById(R.id.dress);
        hd = (CardView)findViewById(R.id.hand);
        fr = (CardView)findViewById(R.id.fruit);
        fo = (CardView)findViewById(R.id.food);
       // fd = (LinearLayout) findViewById(R.id.food);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        //View headerView = navigationView.getHeaderView(0);

        //nm.setText(user.toString());
        //eml = (TextView)headerView.findViewById(R.id.eml);
        //eml.setText(auth.getCurrentUser().getEmail());

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String n=dataSnapshot.child("name").getValue().toString();
                String e =dataSnapshot.child("email").getValue().toString();

                View headerView = navigationView.getHeaderView(0);
                nm = (TextView)headerView.findViewById(R.id.nm);
                eml = (TextView)headerView.findViewById(R.id.eml);
                nm.setText(n);
                eml.setText(e);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



      /*fd.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(Homepage.this,food.class);
              startActivity(i);
          }
      });*/
        dr.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                      startActivity(new Intent(Homepage.this,Dress.class));

          }
        });
        hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this,Handicrafts.class));

            }
        });
        fo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this,food.class));

            }
        });
        fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Homepage.this,Fruits.class));

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.profile:
                startActivity(new Intent(Homepage.this,profile.class));
                break;
            case R.id.parcel:
                startActivity(new Intent(Homepage.this,Pbuyermap.class));
                break;

            case R.id.shop:
                startActivity(new Intent(Homepage.this,deliverMap.class));
                break;
            case R.id.work:
                wannawork();
                break;
            case R.id.settings:
                startActivity(new Intent(Homepage.this,settings.class));
                break;
            case R.id.logout:
                auth.signOut();
                Toast.makeText(this,"Logged Out",Toast.LENGTH_SHORT).show();
                if(auth.getCurrentUser()==null) {
                    this.finish();
                    startActivity(new Intent(Homepage.this,MainActivity.class));
                }
                break;
        }
        return true;
    }

    private void wannawork() {
        workk = db.getReference("Worker").child(auth.getCurrentUser().getUid());
        final String x = auth.getCurrentUser().getUid();
     /*   if(t=="ok") {

        }*/
     //if(!workk.getDatabase().equals(null)) {
         workk.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 if (dataSnapshot.exists() && dataSnapshot.child("uid").getValue().equals(x)) {
                     startActivity(new Intent(Homepage.this, Workernew.class));
                    // Toast.makeText(Homepage.this, "not ok", Toast.LENGTH_SHORT).show();
                     finish();

                 }
                 else {
                     AlertDialog.Builder dialog = new AlertDialog.Builder(Homepage.this);
                     dialog.setTitle("Want to Work? ");
                     dialog.setMessage("If you want to work here,then you have to pay 2$ per month.");
                     dialog.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             final Work wrk = new Work();
                             wrk.setUId(auth.getCurrentUser().getUid());
                             //Common.cwork=wrk;
                             workk.setValue(wrk).addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void aVoid) {
                                     Toast.makeText(Homepage.this, "Congratulations", Toast.LENGTH_SHORT).show();
                                     startActivity(new Intent(Homepage.this, Workernew.class));
                                     finish();

                                 }
                             }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {

                                     Toast.makeText(Homepage.this, "Oh, something went worng!", Toast.LENGTH_SHORT).show();
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

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });


    }
}
