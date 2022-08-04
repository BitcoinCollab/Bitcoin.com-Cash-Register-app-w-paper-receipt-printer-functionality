package com.irhatech.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    ArrayList<Product> products;
    Context context;

    // Constructor of the class
    public ProductAdapter(Context context, ArrayList<Product> products) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_products, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
       try {


           holder.name.setText(products.get(position).name);
           holder.price.setText("$"+products.get(position).price);

           Glide
                   .with(context)
                   .load(products.get(position).pic)
                   .into(holder.pic);

           if (ProductsActivity.selectedproducts.contains(products.get(position)))
           {
               holder.name.setTextColor(context.getResources().getColor(R.color.purple_500));
               holder.price.setTextColor(context.getResources().getColor(R.color.purple_500));

           }else
           {
               holder.name.setTextColor(context.getResources().getColor(R.color.black));
               holder.price.setTextColor(context.getResources().getColor(R.color.black));

           }

           holder.cardView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (ProductsActivity.selectedproducts.contains(products.get(position)))
                   {
                       ProductsActivity.selectedproducts.remove(products.get(position));
                       holder.name.setTextColor(context.getResources().getColor(R.color.black));
                       holder.price.setTextColor(context.getResources().getColor(R.color.black));

                   }else
                   {
                       ProductsActivity.selectedproducts.add(products.get(position));
                       holder.name.setTextColor(context.getResources().getColor(R.color.purple_500));
                       holder.price.setTextColor(context.getResources().getColor(R.color.purple_500));

                   }
                   ProductsActivity.setRecyclerView1();
               }
           });



       }catch (Exception e)
       {

       }
    }


    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        ImageView pic;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.tv_product_name);
            pic =itemView.findViewById(R.id.img_product);
            price =itemView.findViewById(R.id.tv_product_price);
            cardView=itemView.findViewById(R.id.item);

        }
    }

}
