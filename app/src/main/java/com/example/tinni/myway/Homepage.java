package com.example.tinni.myway;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
     //ProgressDialog mprogress;
     DrawerLayout mDrawerLayout;
     ActionBarDrawerToggle mToggle;
     FirebaseAuth auth;
     TextView nm,eml;

     CardView dr,hd,fr,fo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        auth = FirebaseAuth.getInstance();

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




        View headerView = navigationView.getHeaderView(0);
        nm = (TextView)headerView.findViewById(R.id.nm);
       // nm.setText(usr.getName());
        eml = (TextView)headerView.findViewById(R.id.eml);
        eml.setText(auth.getCurrentUser().getEmail());

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
            /*case R.id.about:
                startActivity(new Intent(Homepage.this,about.class));
                break;*/
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
}
