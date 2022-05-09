package com.banzaraktsaeva.console.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.banzaraktsaeva.console.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this.requireContext());
        String url ="https://185.62.194.22/banzaraksaeva/ru/hs/TAPI/V1/Metod1";

        final TextView restET = root.findViewById(R.id.rest_data);

        //for String Request
// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//// Display the first 500 characters of the response string.
//                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getContext(), "Fuck", Toast.LENGTH_LONG).show();
//            }
//        });
//
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);


//for JSONArray Request
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String totalRest = "";
                try {
                    JSONObject restObject = response.getJSONObject("Total");
                    totalRest = restObject.getString("Total");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String totalStr = totalRest + "руб";
                restET.setText(totalStr);
                Toast.makeText(getContext(), totalRest, Toast.LENGTH_LONG).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Сервер не отвечает", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);

        return root;
    }


}
