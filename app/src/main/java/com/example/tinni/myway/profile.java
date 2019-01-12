package com.example.tinni.myway;

import android.os.Bundle;
<<<<<<< HEAD
import android.support.annotation.NonNull;
=======
<<<<<<< HEAD
import android.support.annotation.NonNull;
=======
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
     FirebaseAuth auth;
     FirebaseDatabase db;
     DatabaseReference user;

<<<<<<< HEAD
=======
=======
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference users;
    public String n = "mail";
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
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

<<<<<<< HEAD
=======
=======
        nam = findViewById(R.id.t1);
        mail = findViewById(R.id.t2);
        phn = findViewById(R.id.t3);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        users = firebaseDatabase.getReference("Buyer");

       /*users.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
              mail.setText((CharSequence) dataSnapshot.getValue());
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });*/
        users.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                nam.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
            }
        });


<<<<<<< HEAD

=======
<<<<<<< HEAD

=======
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
    }
}
