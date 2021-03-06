package com.example.tinni.myway;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.tinni.myway.Interface.ItemClickListener;
import com.example.tinni.myway.Model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Handicrafts extends AppCompatActivity {
    ProgressDialog mprogress;
    FirebaseDatabase db;
    DatabaseReference product;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Product,MenuViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handicrafts);
        db = FirebaseDatabase.getInstance();
        product = db.getReference("Handicraft");

        //progressbar
        mprogress= new ProgressDialog(this);
        // mprogress.setTitle("Processing...");
        mprogress.setMessage("Please wait...");
        mprogress.setCancelable(false);
        mprogress.setIndeterminate(true);


        //load product

        recyclerView = (RecyclerView)findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadMenu();


    }
    private void loadMenu() {
        mprogress.show();//to show progressbar
        adapter = new FirebaseRecyclerAdapter<Product, MenuViewHolder>(Product.class,R.layout.menu_item,MenuViewHolder.class,product) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Product model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Product clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(Handicrafts.this,""+clickItem.getName(),Toast.LENGTH_SHORT).show();
                        Intent hdetail = new Intent (Handicrafts.this,Details_hand.class);
                        hdetail.putExtra("HandicraftID",adapter.getRef(position).getKey());
                        startActivity(hdetail);
                    }
                });
                mprogress.dismiss();//to dissmiss progressbar
            }
        };
        recyclerView.setAdapter(adapter);


    }

}
