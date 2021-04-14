package com.banzaraktsaeva.console.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.banzaraktsaeva.console.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private ArrayList<Invoice> invoices = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        setInitialData();
        final RecyclerView recyclerView = root.findViewById(R.id.list);
        InvoiceAdapter adapter = new InvoiceAdapter(this.requireContext(), invoices);
        recyclerView.setAdapter(adapter);
        return root;
    }

    private void setInitialData() {
        invoices.add(new Invoice("01.03.2021", "Бразилиана", 12050));
        invoices.add(new Invoice("21.03.2021", "Евротрейд", 54100));
        invoices.add(new Invoice("08.04.2021", "Кафе Аполлон", 3200));
        invoices.add(new Invoice("21.03.2021", "Ситилонг", 25344));
        invoices.add(new Invoice("11.04.2021", "Леноводный карьер", 2340));
        invoices.add(new Invoice("01.03.2021", "Азбука вкуса", 15350));
        invoices.add(new Invoice("11.04.2021", "Космос", 5812));
        invoices.add(new Invoice("11.04.2021", "Пятерочка", 5812));
        invoices.add(new Invoice("08.04.2021", "Самсунг", 5812));
        invoices.add(new Invoice("21.03.2021", "Галактика", 5812));

        Invoice.sortListBy(invoices);
    }
}
