package com.irhatech.product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {
    ArrayList<Product> products;
    ArrayList<Product> catproducts;
    public static ArrayList<Product> selectedproducts;
    ProductAdapter adapter;
    RecyclerView recyclerView;
    public static TextView totalprice;
    public static RecyclerView RV1;
    Button cat1,cat2,cat3,cat4,cat5;
    public static int total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        recyclerView=findViewById(R.id.RV);
        RV1=findViewById(R.id.RV1);
        totalprice=findViewById(R.id.textView30);
        cat1=findViewById(R.id.btn_cat1);
        cat2=findViewById(R.id.btn_cat2);
        cat3=findViewById(R.id.btn_cat3);
        cat4=findViewById(R.id.btn_cat4);
        cat5=findViewById(R.id.btn_cat5);
        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonscolors(cat1);
            }
        });
        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonscolors(cat2);
            }
        });
        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonscolors(cat3);
            }
        });
        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonscolors(cat4);
            }
        });
        cat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonscolors(cat5);
            }
        });


        setRecyclerView();

    }

    public void checkout(View view) {

        Intent intent = new Intent(ProductsActivity.this,Checkout.class);
        startActivity(intent);
    }
    public void setRecyclerView() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Products");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                products = new ArrayList<>();

                selectedproducts = new ArrayList<>();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Product product = d.getValue(Product.class);
                    product.id=d.getKey();
                    products.add(product);
                }

                setButtonscolors(cat1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public static void setRecyclerView1()
    {
        RV1.setLayoutManager(new LinearLayoutManager(RV1.getContext()));
        RV1.setItemAnimator(new DefaultItemAnimator());
        InvoiceAdapter adapter = new InvoiceAdapter(RV1.getContext(), selectedproducts);
        RV1.setAdapter(adapter);
        total=0;
        for (int i=0;i<selectedproducts.size();i++)
        {
            total=total+Integer.parseInt(selectedproducts.get(i).price);
        }

        totalprice.setText("$"+total+".00");


    }
    public void setButtonscolors(Button button)
    {
        setButtonsfont(cat1);
        cat1.setTextColor(getResources().getColor(R.color.black));

        setButtonsfont(cat2);
        cat2.setTextColor(getResources().getColor(R.color.black));

        setButtonsfont(cat3);
        cat3.setTextColor(getResources().getColor(R.color.black));

        setButtonsfont(cat4);
        cat4.setTextColor(getResources().getColor(R.color.black));

        setButtonsfont(cat5);
        cat5.setTextColor(getResources().getColor(R.color.black));

        button.setTextColor(getResources().getColor(R.color.purple_500));
        Typeface typeface = ResourcesCompat.getFont(ProductsActivity.this, R.font.poppins_semibold);
        button.setTypeface(typeface);

        catproducts=new ArrayList<>();
        for (int i=0;i<products.size();i++)
        {
            if (button.getText().toString().equals(products.get(i).cat))
            {
                catproducts.add(products.get(i));
            }
        }

        recyclerView.setLayoutManager(new GridLayoutManager(ProductsActivity.this, getResources().getInteger(R.integer.number_of_rows)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new ProductAdapter(ProductsActivity.this, catproducts);
        recyclerView.setAdapter(adapter);


    }
    public void setButtonsfont(Button button)
    {
        Typeface typeface = ResourcesCompat.getFont(ProductsActivity.this, R.font.poppins_semibold);
        button.setTypeface(typeface);
    }

}