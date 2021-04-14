package com.banzaraktsaeva.console.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.banzaraktsaeva.console.R;
import com.banzaraktsaeva.console.ui.gallery.Invoice;
import com.banzaraktsaeva.console.ui.gallery.InvoiceAdapter;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private ArrayList<Order> orders = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        setInitialData();
        final RecyclerView recyclerView = root.findViewById(R.id.list);
        OrderAdapter adapter = new OrderAdapter(this.requireContext(), orders);
        recyclerView.setAdapter(adapter);
        return root;
    }

    private void setInitialData() {
        orders.add(new Order("01.03.2021", "Бразилиана", true, 12050));
        orders.add(new Order("21.03.2021", "Евротрейд",false, 54100));
        orders.add(new Order("01.03.2021", "Курская ООО", true, 12050));
        orders.add(new Order("21.03.2021", "PROSTO кафе", true, 54100));
        orders.add(new Order("01.03.2021", "Линк-ОАО", false, 12050));
        orders.add(new Order("11.03.2021", "Евротрейд",true, 13450));
        orders.add(new Order("08.03.2021", "АндроидOFF ООО", true, 5120));
        orders.add(new Order("18.03.2021", "Сибирь кафе", true, 15000));
        orders.add(new Order("05.03.2021", "Рога и копыта", false, 65400));

        Order.sortListBy(orders);
    }
}
