package com.irhatech.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    ArrayList<Product> products;
    Context context;

    // Constructor of the class
    public InvoiceAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return products.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_receipt, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
       try {


           holder.name.setText(products.get(position).name);
           holder.price.setText("$"+products.get(position).price);






       }catch (Exception e)
       {

       }
    }


    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;

        public ViewHolder(View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.name);
            price =itemView.findViewById(R.id.price);

        }
    }

}
