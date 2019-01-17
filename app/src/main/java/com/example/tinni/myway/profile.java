package com.example.tinni.myway;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.provider.DocumentFile;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    private TextView nam,mail,phn;
    private ImageView profile_img;
    private ImageButton edit_profile_img;
     FirebaseAuth auth;
     FirebaseDatabase db;
     DatabaseReference user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        InitializeUIComponents();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case 1010:{
                    DocumentFile file=DocumentFile.fromSingleUri(profile.this,data.getData());
                    profile_img.setImageDrawable(
                         Drawable.createFromPath(UriUtils.getDocumentFileAbsPath(file,profile.this))
                    );



                    // todo upload profile image and set it as profile image.



                    break;
                }
            }
        }
    }

    private void InitializeUIComponents() {
        profile_img=findViewById(R.id.profile_img);
        edit_profile_img=findViewById(R.id.edit_profile_img);
        edit_profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                startActivityForResult(intent,1010);
            }
        });
    }
}
