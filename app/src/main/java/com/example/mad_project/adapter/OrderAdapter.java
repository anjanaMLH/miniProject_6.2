package com.example.mad_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_project.Models.Order;
import com.example.mad_project.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderItemHolder> {

    List<Order> data;

    public OrderAdapter(List<Order> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public OrderItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout view = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent,false);
        OrderItemHolder holder = new OrderItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemHolder holder, int position) {
        holder.textView.setText(data.get(position).getFood());
        holder.textView2.setText(data.get(position).getResturent());
        holder.imageView.setBackgroundResource(R.drawable.best_lasagna_550);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class OrderItemHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public TextView textView2;
        ImageView imageView;

        public OrderItemHolder(@NonNull View itemView) {
            super(itemView);
            this.textView2 = itemView.findViewById(R.id.order_resturent);
            this.textView = itemView.findViewById(R.id.order_text);
            this.imageView = itemView.findViewById(R.id.image);
        }
    }
}
