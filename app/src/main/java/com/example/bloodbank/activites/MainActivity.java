
package com.example.bloodbank.activites;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidnetworking.common.Method;
import com.example.bloodbank.Adapters.RequestAdapter;
import com.example.bloodbank.R;
import com.example.bloodbank.datamodeling.RequestDataModel;
import com.example.bloodbank.utils.VolleySingleton;
import com.example.bloodbank.utils.endpoint;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<RequestDataModel> requestDataModels;
    private RequestAdapter requestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView makerequest=findViewById(R.id.makerequest);
        makerequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerintent = new Intent(MainActivity.this, MakeRequestActivity.class);
                startActivity(registerintent);
            }
        });
        requestDataModels = new ArrayList<>()   ;
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_button) {
                    startActivity(new Intent(MainActivity.this, Search.class));
                }
                return false;
            }
        });

        recyclerView =findViewById(R.id.recycleview);
        LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        requestAdapter=new RequestAdapter(requestDataModels,this);
        recyclerView.setAdapter(requestAdapter);
        popluateHomepage();
        TextView picklocation=findViewById(R.id.picklocation);
        String location= PreferenceManager.getDefaultSharedPreferences(this).getString("city","No city Found");
        if (!location.equals("No city Found ")){
            picklocation.setText(location);

        }
    }

    private void popluateHomepage() {
       final String city = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("city","no_city") ;
        StringRequest stringRequest = new StringRequest(Method.POST, endpoint.get_requests,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson =new Gson();
                        Type type= new TypeToken<List<RequestDataModel>>(){}.getType();
                        List<RequestDataModel> dataModels = gson.fromJson(response,type);
                        requestDataModels.addAll(dataModels);
                        requestAdapter.notifyDataSetChanged();
                    }
                },new Response.ErrorListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something went wrong:(", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", Objects.requireNonNull(error.getMessage()));
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("city",city);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

}