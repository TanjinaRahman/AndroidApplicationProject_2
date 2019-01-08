package com.example.tinni.myway;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
<<<<<<< HEAD

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case 1:
                startActivity(new Intent(settings.this,about.class));
                break;

            case 2:
                updatePassword();
                break;

            case 3:
                deleteAccount();
                break;
        }
        return true;
    }



    private void deleteAccount() {
    }



    private void updatePassword() {
=======
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
    }
}
