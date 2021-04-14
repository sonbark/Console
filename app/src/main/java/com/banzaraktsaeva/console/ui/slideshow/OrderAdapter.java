package com.banzaraktsaeva.console.ui.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.banzaraktsaeva.console.R;
import com.banzaraktsaeva.console.ui.gallery.Invoice;
import com.banzaraktsaeva.console.ui.gallery.InvoiceAdapter;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Order> orders;

    private final String TAG = this.getClass().getSimpleName();

    OrderAdapter(Context context, List<Order> orders) {
        this.orders = orders;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Log.v(TAG, "onCreateViewHolder");


        View view = inflater.inflate(R.layout.list_item_order, parent, false);
        return new OrderAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(OrderAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);

        if (order.getStatus()) {
            holder.statusView.setBackgroundResource(R.drawable.green);
        } else {holder.statusView.setBackgroundResource(R.drawable.red);}

        holder.dateView.setText(order.getDate());

        if (position == 0 || !orders.get(position).getDate().equals(orders.get(position-1).getDate())) {
            holder.dateView.setVisibility(View.VISIBLE);
        } else {
            holder.dateView.setVisibility(View.GONE);
        }
        holder.buyerView.setText(order.getBuyer());
        holder.priceView.setText(String.valueOf(order.getPrice()));

        //Log.v(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        //Log.v(TAG, "getItemCount");
        return orders.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView statusView;
        final TextView dateView, buyerView, priceView;
        ViewHolder(View view){
            super(view);
            dateView = view.findViewById(R.id.dateView);
            buyerView = view.findViewById(R.id.buyerView);
            statusView = view.findViewById(R.id.statusView);
            priceView = view.findViewById(R.id.priceView);
        }
    }
}
