package com.example.bloodbank.activites;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.common.Method;
import com.example.bloodbank.R;
import com.example.bloodbank.utils.VolleySingleton;
import com.example.bloodbank.utils.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.transform.ErrorListener;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final EditText et_blood_group, et_city;
        et_blood_group = findViewById(R.id.et_blood_group);
        et_city = findViewById(R.id.et_city);
        Button submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String blood_group = et_blood_group.getText().toString();
                String city = et_city.getText().toString();
                if(isValid(blood_group, city)){
                    get_search_results(blood_group, city);
                }
            }
        });
    }


    private void get_search_results(final String bloodgroup, final String city) {
            StringRequest stringRequest = new StringRequest(
                    Method.POST, endpoint.search_donors, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //json response

                }
            }, new Response.ErrorListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Search.this, "Something went wrong:(", Toast.LENGTH_SHORT).show();
                    Log.d("VOLLEY", Objects.requireNonNull(error.getMessage()));
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("city", city);
                    params.put("bloodgroup", bloodgroup);
                    return params;
                }
            };
            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }




    private boolean isValid(String blood_group, String city){
        List<String> valid_blood_groups = new ArrayList<>();
        valid_blood_groups.add("A+");
        valid_blood_groups.add("A-");
        valid_blood_groups.add("B+");
        valid_blood_groups.add("B-");
        valid_blood_groups.add("AB+");
        valid_blood_groups.add("AB-");
        valid_blood_groups.add("O+");
        valid_blood_groups.add("O-");
        if(!valid_blood_groups.contains(blood_group)){
            showMsg("Blood group invalid choose from " + valid_blood_groups);
            return false;
        }else if(city.isEmpty()){
            showMsg("Enter city");
            return false;
        }
        return true;
    }


    private void showMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



}
