package com.banzaraktsaeva.console.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.banzaraktsaeva.console.R;

import java.util.List;

import static android.view.View.GONE;

public class InvoiceAdapter  extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Invoice> invoices;

    private final String TAG = this.getClass().getSimpleName();

    InvoiceAdapter(Context context, List<Invoice> invoices) {
        this.invoices = invoices;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public InvoiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Log.v(TAG, "onCreateViewHolder");


        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(InvoiceAdapter.ViewHolder holder, int position) {
        Invoice invoice = invoices.get(position);
        holder.dateView.setText(invoice.getDate());

        if (position == 0 || !invoices.get(position).getDate().equals(invoices.get(position-1).getDate())) {
            holder.dateView.setVisibility(View.VISIBLE);
        } else {
            holder.dateView.setVisibility(View.GONE);
        }
        holder.buyerView.setText(invoice.getBuyer());
        holder.priceView.setText(String.valueOf(invoice.getPrice()));

        //Log.v(TAG, "onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        //Log.v(TAG, "getItemCount");
        return invoices.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView dateView, buyerView, priceView;
        ViewHolder(View view){
            super(view);
            dateView = view.findViewById(R.id.dateView);
            buyerView = view.findViewById(R.id.buyerView);
            priceView = view.findViewById(R.id.priceView);
        }
    }
}