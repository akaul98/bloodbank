package com.example.bloodbank.activites;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbank.R;
import com.example.bloodbank.utils.VolleySingleton;
import com.example.bloodbank.utils.endpoint;

import java.util.HashMap;
import java.util.Map;

public class activity_login extends AppCompatActivity {
    EditText phoneEt;
    EditText passwordEt;
    Button loginEt;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        phoneEt=(EditText)findViewById(R.id.phone);
        passwordEt=(EditText)findViewById(R.id.password);
        loginEt=(Button)findViewById(R.id.login);
        register=(TextView)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerintent= new Intent(activity_login.this,Register.class);
                startActivity(registerintent);

            }
            });

        loginEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneEt.setError(null);
                passwordEt.setError(null);
                String phone=phoneEt.getText().toString();
                String password=passwordEt.getText().toString();
                if(isValid(phone,password)){
                    login(phone,password);

                }
            }
        });
    }

    private boolean isValid(String phone,String password){
         if(phone.isEmpty() || phone.length()!=10){
             phoneEt.setError("Phone number  is not valid");
            showmessage("Phone number  is not valid");
            return false;
        }
        else if (password.isEmpty()){
            showmessage("password  is empty");
             passwordEt.setError("password  is empty");
            return false;
        }

        return true;
    }

    private void login(final String phone, final String password){  StringRequest stringRequest = new StringRequest(Request.Method.POST, endpoint.login_url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(!response.equals("Invalid Credentials")){
                        Toast.makeText(activity_login.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(activity_login.this,MainActivity.class));
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("phone",phone).apply();
                        PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("city",response).apply();
                        activity_login.this.finish();
                    }else{
                        Toast.makeText(activity_login.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            },new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(activity_login.this, "Something went wrong:(", Toast.LENGTH_SHORT).show();
            Log.d("VOLLEY", error.getMessage());
        }
    })
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("phone", phone);
            params.put("password", password);

            return params;
        }
    };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void showmessage(String msg){
        Toast.makeText(activity_login.this,msg,Toast.LENGTH_SHORT).show();

    }

}




