package com.banzaraktsaeva.console.ui.slideshow;

import android.app.DatePickerDialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.banzaraktsaeva.console.R;
import com.banzaraktsaeva.console.ui.gallery.Invoice;
import com.banzaraktsaeva.console.ui.gallery.InvoiceAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class SlideshowFragment extends Fragment {

    private ArrayList<Order> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView dateView;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        setInitialData();
        recyclerView = root.findViewById(R.id.list);
        OrderAdapter adapter = new OrderAdapter(this.requireContext(), orders);
        recyclerView.setAdapter(adapter);

        dateView = root.findViewById(R.id.dateView);
        dateView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(requireContext(),
                        android.R.style.Theme_Material_Dialog_MinWidth, mDateSetListener, year, month, day);
                //Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Objects.requireNonNull(dialog.getWindow()).setColorMode(ActivityInfo.COLOR_MODE_DEFAULT);
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = "";
                if (day>=10 && month>=10){
                    date = day + "." + month + "." + year;
                } else if (month<10){
                    date = day + ".0" + month + "." + year;
                } else if (day<10){
                    date = "0" + day + "." + month + "." + year;
                }
                dateView.setText(date);
            }
        };

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
