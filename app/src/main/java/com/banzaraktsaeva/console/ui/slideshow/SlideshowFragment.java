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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.banzaraktsaeva.console.R;
import com.banzaraktsaeva.console.ui.gallery.Invoice;
import com.banzaraktsaeva.console.ui.gallery.InvoiceAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class SlideshowFragment extends Fragment {

    private ArrayList<Order> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView dateView;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String date = "";

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
        orders.add(new Order("01.03.2021", "????????????????????", true, 12050));
        orders.add(new Order("21.03.2021", "??????????????????",false, 54100));
        orders.add(new Order("01.03.2021", "?????????????? ??????", true, 12050));
        orders.add(new Order("21.03.2021", "PROSTO ????????", true, 54100));
        orders.add(new Order("01.03.2021", "????????-??????", false, 12050));
        orders.add(new Order("11.03.2021", "??????????????????",true, 13450));
        orders.add(new Order("08.03.2021", "??????????????OFF ??????", true, 5120));
        orders.add(new Order("18.03.2021", "???????????? ????????", true, 15000));
        orders.add(new Order("05.03.2021", "???????? ?? ????????????", false, 65400));

        Order.sortListBy(orders);
    }

    public void getOrders(View view, String date){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.requireContext());
        String url ="https://185.62.194.22/banzaraksaeva/ru/hs/TAPI/V1/Metod2";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray invoiceArray = response.getJSONArray(0);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                String invoice = "";
//                try {
//                    JSONObject invoiceObject = response.getJSONObject(0);
//                    invoice = invoiceObject.getString("");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "no2", Toast.LENGTH_LONG).show();
            }
        });

        queue.add(request);

    }
}
