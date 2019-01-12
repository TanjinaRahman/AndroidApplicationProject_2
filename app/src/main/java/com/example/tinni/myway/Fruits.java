package com.example.tinni.myway;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.tinni.myway.Interface.ItemClickListener;
import com.example.tinni.myway.Model.Product;
import com.example.tinni.myway.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Fruits extends AppCompatActivity {
    ProgressDialog mprogress;
    FirebaseDatabase db;
    DatabaseReference product;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Fruits extends AppCompatActivity {
>>>>>>> 62eb49be8f7e1a351c6ecd03ca90a1de185c6e92
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
        db = FirebaseDatabase.getInstance();
        product = db.getReference();

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
        FirebaseRecyclerAdapter<Product,MenuViewHolder> adapter = new FirebaseRecyclerAdapter<Product, MenuViewHolder>(Product.class,R.layout.menu_item,MenuViewHolder.class,product) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Product model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imageView);
                final Product clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(Fruits.this,""+clickItem.getName(),Toast.LENGTH_SHORT).show();
                    }
                });
                mprogress.dismiss();//to dissmiss progressbar
            }
        };
        recyclerView.setAdapter(adapter);


    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
    }
>>>>>>> 62eb49be8f7e1a351c6ecd03ca90a1de185c6e92
>>>>>>> fc40b365bb3ac4a2a0968b99aea596cabbd149f5
>>>>>>> a460a02678e6a1e66a0aec07db120c5a30c195b1
}
