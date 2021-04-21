package com.banzaraktsaeva.console.ui.gallery;

import android.app.DatePickerDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.banzaraktsaeva.console.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class GalleryFragment extends Fragment {

    private ArrayList<Invoice> invoices = new ArrayList<>();

    private RecyclerView recyclerView;

    //private DatePicker datePicker;

    private TextView dateView;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        setInitialData();
        recyclerView = root.findViewById(R.id.list);
        InvoiceAdapter adapter = new InvoiceAdapter(this.requireContext(), invoices);
        recyclerView.setAdapter(adapter);

        /*datePicker = root.findViewById(R.id.datePicker);
        long today = System.currentTimeMillis()/1000;
        datePicker.setMinDate(today);*/


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
        invoices.add(new Invoice("01.03.2021", "Бразилиана", 12050));
        invoices.add(new Invoice("21.03.2021", "Евротрейд", 54100));
        invoices.add(new Invoice("08.04.2021", "Кафе Аполлон", 3200));
        invoices.add(new Invoice("21.03.2021", "Ситилонг", 25344));
        invoices.add(new Invoice("11.04.2021", "Леноводный карьер", 2340));
        invoices.add(new Invoice("01.03.2021", "Азбука вкуса", 15350));
        invoices.add(new Invoice("11.04.2021", "Космос", 10354));
        invoices.add(new Invoice("11.04.2021", "Пятерочка", 2354));
        invoices.add(new Invoice("08.04.2021", "Самсунг", 5812));
        invoices.add(new Invoice("21.03.2021", "Галактика", 45687));

        Invoice.sortListBy(invoices);
    }

    public void getInvoices(View view){
        Toast.makeText(getContext(), "U just got ur invoice list", Toast.LENGTH_LONG).show();

        /*
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "JSONArray made me worry", Toast.LENGTH_LONG).show();
            }
        });

        queue.add(request);
         */
    }
}
