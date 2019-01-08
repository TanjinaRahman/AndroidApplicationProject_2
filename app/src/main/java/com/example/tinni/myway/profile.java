package com.example.tinni.myway;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    private ImageView propic;
    private TextView nam,mail,phn;
     FirebaseAuth auth;
     FirebaseDatabase db;
     DatabaseReference user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth =FirebaseAuth.getInstance();

        db = FirebaseDatabase.getInstance();
        user = db.getReference("Users").child("Buyer").child(auth.getCurrentUser().getUid());

        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String n=dataSnapshot.child("name").getValue().toString();
                String e =dataSnapshot.child("email").getValue().toString();
                String p =dataSnapshot.child("phone").getValue().toString();

                nam = findViewById(R.id.t1);
                mail = findViewById(R.id.t2);
                phn = findViewById(R.id.t3);
                nam.setText(n);
                mail.setText(e);
                phn.setText(p);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
