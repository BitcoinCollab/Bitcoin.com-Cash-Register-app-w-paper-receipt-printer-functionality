package com.irhatech.product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Checkout extends AppCompatActivity {

    TextView price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        price=findViewById(R.id.textView);
        price.setText("$"+ProductsActivity.total);
    }



    public void per15(View view) {
        int newvalue;
        newvalue = (ProductsActivity.total*15)/100;
        newvalue=ProductsActivity.total+newvalue;
        price.setText("$"+newvalue);

    }

    public void per25(View view) {
        int newvalue;
        newvalue = (ProductsActivity.total*20)/100;
        newvalue=ProductsActivity.total+newvalue;
        price.setText("$"+newvalue);
    }

    public void per20(View view) {
        int newvalue;
        newvalue = (ProductsActivity.total*25)/100;
        newvalue=ProductsActivity.total+newvalue;
        price.setText("$"+newvalue);
    }
}